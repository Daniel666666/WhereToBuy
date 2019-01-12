package cdtu.wheretobuy.service.impl;
import java.util.List;

import cdtu.wheretobuy.mapper.CommentMapper;
import cdtu.wheretobuy.pojo.Comment;
import cdtu.wheretobuy.pojo.CommentExample;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Comment> findAll() {
		return commentMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Comment> page=   (Page<Comment>) commentMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Comment comment) {
		commentMapper.insert(comment);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Comment comment){
		commentMapper.updateByPrimaryKey(comment);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Comment findOne(Integer id){
		return commentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			commentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Comment comment, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		CommentExample example=new CommentExample();
		CommentExample.Criteria criteria = example.createCriteria();
		
		if(comment!=null){			
						if(comment.getContent()!=null && comment.getContent().length()>0){
				criteria.andContentLike("%"+comment.getContent()+"%");
			}
			if(comment.getGoodsId()!=null && comment.getGoodsId().length()>0){
				criteria.andGoodsIdLike("%"+comment.getGoodsId()+"%");
			}
	
		}
		
		Page<Comment> page= (Page<Comment>)commentMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Comment> getCommentList(Integer goodsId) {
		CommentExample example=new CommentExample();
		CommentExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId+"");
		return commentMapper.selectByExample(example);
	}

}
