 //控制层 
app.controller('goodsController' ,function($scope,$controller   ,goodsService,sellerService,goodsTypeService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){
		if($scope.selectIds==[]||$scope.selectIds==""||$scope.selectIds==null){
			alert("请选择要审核的内容");
			return;
		}
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

	//审核通过
	$scope.checkPass=function () {
		if($scope.selectIds==[]||$scope.selectIds==""||$scope.selectIds==null){
			alert("请选择要审核的内容");
			return;
		}
		goodsService.checkPass($scope.selectIds).success(function (response) {
			if(response.success){
				$scope.reloadList();//刷新列表
				$scope.selectIds=[];
			}
			alert(response.message);
		})
	}

	//审核不通过
	$scope.unCheckPass=function () {
		if($scope.selectIds==[]||$scope.selectIds==""||$scope.selectIds==null){
			alert("请选择要审核的内容");
			return;
		}
		goodsService.unCheckPass($scope.selectIds).success(function (response) {
			if(response.success){
				$scope.reloadList();//刷新列表
				$scope.selectIds=[];
			}
			alert(response.message);
		})
	}

	//获取状态
	$scope.getStatus=function(status){
		switch (status) {
			case "0":return "未审核";
			case "1":return "已审核";
			case "2":return "未通过";
			case "3":return "已上架";
			case "4":return "已下架";
			default:return "";
		}
	}

	$scope.sellerList=[];
	//获取商家列表
	$scope.getSellerList=function () {
		sellerService.findAll().success(function (response) {
			for(var i=0;i<response.length;i++){
				$scope.sellerList[response[i].id]=response[i].name;
			}

		})
	}

	$scope.goodsTypeList=[];
	//获取商家列表
	$scope.getGoodsTypeList=function () {
		goodsTypeService.findAll().success(function (response) {
			for(var i=0;i<response.length;i++){
				$scope.goodsTypeList[response[i].id]=response[i].name;
			}
		})
	}


    
});	
