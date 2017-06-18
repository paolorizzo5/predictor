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
	        
	        getMasanielloPlans: function (serviceUrl,email){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/getMasanielloPlans/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'email': email
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        getPlanDetail: function (serviceUrl,email,name){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/getPlanDetail/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'email': email,
	    			'name': name
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        getPlansNames: function (serviceUrl,email){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/getPlansNames/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'email': email
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        deleteMasanielloPlan: function (serviceUrl,email,name){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/deleteMasanielloPlan/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'email': email,
	    			'name': name
	    			
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        getMasanielloRounds: function (serviceUrl,planName,name,email,id){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/getMasanielloRounds/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'email': email,
	    			'name': name,
	    			'planName': planName,
	    			'id': id
	    			
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
		
			getDirettaFixtures: function (serviceUrl,competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,fullSearch,dateFrom,dateTo){
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
	    			'awayTeam': awayTeam,
	    			'fullSearch': fullSearch,
	    			'dateFrom': dateFrom,
	    			'dateTo': dateTo
	    			
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        createMasaniello: function (serviceUrl,filters,email,masanielloName,masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloAverageQuote,masanielloAdditionalQuote,masanielloPercentage,patrimonyPercentage,id){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/createMasaniello/";
		    	console.log ("createMasaniello: id: " + id);
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'filtersBase': filters,
	    			'masanielloName': masanielloName,
	    			'masanielloAmount': masanielloAmount,
	    			'masanielloRounds': masanielloRounds,
	    			'masanielloEventToWin': masanielloEventToWin,
	    			'masanielloAverageQuote':masanielloAverageQuote,
	    			'masanielloAdditionalQuote':masanielloAdditionalQuote,
	    			'masanielloPercentage': masanielloPercentage,
	    			'patrimonyPercentage': patrimonyPercentage,
	    			'masanielloUserEmail': email,
	    			'id': id
	    			
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        createPlan: function (serviceUrl,filters,email,masanielloName,masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloAverageQuote,masanielloAdditionalQuote,masanielloPercentage,patrimonyPercentage){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/createPlan/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'filtersBase': filters,
	    			'masanielloName': masanielloName,
	    			'masanielloAmount': masanielloAmount,
	    			'masanielloRounds': masanielloRounds,
	    			'masanielloEventToWin': masanielloEventToWin,
	    			'masanielloAverageQuote':masanielloAverageQuote,
	    			'masanielloAdditionalQuote':masanielloAdditionalQuote,
	    			'masanielloPercentage': masanielloPercentage,
	    			'patrimonyPercentage': patrimonyPercentage,
	    			'masanielloUserEmail': email
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        createPlanAdvanced: function (serviceUrl,filters,email,masanielloName,masanielloAmount,masanielloRounds,patrimonyPercentage){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/createPlanAdvanced/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'filtersAdvanced': filters,
	    			'masanielloName': masanielloName,
	    			'masanielloAmount': masanielloAmount,
	    			'masanielloRounds': masanielloRounds,
	    			'patrimonyPercentage': patrimonyPercentage,
	    			'masanielloUserEmail': email
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        createMasanielloAdvanced: function (serviceUrl,filters,email,masanielloName,masanielloAmount,masanielloRounds,patrimonyPercentage,lowerByWin,raiseByLoss,id){
		    	var url = serviceUrl + "/rest/DirettaFixtureService/createMasanielloAdvanced/";
		    	console.log ("createMasaniello: id: " + id);
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'filtersAdvanced': filters,
	    			'masanielloName': masanielloName,
	    			'masanielloAmount': masanielloAmount,
	    			'masanielloRounds': masanielloRounds,
	    			'patrimonyPercentage': patrimonyPercentage,
	    			'lowerByWin': lowerByWin,
	    			'raiseByLoss': raiseByLoss,
	    			'masanielloUserEmail': email,
	    			'id': id
	    			
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
		   
		};
	});
