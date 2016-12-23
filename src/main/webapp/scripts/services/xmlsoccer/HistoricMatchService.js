app
	.factory('HistoricMatchService', 
			function HistoricMatchService($http){
	
		return{
			getHistoricMatchesByLeagueAndSeason: function (league,season,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/HistoricMatchService/getHistoricMatchesByLeagueAndSeason/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'league': league,
	    			'season': season
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        getFixturePreview: function (homeTeamId,awayTeamId,league,season,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/HistoricMatchService/getFixturePreview/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'homeTeamId': homeTeamId,
	    			'awayTeamId': awayTeamId,
	    			'league': league,
	    			'season': season
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        }
		   
		};
	});
