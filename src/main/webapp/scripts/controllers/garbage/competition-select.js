'use strict';


app
  .controller('CompetitionSelectCtrl',['$rootScope','$scope','$state','$stateParams','$window', '$filter','$location', 'FixtureService', function ($rootScope, $scope, $state, $stateParams,$window,$filter,$location,FixtureService) {
  	
	  $scope.bets = angular.fromJson($window.sessionStorage.getItem("bets"));
	  
	 if($stateParams.daily == true){
		 console.log("RICHIESTA PARTITE DI OGGI");
		 FixtureService.getDailyFixtures($scope.configuration.serviceUrl).then(function(d) {
			if (d.data != null){
				$scope.fixtureDtos = d.data.fixtureDtos;
			}else{
				
			}
		}); 
	 }else{
		 if($stateParams.competitionDto == null){
				$scope.competitionDto = angular.fromJson($window.sessionStorage.getItem("selectedCompetition"));
				//$state.go("app.dashboard");
			}else{
				$window.sessionStorage.setItem("selectedCompetition",angular.toJson($stateParams.competitionDto));
				$scope.competitionDto = $stateParams.competitionDto;
			}
		 
		 FixtureService.getCompetitionTimedFixtures($scope.competitionDto.id,$scope.configuration.serviceUrl).then(function(d) {
				if (d.data != null){
					$scope.fixtureDtos = d.data.fixtureDtos;
				}else{
					
				}
			}); 
		    
		    FixtureService.getCompetitionFinishedFixtures($scope.competitionDto.id,$scope.configuration.serviceUrl).then(function(d) {
				if (d.data != null){
					$scope.finishedFixtureDtos = d.data.fixtureDtos;
					$scope.hgt = "300";
				}else{
					
				}
			}); 
	 }
	
    $scope.$on('addBet', function(event, data) {
    	$scope.bets = angular.fromJson($window.sessionStorage.getItem("bets"));
    	if($scope.bets == null){
    		$scope.bets = [];
    	}
    	$scope.bets.push(data);
    	$window.sessionStorage.setItem("bets",angular.toJson($scope.bets));
    });
    
    $scope.deleteBet = function(index) {
    	$scope.bets = angular.fromJson($window.sessionStorage.getItem("bets"));
    	$scope.bets.splice(index, 1);
    	$window.sessionStorage.setItem("bets",angular.toJson($scope.bets));
    };
	
	
    
    
    $scope.viewDetail = false;
    
    $scope.viewOdds = function(fixtureDto){
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
