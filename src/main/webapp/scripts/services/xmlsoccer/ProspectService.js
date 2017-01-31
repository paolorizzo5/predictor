app
	.factory('ProspectService', 
			function ProspectService($http){
	
		return{
			add: function (name,initialAmount,duration,dailyPercentageExpected,accountName,email,serviceUrl){
				
		    	var url = "http://" + serviceUrl + "/rest/ProspectService/add/";
		    	console.log(url);
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'name': name,
	    			'initialAmount': initialAmount,
	    			'duration': duration,
	    			'dailyPercentageExpected': dailyPercentageExpected,
	    			'accountName': accountName,
	    			'email': email
	    			
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        get: function (accountName,email,serviceUrl){
				
		    	var url = "http://" + serviceUrl + "/rest/ProspectService/get/";
		    	console.log(url);
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'accountName': accountName,
	    			'email': email
	    			
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        pushProspectElement: function (prospectName,incremental,accountName,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/ProspectService/pushProspectElement/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'prospectName': prospectName,
	    			'incremental': incremental,
	    			'email': email,
	    			'accountName': accountName
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        popProspectElement: function (prospectName,incremental,accountName,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/ProspectService/popProspectElement/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'prospectName': prospectName,
	    			'incremental': incremental,
	    			'email': email,
	    			'accountName': accountName
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
		};
	});
