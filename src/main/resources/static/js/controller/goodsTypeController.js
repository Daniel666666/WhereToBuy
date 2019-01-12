 //控制层 
app.controller('typeController' ,function($scope,$controller   ,goodsTypeService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsTypeService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		/*goodsTypeService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}
		);*/
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		goodsTypeService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=goodsTypeService.update( $scope.entity ); //修改  
		}else{
			$scope.entity.parentId=$scope.PId;
			serviceObject=goodsTypeService.add( $scope.entity  );//增加 
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
		goodsTypeService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	$scope.PId=0;
	//搜索
	$scope.search=function(page,rows){			
		/*goodsTypeService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);*/

		/*goodsTypeService.selectByPId($scope.PId,page,rows).success(
			function(response){
				$scope.list=response.rows;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}
		);*/

		$scope.selectByPId();

	}

	//查询子分类
	$scope.selectByPId=function () {
		goodsTypeService.selectByPId($scope.PId,$scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).success(
			function(response){
				$scope.list=response.rows;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}
		);
	}

	$scope.changePId=function (PId) {
		$scope.PId=PId;
	}

	$scope.MBX="{}";
	$scope.updateMBX=function (ent) {
		$scope.MBX=ent;
	}
    
});	
