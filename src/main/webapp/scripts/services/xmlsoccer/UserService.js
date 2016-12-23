app
	.factory('UserService', 
			function UserService($http){
	
		return{
			signup: function (username,serviceUrl){
				console.log("UserService.signup");
				console.log("http://" + serviceUrl + "/rest/UserService/signup/" + username + "&" + new Date().getTime());
				return $http.get("http://" + serviceUrl + "/rest/UserService/signup/" + username + "&" + new Date().getTime());
		    },
		    login: function (username,password,serviceUrl){
    			var passhash = CryptoJS.MD5(password);
    			return $http.get("http://" + serviceUrl + "/rest/UserService/login/" + username + "&" + passhash + "&" + new Date().getTime());
    	    },
    	    register: function (username,serviceUrl){
    			return $http.get("http://" + serviceUrl + "/rest/UserService/register/" + username + "&" + new Date().getTime());
    	    }
		};
	});
