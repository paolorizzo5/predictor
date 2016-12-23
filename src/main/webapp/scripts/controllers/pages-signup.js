'use strict';

/**
 * @ngdoc function
 * @name minovateApp.controller:PagesSignupCtrl
 * @description
 * # PagesSignupCtrl
 * Controller of the minovateApp
 */
app
  .controller('SignupCtrl',['$scope', 'UserService', function ($scope,UserService) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    
    $scope.signup = function(email) {
    	UserService.signup(email,$scope.configuration.serviceUrl).then(function(d) {
    		console.log(d.data);
    		if(d.data != null){
    			$scope.message = "An email has been sent to " + email + " please follow instructions in order to complete your registration";
    			$scope.user.email = "";
    		}
    		
    	});
    };
    
  }]);
