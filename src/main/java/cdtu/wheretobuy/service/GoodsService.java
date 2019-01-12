package cdtu.wheretobuy.service;
import java.util.List;
import cdtu.wheretobuy.pojo.Goods;

import cdtu.wheretobuy.pojo.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Goods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Goods goods);
	
	
	/**
	 * 修改
	 */
	public void update(Goods goods);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Goods findOne(Integer id);
	
	
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
	public PageResult findPage(Goods goods, int pageNum, int pageSize);

	/**
	 * 更新商品状态 审核。。。
	 * @param goodsIds
	 * @param status
	 */
	public void updateGoodsStatus(Integer[] goodsIds,String status);

    List<Goods> newGoods();

	PageResult userSearchByPage(String keywords,int pageNum,int pageSize,String orderBy);
}
