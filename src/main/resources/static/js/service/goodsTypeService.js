//服务层
app.service('goodsTypeService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../type/findAll');
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../type/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../type/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../type/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../type/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../type/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../type/search?page='+page+"&rows="+rows, searchEntity);
	}

	//查询子分类
	this.selectByPId=function(PId,page,rows){
		return $http.get('../type/selectByPId?page='+page+'&rows='+rows+'&PId='+PId);
	}
});
