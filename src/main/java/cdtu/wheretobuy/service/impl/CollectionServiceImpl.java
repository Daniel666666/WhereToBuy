package cdtu.wheretobuy.service.impl;
import java.util.List;

import cdtu.wheretobuy.mapper.CollectionMapper;
import cdtu.wheretobuy.pojo.Collection;
import cdtu.wheretobuy.pojo.CollectionExample;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.service.CollectionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

	@Autowired
	private CollectionMapper collectionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Collection> findAll() {
		return collectionMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Collection> page=   (Page<Collection>) collectionMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Collection collection) {
		collectionMapper.insert(collection);
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Collection collection){
		collectionMapper.updateByPrimaryKey(collection);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Collection findOne(Integer id){
		return collectionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			collectionMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Collection collection, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		CollectionExample example=new CollectionExample();
		CollectionExample.Criteria criteria = example.createCriteria();
		
		if(collection!=null){			
						if(collection.getType()!=null && collection.getType().length()>0){
				criteria.andTypeLike("%"+collection.getType()+"%");
			}
	
		}
		
		Page<Collection> page= (Page<Collection>) collectionMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
