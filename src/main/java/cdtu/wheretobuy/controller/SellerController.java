package cdtu.wheretobuy.controller;

import cdtu.wheretobuy.pojo.Goods;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.pojo.Result;
import cdtu.wheretobuy.pojo.Seller;
import cdtu.wheretobuy.service.SellerService;
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
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Seller> findAll(){
		return sellerService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return sellerService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param seller
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Seller seller){
		try{
			sellerService.add(seller);
			return new Result(true, "增加成功");
		}catch (Exception e){
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param seller
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Seller seller){
		try{
			sellerService.update(seller);
			return new Result(true, "修改成功");
		}catch (Exception e){
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
	public Seller findOne(Integer id){
		return sellerService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			sellerService.delete(ids);
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
	public PageResult search(@RequestBody Seller seller, int page, int rows  ){
		return sellerService.findPage(seller, page, rows);		
	}

	/**
	 * 商家注册
	 * @param seller
	 * @return
	 */
	@RequestMapping("/regist")
	public Result regist(@RequestBody Seller seller){
		try{
			sellerService.add(seller);
			return new Result(true,"注册成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"注册失败");
		}
	}

	/**
	 * 商家激活
	 * @param Id
	 * @param activeCode
	 * @return
	 */
	@RequestMapping("/activate")
	public Result activate(Integer Id,String activeCode){
		return sellerService.activate(Id,activeCode);
	}

	/**
	 * 商家登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public Seller login(String username,String password){
		Seller seller = sellerService.findByUsernameAndPassword(username, password);
		return seller;
	}

	/*@RequestMapping("/getGoodsByPage")
	public PageResult getGoodsByPage(Integer sellerId,int pageNum,int pageSize){

	}*/

	/**
	 * 添加商品
	 * @param goods
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/addGoods")
	public Result addGoods(@RequestBody Goods goods,Integer sellerId){
		try{
			sellerService.addGoods(goods,sellerId);
			return new Result(true,"添加商品成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"添加商品失败");
		}
	}

	/**
	 * 更新商品
	 * @param goods
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/updateGoods")
	public Result updateGoods(Goods goods,Integer sellerId){
		try{
			sellerService.updateGoods(goods,sellerId);
			return new Result(true,"修改商品成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"修改商品失败");
		}
	}

	/**
	 * 删除商品
	 * @param goodsIds
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/deleGoods")
	public Result deleGoods(Integer[] goodsIds,Integer sellerId){
		try{
			sellerService.deleGoods(goodsIds,sellerId);
			return new Result(true,"删除成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"删除失败");
		}
	}

	/**
	 * 商品上架
	 * @param goodsIds
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/shelves")
	public Result shelves(Integer[] goodsIds,Integer sellerId){
		try{
			sellerService.updateGoodsStatus(goodsIds,"3",sellerId);
			return new Result(true,"上架成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"上架失败");
		}
	}

	/**
	 * 商品下架
	 * @param goodsIds
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/outshelves")
	public Result outshelves(Integer[] goodsIds,Integer sellerId){
		try{
			sellerService.updateGoodsStatus(goodsIds,"4",sellerId);
			return new Result(true,"下架成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"下架失败");
		}
	}

	/**
	 * 注销店铺
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/logoutSeller")
	public Result logoutSeller(Integer sellerId){
		try{
			sellerService.logoutSeller(sellerId);
			return new Result(true,"店铺注销成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"店铺注销失败");
		}
	}

	/**
	 * 获取所有商品
	 * @param sellerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getAllGoodsByPage")
	public PageResult getAllGoodsByPage(Integer sellerId,int pageNum,int pageSize){
		return  sellerService.getGoodsByPage(sellerId,"",pageNum,pageSize);
	}

	/**
	 * 获取未审核商品
	 * @param sellerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getUnCheckedGoodsByPage")
	public PageResult getUnCheckedGoodsByPage(Integer sellerId,int pageNum,int pageSize){
		return  sellerService.getGoodsByPage(sellerId,"0",pageNum,pageSize);
	}

	/**
	 * 获取审核已通过商品
	 * @param sellerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getCheckedGoodsByPage")
	public PageResult getCheckedGoodsByPage(Integer sellerId,int pageNum,int pageSize){
		return  sellerService.getGoodsByPage(sellerId,"1",pageNum,pageSize);
	}

	/**
	 * 获取审核未通过商品
	 * @param sellerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getNoPassGoodsByPage")
	public PageResult getNoPassGoodsByPage(Integer sellerId,int pageNum,int pageSize){
		return  sellerService.getGoodsByPage(sellerId,"2",pageNum,pageSize);
	}

	/**
	 * 获取已上架商品
	 * @param sellerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getShelvesGoodsByPage")
	public PageResult getShelvesGoodsByPage(Integer sellerId,int pageNum,int pageSize){
		return  sellerService.getGoodsByPage(sellerId,"3",pageNum,pageSize);
	}

	/**
	 * 获取已下架商品
	 * @param sellerId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getOutShelvesGoodsByPage")
	public PageResult getOutShelvesGoodsByPage(Integer sellerId,int pageNum,int pageSize){
		return  sellerService.getGoodsByPage(sellerId,"4",pageNum,pageSize);
	}

	@RequestMapping("/checkPass")
	public Result checkPass(Integer[] ids){
		try{
			sellerService.updateSellerStatus(ids,"2");
			return new Result(true,"通过审核成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"通过审核失败");
		}
	}

	@RequestMapping("/unCheckPass")
	public Result UnCheckPass(Integer[] ids){
		try{
			sellerService.updateSellerStatus(ids,"3");
			return new Result(true,"不通过审核成功");
		}catch (Exception e){
			System.out.println(e.getMessage());
			return new Result(false,"不通过审核失败");
		}
	}
}
