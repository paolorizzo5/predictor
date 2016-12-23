'use strict';

/**
 * @ngdoc function
 * @name minovateApp.controller:PagesLoginCtrl
 * @description
 * # PagesLoginCtrl
 * Controller of the minovateApp
 */
app
  .controller('LoginCtrl',['$rootScope','$scope','$state','$window', 'UserService',  function ($rootScope, $scope, $state, $window, UserService) {

    $scope.login = function(email, password) {
    	UserService.login(email, password, $scope.configuration.serviceUrl).then(function(d) {
    		if (d.data != null){
				$window.sessionStorage.setItem("userDto", angular.toJson(d.data.userDto));
				$state.go('app.dashboard');
			}else{
				console.log("login failed");
				$scope.errorMessage = "LOGIN.ERROR";
			}
    	}); 
    };
    
    $scope.signup = function(email) {
    	UserService.register(email, $scope.configuration.serviceUrl).then(function(d) {
    		if (d.data != null){
				$window.sessionStorage.setItem("userDto", angular.toJson(d.data.userDto));
				$state.go('app.dashboard');
			}else{
				$scope.errorMessage = "LOGIN.ERROR";
			}
    	}); 
    };
    
    $scope.facebookLogin = function() {
		console.log('facebook login attempt');
		var _self = this;
		 FB.api('/me', function(res) {
			  console.log (res);
			});
    };
  }]);
