package cdtu.wheretobuy.controller;

import cdtu.wheretobuy.pojo.Collection;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.pojo.Result;
import cdtu.wheretobuy.service.CollectionService;
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
@RequestMapping("/collection")
public class CollectionController {

	@Autowired
	private CollectionService collectionService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Collection> findAll(){
		return collectionService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return collectionService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param collection
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Collection collection){
		try {
			collectionService.add(collection);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param collection
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Collection collection){
		try {
			collectionService.update(collection);
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
	public Collection findOne(Integer id){
		return collectionService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			collectionService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody Collection collection, int page, int rows  ){
		return collectionService.findPage(collection, page, rows);		
	}
	
}
