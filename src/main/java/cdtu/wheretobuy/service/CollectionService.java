package cdtu.wheretobuy.service;
import java.util.List;
import cdtu.wheretobuy.pojo.Collection;

import cdtu.wheretobuy.pojo.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CollectionService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Collection> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Collection collection);
	
	
	/**
	 * 修改
	 */
	public void update(Collection collection);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Collection findOne(Integer id);
	
	
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
	public PageResult findPage(Collection collection, int pageNum, int pageSize);
	
}
