'use strict';


app
  .controller('RecapCtrl',['$rootScope','$scope','$state','$stateParams','$window', '$filter','$location', 'FixtureService', function ($rootScope, $scope, $state, $stateParams,$window,$filter,$location,FixtureService) {
  	  
    $scope.addBet = function(bettype,detail,percentage){
    	console.log("addBet");
    	console.log(bettype);
    	console.log(detail);
    	console.log(detail);
    	
    	var data = {
    		'bettype' : bettype,
    		'detail': detail,
    		'percentage': percentage
    	};
    	
    	$scope.$emit('addBet', data);
    	
    };
    
    
    $scope.backToFixtures = function(fixtureDto){
    	$scope.viewDetail = false;
    };
    
  }]);
