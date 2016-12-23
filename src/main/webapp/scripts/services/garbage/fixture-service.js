app
	.factory('FixtureService', 
			function FixtureService($http){
	
		return{
			
			viewOdds: function (homeTeam,awayTeam,id,serviceUrl){
				
				var url = "http://" + serviceUrl + "/rest/FixtureService/view-odds/";
		    	var fd = {
		    			'homeTeam': homeTeam,
		    			'awayTeam': awayTeam,
		    			'id': id,
		    			'uniquecallid': new Date().getTime()
		    		  };
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	    	
				
				
				//return $http.get("http://" + serviceUrl + "/rest/FixtureService/view-odds/" + homeTeam + "&"  + awayTeam + "&"  + id + "&" + new Date().getTime());
		    },
		    
	    	getDailyFixtures : function (serviceUrl){
		    	
		    	var url = "http://" + serviceUrl + "/rest/FixtureService/get-daily-fixtures/";
		    	var fd = {
		    			'uniquecallid': new Date().getTime()
		    		  };
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	    	
		    },
		    
		    getCompetitionTimedFixtures : function (id,serviceUrl){
		    	
		    	var url = "http://" + serviceUrl + "/rest/FixtureService/get-competition-timed-fixtures/";
		    	var fd = {
		    			'id': id,
		    			'uniquecallid': new Date().getTime()
		    		  };
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	    	
		    },
		    
    		getCompetitionFinishedFixtures : function (id,serviceUrl){
		    	
		    	var url = "http://" + serviceUrl + "/rest/FixtureService/get-competition-finished-fixtures/";
		    	var fd = {
		    			'id': id,
		    			'uniquecallid': new Date().getTime()
		    		  };
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	    	
		    },
		    
			
		    addFixtures: function (id,file,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/FixtureService/add-fixtures/";
		    	var fd = {
	    		    uniquecallid: new Date().getTime()
	    		  }
		        
		        return $http.post(url, fd, {transformRequest: angular.identity,headers: {'Content-Type': undefined}});
	    	},
		    deleteCompetition: function (id,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/CompetitionService/delete-competition/" + id + "&" + new Date().getTime());
		    },
		    list: function (serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/CompetitionService/list/" + new Date().getTime());
		    },
		    findByCompetition: function (id,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/FixtureService/find-by-competition/" + id + "&" + new Date().getTime());
		    },

		};
	});
