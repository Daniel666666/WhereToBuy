package cdtu.wheretobuy.controller;

import cdtu.wheretobuy.pojo.GoodsType;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.pojo.Result;
import cdtu.wheretobuy.service.GoodsTypeService;
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
@RequestMapping("/type")
@CrossOrigin
public class GoodsTypeController {

	@Autowired
	private GoodsTypeService goodsTypeService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<GoodsType> findAll(){
		return goodsTypeService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return goodsTypeService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param type
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody GoodsType type){
		try {
			goodsTypeService.add(type);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param type
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody GoodsType type){
		try {
			goodsTypeService.update(type);
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
	public GoodsType findOne(Integer id){
		return goodsTypeService.findOne(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			goodsTypeService.delete(ids);
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
	public PageResult search(@RequestBody GoodsType type, int page, int rows  ){
		return goodsTypeService.findPage(type, page, rows);
	}

	@RequestMapping("/selectByPId")
	public PageResult selectByPId(Integer PId,int page, int rows){
		return goodsTypeService.selectByPId(PId,page,rows);
	}
	
}
