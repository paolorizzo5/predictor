'use strict';

/**
 * @ngdoc function
 * @name minovateApp.controller:NavCtrl
 * @description # NavCtrl Controller of the minovateApp
 */
app.controller('NavCtrl',['$rootScope','$scope','$window','$location','LeagueService', 'AccountService',
				 function ($rootScope,$scope, $window,$location,LeagueService,AccountService) {
	
	$scope.status = {
			isFirstOpen : true	
	}
	
	if ($scope.leagues == null) {
		LeagueService.list($scope.configuration.serviceUrl).then(function(d) {
			if (d.data != null) {
				$scope.leagues = angular.fromJson(d.data.leagues);
			} else {
			}
		});
	}
	
	if ($scope.accounts == null) {
		AccountService.list(angular.fromJson($window.sessionStorage.getItem("userDto")).email,$scope.configuration.serviceUrl).then(function(d) {
			if (d.data != null) {
				console.log("user accounts");
				console.log(d.data);
				$scope.accounts = angular.fromJson(d.data.accounts);
			} else {
			}
		});
	}
	
	$scope.selectCompetition = function(id){
		$state.go("second", { id: $scope.userInput });
    };
    
    $scope.$on("onAccountCreated", function(){
		AccountService.list(angular.fromJson($window.sessionStorage.getItem("userDto")).email,$scope.configuration.serviceUrl).then(function(d) {
			if (d.data != null) {
				$scope.accounts = angular.fromJson(d.data.accounts);
			} else {
			}
		});
    });
    
    $scope.$on("onAccountDeleted", function(){
		AccountService.list(angular.fromJson($window.sessionStorage.getItem("userDto")).email,$scope.configuration.serviceUrl).then(function(d) {
			if (d.data != null) {
				$scope.accounts = angular.fromJson(d.data.accounts);
				$state.go("app.accountAdd");
			} else {
			}
		});
    });
}]);
