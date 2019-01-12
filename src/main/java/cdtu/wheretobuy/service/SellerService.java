package cdtu.wheretobuy.service;
import java.util.List;

import cdtu.wheretobuy.pojo.Goods;
import cdtu.wheretobuy.pojo.Result;
import cdtu.wheretobuy.pojo.Seller;

import cdtu.wheretobuy.pojo.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SellerService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Seller> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Seller seller);
	
	
	/**
	 * 修改
	 */
	public void update(Seller seller);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Seller findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(Seller seller, int pageNum, int pageSize);

	/**
	 * 激活
	 * @param Id
	 * @param activeCode
	 * @return
	 */
	public Result activate(Integer Id,String activeCode);

	/**
	 * 登录查询
	 * @param username
	 * @param password
	 * @return
	 */
	public Seller findByUsernameAndPassword(String username,String password);

	/**
	 * 获取商家所属商品
	 * @param sellerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	/*public PageResult getGoodsByPage(Integer sellerId,int pageNum,int pageSize);*/


	public void addGoods(Goods goods, Integer sellerId);

	public void updateGoods(Goods goods,Integer sellerId);

	public void deleGoods(Integer[] goodsIds,Integer sellerId);

	public void updateGoodsStatus(Integer[] goodsIds,String status,Integer sellerId);

	/**
	 * 注销店铺
	 * @param sellerId
	 */
	public void logoutSeller(Integer sellerId);

	/**
	 * 根据状态分页查询商家商品
	 * @param sellerId
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult getGoodsByPage(Integer sellerId,String status,int pageNum,int pageSize);

	/**
	 *更新商家状态 通过审核。。。
	 * @param sellerIds
	 * @param status
	 * @return
	 */
	public void updateSellerStatus(Integer[] sellerIds,String status);

	
}
