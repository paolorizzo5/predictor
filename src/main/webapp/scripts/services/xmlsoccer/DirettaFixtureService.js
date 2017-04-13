app
	.factory('DirettaFixtureService', 
			function DirettaFixtureService($http){
	
		return{
			getCompetitions: function (serviceUrl){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/getCompetitions/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime()
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
		
			getDirettaFixtures: function (serviceUrl,competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/getDirettaFixtures/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'competition': competition,
	    			'quota1From': quota1From,
	    			'quota1To': quota1To,
	    			'quotaXFrom': quotaXFrom,
	    			'quotaXTo': quotaXTo,
	    			'quota2From': quota2From,
	    			'quota2To': quota2To,
	    			'homeTeam': homeTeam,
	    			'awayTeam': awayTeam
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        createMasaniello: function (serviceUrl,competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,email,masanielloName,masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloEventType,masanielloAverageQuote,masanielloPercentage,patrimonyPercentage){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/createMasaniello/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'competition': competition,
	    			'quota1From': quota1From,
	    			'quota1To': quota1To,
	    			'quotaXFrom': quotaXFrom,
	    			'quotaXTo': quotaXTo,
	    			'quota2From': quota2From,
	    			'quota2To': quota2To,
	    			'homeTeam': homeTeam,
	    			'awayTeam': awayTeam,
	    			'masanielloName': masanielloName,
	    			'masanielloAmount': masanielloAmount,
	    			'masanielloRounds': masanielloRounds,
	    			'masanielloEventToWin': masanielloEventToWin,
	    			'masanielloEventType': masanielloEventType,
	    			'masanielloAverageQuote':masanielloAverageQuote,
	    			'masanielloPercentage': masanielloPercentage,
	    			'patrimonyPercentage': patrimonyPercentage,
	    			'masanielloUserEmail': email,
	    			
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        }
		   
		};
	});
