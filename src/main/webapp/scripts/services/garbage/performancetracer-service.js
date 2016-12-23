app
	.factory('PerformanceTracerService', 
			function PerformanceTracerService($http){
	
		return{
			list: function (serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/PerformanceTracerService/list/" + new Date().getTime());
		    }
		};
	});
