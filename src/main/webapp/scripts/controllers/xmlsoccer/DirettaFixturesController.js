'use strict';

app.controller('DirettaFixturesController', [
		'$rootScope',
		'$scope',
		'$state',
		'$stateParams',
		'$window',
		'$filter',
		'$location',
		'$interval',
		'DirettaFixtureService',
		'BetTypeService',
		function($rootScope, $scope, $state, $stateParams, $window, $filter,
				$location, $interval, DirettaFixtureService,BetTypeService) {

			var userDto;
			if(typeof userDto === 'undefined' || user == null){
				userDto = angular.fromJson($window.sessionStorage.getItem("userDto"));
				if(typeof userDto === 'undefined' || userDto == null){
					$window.location.href = "#/core/login";
				}
				else{
					$scope.userDto = userDto;
				}
			}
			
			DirettaFixtureService.getCompetitions(
					$scope.configuration.serviceUrl).then(function(d) {
				if (d.data != null) {
					
					$scope.competitions = d.data.competitions;
					$scope.competitions.push("");
					console.log($scope.competitions);
				} else {

				}
			});
			
			BetTypeService.list(
					$scope.configuration.serviceUrl).then(function(d) {
				if (d.data != null) {
					$scope.bettypes = d.data.bettypes;
				}
			});
			
			$scope.getDirettaFixtures = function(competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To){
				if(quota1From == ''){
					quota1From = null;
				}
				if(quotaXFrom == ''){
					quotaXFrom = null;
				}
				if(quota2From == ''){
					quota2From = null;
				}
				if(quota1To == ''){
					quota1To = null;
				}
				if(quotaXTo == ''){
					quotaXTo = null;
				}
				if(quota2To == ''){
					quota2To = null;
				}
				if(homeTeam == ''){
					homeTeam = null;
				}
				if(awayTeam == ''){
					awayTeam = null;
				}
				DirettaFixtureService.getDirettaFixtures(
						$scope.configuration.serviceUrl,competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To).then(function(d) {
					if (d.data != null) {

						$scope.direttaFixtures = d.data.direttaFixtures;
						$scope.stats = d.data.stats;
						console.log($scope.direttaFixtures);
					} else {

					}
				});
				
			}
			
			$scope.createMasaniello = function(competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,masanielloName,masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloEventType,masanielloAverageQuote,masanielloPercentage,patrimonyPercentage){
				if(quota1From == ''){
					quota1From = null;
				}
				if(quotaXFrom == ''){
					quotaXFrom = null;
				}
				if(quota2From == ''){
					quota2From = null;
				}
				if(quota1To == ''){
					quota1To = null;
				}
				if(quotaXTo == ''){
					quotaXTo = null;
				}
				if(quota2To == ''){
					quota2To = null;
				}
				if(homeTeam == ''){
					homeTeam = null;
				}
				if(awayTeam == ''){
					awayTeam = null;
				}
				DirettaFixtureService.createMasaniello(
						$scope.configuration.serviceUrl,competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,$scope.userDto.email,masanielloName,masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloEventType,masanielloAverageQuote,masanielloPercentage,patrimonyPercentage).then(function(d) {
					if (d.data != null) {
						$scope.masaniellos = d.data.masaniellos;
					} else {

					}
				});
				
			}

		} ]);
