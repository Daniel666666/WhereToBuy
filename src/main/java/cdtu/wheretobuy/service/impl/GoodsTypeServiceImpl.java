package cdtu.wheretobuy.service.impl;

import cdtu.wheretobuy.mapper.GoodsTypeMapper;
import cdtu.wheretobuy.pojo.GoodsType;
import cdtu.wheretobuy.pojo.GoodsTypeExample;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.service.GoodsTypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsTypeServiceImpl implements GoodsTypeService {

	@Autowired
	private GoodsTypeMapper typeMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<GoodsType> findAll() {
		return typeMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<GoodsType> page=   (Page<GoodsType>) typeMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(GoodsType type) {
		typeMapper.insert(type);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(GoodsType type){
		typeMapper.updateByPrimaryKey(type);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public GoodsType findOne(Integer id){
		return typeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			typeMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(GoodsType type, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		GoodsTypeExample example=new GoodsTypeExample();
		GoodsTypeExample.Criteria criteria = example.createCriteria();
		
		if(type!=null){			
						if(type.getName()!=null && type.getName().length()>0){
				criteria.andNameLike("%"+type.getName()+"%");
			}
	
		}
		
		Page<GoodsType> page= (Page<GoodsType>)typeMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public PageResult selectByPId(Integer PId,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		GoodsTypeExample example=new GoodsTypeExample();
		GoodsTypeExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(PId);
		Page<GoodsType> page= (Page<GoodsType>)typeMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

}
