'use strict';


app
  .controller('FixtureDailyCtrl',['$rootScope','$scope','$state','$stateParams','$window', '$filter','$location', 'FixtureService', function ($rootScope, $scope, $state, $stateParams,$window,$filter,$location,FixtureService) {
  
    FixtureService.getDailyFixtures($scope.configuration.serviceUrl).then(function(d) {
		if (d.data != null){
			$scope.fixtureDtos = d.data.fixtureDtos;
		}else{
			
		}
	}); 
    
    $scope.bets = [];
    
//    $scope.$on('addBet', function(event, data) { 
//    	
//    	$scope.bets.push(data);
//    	
//    	
//	});
    
    $scope.viewDetail = false;
    
    $scope.viewOdds = function(fixtureDto){
    	console.log("competitionId " + fixtureDto.competitionId);
    	FixtureService.viewOdds(fixtureDto.homeTeamCode,fixtureDto.awayTeamCode,fixtureDto.competitionId,$scope.configuration.serviceUrl).then(function(d) {
    		if (d.data != null){
    			$scope.detail = d.data;
    			$scope.viewDetail = true;
			}else{
				
			}
    	}); 
    };
    
    $scope.backToFixtures = function(fixtureDto){
    	$scope.viewDetail = false;
    };
    
  }]);
