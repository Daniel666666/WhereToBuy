package cdtu.wheretobuy.service;
import java.util.List;
import cdtu.wheretobuy.pojo.GoodsType;

import cdtu.wheretobuy.pojo.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsTypeService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<GoodsType> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(GoodsType type);
	
	
	/**
	 * 修改
	 */
	public void update(GoodsType type);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public GoodsType findOne(Integer id);
	
	
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
	public PageResult findPage(GoodsType type, int pageNum, int pageSize);

	/**
	 * 查询子分类
	 * @param PId 父id
	 * @return
	 */
	public PageResult selectByPId(Integer PId,int pageNum, int pageSize);
	
}
