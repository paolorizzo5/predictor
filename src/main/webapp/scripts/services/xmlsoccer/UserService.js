app.factory('UserService', function UserService($http, $location) {

	return {
		signup : function(email, serviceUrl) {
			var url = serviceUrl + "/rest/UserService/signup/";
	    	var fd = {
    			'uniquecallid': new Date().getTime(),
    			'email': email
    			
			};
	    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
		},
		login : function(email, password, serviceUrl) {
			
			var url = serviceUrl + "/rest/UserService/login/";
			//var pwd = CryptoJS.MD5(password);
	    	var fd = {
    			'uniquecallid': new Date().getTime(),
    			'email': email,
    			'password' : password
    			
			};
	    	return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
			
		},
		register : function(username, serviceUrl) {
			return $http.get(serviceUrl + "/rest/UserService/register/"
					+ username + "&" + new Date().getTime());
		}
	};
});
