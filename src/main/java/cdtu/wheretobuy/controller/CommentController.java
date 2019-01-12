package cdtu.wheretobuy.controller;

import cdtu.wheretobuy.pojo.Comment;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.pojo.Result;
import cdtu.wheretobuy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Comment> findAll(){
		return commentService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return commentService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param comment
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Comment comment){
		try {
			commentService.add(comment);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param comment
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Comment comment){
		try {
			commentService.update(comment);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Comment findOne(Integer id){
		return commentService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			commentService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody Comment comment, int page, int rows  ){
		return commentService.findPage(comment, page, rows);		
	}

	@RequestMapping("/getCommentList")
	public List<Comment> getCommentList(Integer goodsId){
		return commentService.getCommentList(goodsId);
	}
	
}
