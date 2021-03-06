package cdtu.wheretobuy.service;

import cdtu.wheretobuy.pojo.Comment;
import cdtu.wheretobuy.pojo.PageResult;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CommentService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Comment> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Comment comment);
	
	
	/**
	 * 修改
	 */
	public void update(Comment comment);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Comment findOne(Integer id);
	
	
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
	public PageResult findPage(Comment comment, int pageNum, int pageSize);

	/**
	 * 商品评论
	 * @param sellerId
	 * @return
	 */
	public List<Comment> getCommentList(Integer goodsId);
	
}
