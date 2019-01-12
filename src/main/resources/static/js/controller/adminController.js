 //控制层 
app.controller('adminController' ,function($scope,$controller   ,adminService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		adminService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		adminService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		adminService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=adminService.update( $scope.entity ); //修改  
		}else{
			serviceObject=adminService.add( $scope.entity  );//增加 
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
		adminService.dele( $scope.selectIds ).success(
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
		adminService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

	/*//上传图片
	$scope.upload=function(){
		uploadService.uploadFile().success(function(response){
			$scope.entity.pic=response.message;
		})
	}*/
	$scope.admin=null;
	//登录
	$scope.login=function () {
		adminService.login($scope.admin.name,$scope.admin.password).success(function (res) {
			if(res.id!=null&&res.id!=""){
				$scope.admin2=res;
				location.href='/admin/index.html';
			}else{
				alert("登录失败");
			}
		})
	}

	//退出登录
	$scope.logout=function(){
		adminService.logout().success(function(res){
			if(res.success){
				$scope.admin="";
				location.href='/login.html';
			}else{
				alert("退出失败");
			}
		})
	}

	//获取登录yongh
	$scope.getUserName=function () {
		adminService.getUserName().success(function (res) {
			$scope.userName=res;
		})
	}
});	
