'use strict';

/**
 * @ngdoc function
 * @name minovateApp.controller:NavCtrl
 * @description # NavCtrl Controller of the minovateApp
 */
app.controller('NavCtrl',['$rootScope','$scope','$window','LeagueService', 
				 function ($rootScope,$scope, $window,LeagueService) {
	

	if ($scope.leagues == null) {
		LeagueService.list($scope.configuration.serviceUrl).then(function(d) {
			if (d.data != null) {
				$scope.leagues = angular.fromJson(d.data.leagues);
			} else {
			}
		});
	}
	
	
	$scope.selectCompetition = function(id){
		$state.go("second", { id: $scope.userInput });
    };
}]);
