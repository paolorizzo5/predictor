app
	.factory('AreaService', 
			function AreaService($http){
	
		return{
			list: function (serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/AreaService/list/" + new Date().getTime());
		    },
			addArea: function (id,name,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/AreaService/add-area/" + id + "&" + name + "&" + new Date().getTime());
		    },
		    deleteArea: function (id,name,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/AreaService/delete-area/" + id + "&" + name + "&" + new Date().getTime());
		    }
		   
		};
	});
