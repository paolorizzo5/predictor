app
	.factory('LeagueService', 
			function LeagueService($http){
	
		return{
			list: function (serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/LeagueService/list/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime()
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        }
		   
		};
	});
