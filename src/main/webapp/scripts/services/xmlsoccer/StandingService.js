app
	.factory('StandingService', 
			function StandingService($http){
	
		return{
			getStandingByLeagueAndSeason: function (league,season,serviceUrl){
		    	var url = serviceUrl + "/rest/StandingService/getStandingByLeagueAndSeason/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'league': league,
	    			'season': season
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        }
		   
		};
	});
