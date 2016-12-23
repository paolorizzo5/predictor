'use strict';


app
  .controller('LeagueController',['$rootScope','$scope','$state','$stateParams','$window', '$filter','$location', 'LeagueService', function ($rootScope, $scope, $state, $stateParams,$window,$filter,$location,LeagueService) {
  		LeagueService.list($scope.configuration.serviceUrl).then(function(d) {
			if (d.data != null){
				$scope.leagues = d.data.fixtureDtos;
			}else{
				
			}
		}); 
		
    
  }]);
