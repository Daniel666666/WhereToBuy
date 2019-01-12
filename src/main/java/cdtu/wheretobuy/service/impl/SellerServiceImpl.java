package cdtu.wheretobuy.service.impl;

import cdtu.wheretobuy.mapper.GoodsMapper;
import cdtu.wheretobuy.mapper.SellerMapper;
import cdtu.wheretobuy.pojo.*;
import cdtu.wheretobuy.service.SellerService;
import cdtu.wheretobuy.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerMapper sellerMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<Seller> findAll() {
		return sellerMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Seller> page=   (Page<Seller>) sellerMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Autowired
	private RedisTemplate redisTemplate;



	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	/**
	 * 增加
	 */
	@Override
	public void add(Seller seller) {
		seller.setStatus("0");
		seller.setPassword(DigestUtils.md5DigestAsHex(seller.getPassword().getBytes()));
		sellerMapper.insert(seller);
		String activeCode= RandomUtil.generateStr(32);
		redisTemplate.boundHashOps("sellerActiveCode").put(seller.getId(),activeCode);
		Map map=new HashMap();
		map.put("obj","seller");
		map.put("addr",seller.getEmail());
		map.put("sellerId",seller.getId());
		map.put("activeCode",activeCode);
		jmsMessagingTemplate.convertAndSend("sendEmail",map);//发送激活邮件
		System.out.println("已发送activeMq----发送邮件消息!");
	}


	/**
	 * 修改
	 */
	@Override
	public void update(Seller seller){
		//seller.setPassword(DigestUtils.md5DigestAsHex(seller.getPassword().getBytes()));
		sellerMapper.updateByPrimaryKey(seller);
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Seller findOne(Integer id){
		return sellerMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			sellerMapper.deleteByPrimaryKey(id);
		}
	}


		@Override
	public PageResult findPage(Seller seller, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		SellerExample example=new SellerExample();
		SellerExample.Criteria criteria = example.createCriteria();

		if(seller!=null){
						if(seller.getName()!=null && seller.getName().length()>0){
				criteria.andNameLike("%"+seller.getName()+"%");
			}
			if(seller.getNickName()!=null && seller.getNickName().length()>0){
				criteria.andNickNameLike("%"+seller.getNickName()+"%");
			}
			if(seller.getPassword()!=null && seller.getPassword().length()>0){
				criteria.andPasswordLike("%"+seller.getPassword()+"%");
			}
			if(seller.getPhone()!=null && seller.getPhone().length()>0){
				criteria.andPhoneLike("%"+seller.getPhone()+"%");
			}
			if(seller.getEmail()!=null && seller.getEmail().length()>0){
				criteria.andEmailLike("%"+seller.getEmail()+"%");
			}
			if(seller.getAddress()!=null && seller.getAddress().length()>0){
				criteria.andAddressLike("%"+seller.getAddress()+"%");
			}
			if(seller.getLatitude()!=null && seller.getLatitude().length()>0){
				criteria.andLatitudeLike("%"+seller.getLatitude()+"%");
			}
			if(seller.getLongitude()!=null && seller.getLongitude().length()>0){
				criteria.andLongitudeLike("%"+seller.getLongitude()+"%");
			}
			if(seller.getImages()!=null && seller.getImages().length()>0){
				criteria.andImagesLike("%"+seller.getImages()+"%");
			}
			if(seller.getStatus()!=null && seller.getStatus().length()>0){
				criteria.andStatusLike("%"+seller.getStatus()+"%");
			}

		}

		Page<Seller> page= (Page<Seller>)sellerMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 激活
	 * @param Id
	 * @param activeCode
	 * @return
	 */
	public Result activate(Integer Id, String activeCode){
		if(redisTemplate.boundHashOps("sellerActiveCode").get(Id)==null||redisTemplate.boundHashOps("activeCode").get(Id)==""){
			return new Result(false,"你已激活账户，请勿重复激活");
		}
		String redisCode=redisTemplate.boundHashOps("sellerActiveCode").get(Id).toString();
		if (redisCode.equals(activeCode)){
			Seller seller=sellerMapper.selectByPrimaryKey(Id);
			seller.setStatus("1");
			sellerMapper.updateByPrimaryKey(seller);

			redisTemplate.boundHashOps("sellerActiveCode").delete(Id);
			System.out.println(Id+" 已激活");
			return new Result(true,"激活成功");
		}else{
			return new Result(false,"非法操作");
		}

	}

	@Override
	public Seller findByUsernameAndPassword(String username, String password) {
		SellerExample example=new SellerExample();
		SellerExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		criteria.andStatusEqualTo("2");
		List<Seller> sellers = sellerMapper.selectByExample(example);
		if(sellers!=null&&sellers.size()>0){
			return sellers.get(0);
		}else {
			return null;
		}
	}

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public void addGoods(Goods goods, Integer sellerId) {
		goods.setSellerId(sellerId);
		goods.setCreateTime(new Date());
		goods.setStatus("0");
		goodsMapper.insert(goods);
	}

	@Override
	public void updateGoods(Goods goods, Integer sellerId) {
		Goods g1 = goodsMapper.selectByPrimaryKey(goods.getId());
		if(g1.getSellerId().equals(sellerId)) {
			goods.setUpdateTime(new Date());
			goodsMapper.updateByPrimaryKey(goods);
		}else{
			throw new RuntimeException("非法操作");
		}
	}

	@Override
	public void deleGoods(Integer[] goodsIds, Integer sellerId) {
		for(Integer id:goodsIds){
			Goods goods = goodsMapper.selectByPrimaryKey(id);
			if(goods.getSellerId().equals(sellerId)){
				goodsMapper.deleteByPrimaryKey(id);
			}else{
				throw new RuntimeException("非法操作");
			}
		}
	}

	@Override
	public void updateGoodsStatus(Integer[] goodsIds, String status, Integer sellerId) {
		for(Integer id:goodsIds){
			Goods goods = goodsMapper.selectByPrimaryKey(id);
			if(goods.getSellerId().equals(sellerId)){
				goods.setStatus(status);
				goodsMapper.updateByPrimaryKey(goods);
			}else{
				throw new RuntimeException("非法操作");
			}
		}
	}

	/**
	 * 注销店铺
	 * @param sellerId
	 */
	@Override
	public void logoutSeller(Integer sellerId) {
		Seller seller = sellerMapper.selectByPrimaryKey(sellerId);
		seller.setStatus("4");
		sellerMapper.updateByPrimaryKey(seller);
	}

	@Override
	public PageResult getGoodsByPage(Integer sellerId, String status, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria = example.createCriteria();
		criteria.andSellerIdEqualTo(sellerId);
		if(status!=null&&status!=""){
			criteria.andStatusEqualTo(status);
		}
		Page<Goods> page = (Page<Goods>)goodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public void updateSellerStatus(Integer[] sellerIds, String status) {
		if(sellerIds==null||sellerIds.length==0){
			return ;
		}
		for(Integer id:sellerIds){
			Seller seller = sellerMapper.selectByPrimaryKey(id);
			seller.setStatus(status);
			sellerMapper.updateByPrimaryKey(seller);
		}
	}

	/*@Override
	public PageResult getGoodsByPage(Integer sellerId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		GoodsExample example=new GoodsExample();
		GoodsExample.Criteria criteria = example.createCriteria();
		criteria.andSellerIdEqualTo(sellerId);
		criteria.andStatusEqualTo("3");
		Page<Goods> page=(Page<Goods>)goodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getResult());
	}*/

}
