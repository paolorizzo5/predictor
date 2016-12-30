app
	.factory('FixtureService', 
			function FixtureService($http){
	
		return{
			getFixturesByLeagueAndSeason: function (league,season,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/FixtureService/getFixturesByLeagueAndSeason/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'league': league,
	    			'season': season
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
		
			getDailyFixtures: function (serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/FixtureService/getDailyFixtures/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime()
				};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        getLivescores: function (serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/FixtureService/getLivescores/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime()
				};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        }
		   
		};
	});
