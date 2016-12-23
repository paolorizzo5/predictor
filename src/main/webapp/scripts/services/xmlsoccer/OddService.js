app
	.factory('OddService', 
			function OddService($http){
	
		return{
			getOddsByFixtureId: function (id,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/OddService/getOddsByFixtureId/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'id': id
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        }
		   
		};
	});
