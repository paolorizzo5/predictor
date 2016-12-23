'use strict';

/**
 * @ngdoc function
 * @name minovateApp.controller:PagesHeaderCtrl
 * @description
 * # PagesHeaderCtrl
 * Controller of the minovateApp
 */
app
  .controller('HeaderCtrl',['$rootScope','$scope','$state','$window', 'UserService',  function ($rootScope, $scope, $state, $window, UserService) {

	var userDto;
	if(typeof userDto === 'undefined' || user == null){
		userDto = angular.fromJson($window.sessionStorage.getItem("userDto"));
		if(typeof userDto === 'undefined' || userDto == null){
			console.log("session expired");
			$window.location.href = "#/core/login";
		}
		else{
			$scope.userDto = userDto;
		}
	}
    
  }]);
