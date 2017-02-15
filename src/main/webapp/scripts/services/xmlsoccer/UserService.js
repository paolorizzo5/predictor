app
	.factory('UserService', 
			function UserService($http,$location){
	
		return{
			signup: function (username,serviceUrl){
				return $http.get(serviceUrl + "/rest/UserService/signup/" + username + "&" + new Date().getTime());
		    },
		    login: function (username,password,serviceUrl){
		    	var passhash = CryptoJS.MD5(password);
    			return $http.get(serviceUrl + "/rest/UserService/login/" + username + "&" + passhash + "&" + new Date().getTime());
    	    },
    	    register: function (username,serviceUrl){
    	    	return $http.get(serviceUrl + "/rest/UserService/register/" + username + "&" + new Date().getTime());
    	    }
		};
	});
