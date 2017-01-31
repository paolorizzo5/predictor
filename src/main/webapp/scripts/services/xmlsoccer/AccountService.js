app
	.factory('AccountService', 
			function AccountService($http){
	
		return{
			add: function (name,description,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/add/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'name': name,
	    			'description': description,
	    			'email': email
	    			
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        delete: function (name,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/delete/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'name': name,
	    			'email': email
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        get: function (name,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/get/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'name': name,
	    			'email': email
    			};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
		
			list: function (email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/list/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'email': email
				};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
	        deposit: function (accountName,amount,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/deposit/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'accountName':accountName,
	    			'amount':amount,
	    			'email': email
				};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        withdraw: function (accountName,amount,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/withdraw/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'accountName':accountName,
	    			'amount':amount,
	    			'email': email
				};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        addBet: function (eventDescription,bettypeDescription,amount,moltiplicator,accountName,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/addBet/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'eventDescription': eventDescription,
	    			'bettypeDescription': bettypeDescription,
	    			'amount': amount,
	    			'moltiplicator': moltiplicator,
	    			'email': email,
	    			'accountName': accountName
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        markBet: function (insertDate,isWinning,accountName,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/markBet/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'insertDate': insertDate,
	    			'isWinning': isWinning,
	    			'email': email,
	    			'accountName': accountName
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        archiveBet: function (insertDate,accountName,email,serviceUrl){
		    	var url = "http://" + serviceUrl + "/rest/AccountService/archiveBet/";
		    	var fd = {
	    			'uniquecallid': new Date().getTime(),
	    			'insertDate': insertDate,
	    			'email': email,
	    			'accountName': accountName
	    		};
		    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	        },
	        
		};
	});
