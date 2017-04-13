app
	.factory('BetTypeService', 
			function BetTypeService($http){
	
		return{
			list: function (serviceUrl){
		    	var url = serviceUrl + "/rest/BetTypeService/list/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime()
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        }
		   
		};
	});
