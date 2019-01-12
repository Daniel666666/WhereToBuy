package cdtu.wheretobuy.controller;

import cdtu.wheretobuy.pojo.Goods;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.pojo.Result;
import cdtu.wheretobuy.service.GoodsService;
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
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Goods> findAll(){
		return goodsService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return goodsService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param goods
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Goods goods){
		try {
			goodsService.add(goods);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Goods goods){
		try {
			goodsService.update(goods);
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
	public Goods findOne(Integer id){
		return goodsService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			goodsService.delete(ids);
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
	public PageResult search(@RequestBody Goods goods, int page, int rows  ){
		return goodsService.findPage(goods, page, rows);		
	}

	@RequestMapping("/checkPass")
	public Result checkPass(Integer[] ids){
		try{
			goodsService.updateGoodsStatus(ids,"1");
			return new Result(true,"通过审核成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"通过审核失败");
		}
	}

	@RequestMapping("/unCheckPass")
	public Result UnCheckPass(Integer[] ids){
		try{
			goodsService.updateGoodsStatus(ids,"2");
			return new Result(true,"不通过审核成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"不通过审核失败");
		}
	}

	@RequestMapping("/newGoods")
	public List<Goods> newGoods(){
		return goodsService.newGoods();
	}

	@RequestMapping("/userSearchByPage")
	public PageResult userSearchByPage(String keywords,int pageNum,int pageSize,String orderBy){
		return goodsService.userSearchByPage(keywords,pageNum,pageSize,orderBy);
	}

}
