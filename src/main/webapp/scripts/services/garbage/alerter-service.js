app
	.factory('AlerterService', 
			function AlerterService($http){
	
		return{
			list: function (serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/AlerterService/list/" + new Date().getTime());
		    }
		};
	});
