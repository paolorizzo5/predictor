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

			$scope.filtersBase = [];
			$scope.filtersAdvanced = [];
			
			$scope.today = function() {
			      $scope.dateFrom = new Date();
			      $scope.dateTo = new Date();
			    };

			    $scope.today();

			    $scope.clear = function () {
			    	$scope.dateFrom = null;
				      $scope.dateTo = null;
			    };

			    // Disable weekend selection
			    $scope.disabled = function(date, mode) {
			      return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
			    };

			    $scope.toggleMin = function() {
			      $scope.minDate = $scope.minDate ? null : new Date();
			    };
			    $scope.toggleMin();

			    $scope.open = function($event) {
			      $event.preventDefault();
			      $event.stopPropagation();

			      $scope.opened = true;
			    };
			    
			    $scope.openDateFrom = function($event) {
				      $event.preventDefault();
				      $event.stopPropagation();

				      $scope.openedDateFrom = true;
				    };
				    
			    $scope.openDateTo = function($event) {
			      $event.preventDefault();
			      $event.stopPropagation();

			      $scope.openedDateTo = true;
			    };

			    $scope.dateOptions = {
			      formatYear: 'yy',
			      startingDay: 1,
			      'class': 'datepicker'
			    };

			    $scope.formats = ['dd-MM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
			    $scope.format = $scope.formats[0];
			    
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
			$scope.visibleDiv = "showAdvanced";
			
			$scope.viewMasaniello = false;
			
			$scope.showMasaniello = function(){
				$scope.viewMasaniello = true;
			};
			$scope.hideMasaniello = function(){
				$scope.viewMasaniello = false;
			};
			
			
			DirettaFixtureService.getPlansNames(
					$scope.configuration.serviceUrl,$scope.userDto.email).then(function(d) {
				if (d.data != null) {
					$scope.planNames = d.data.planNames;
				} else {

				}
			});
			
			DirettaFixtureService.getCompetitions(
					$scope.configuration.serviceUrl).then(function(d) {
				if (d.data != null) {
					
					$scope.competitions = d.data.competitions;
					$scope.competitions.push("");
					
				} else {

				}
			});
			
			BetTypeService.list(
					$scope.configuration.serviceUrl).then(function(d) {
				if (d.data != null) {
					$scope.bettypes = d.data.bettypes;
				}
			});
			
				
			$scope.getDirettaFixtures = function(competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,fullSearch,dateFrom,dateTo,divToShow){
				
				$scope.visibleDiv = 'showLoading';
				console.log(dateFrom);
				console.log(dateTo);
				
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
				if(dateFrom == ''){
					dateFrom = null;
				}
				if(dateTo == ''){
					dateTo = null;
				}
				
				DirettaFixtureService.getDirettaFixtures(
						$scope.configuration.serviceUrl,competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,fullSearch,dateFrom,dateTo).then(function(d) {
					if (d.data != null) {
						$scope.direttaFixtures = d.data.direttaFixtures;
						$scope.stats = d.data.stats;
						$scope.visibleDiv = divToShow;
						
					} else {

					}
				});
				
			}
			$scope.parseFloat = function(value)
		    {
				console.log("parse-float");
		        return parseFloat(value);
		    }
			
			$scope.viewDiv = function(divToShow){
				$scope.visibleDiv = divToShow;
			};
			
			$scope.viewPlanDetail = function(name){
				$scope.masaniellos = null;
				$scope.selectedMasanielloRounds = null;
				$scope.selectedDirettaFixtures = null;
				
				DirettaFixtureService.getPlanDetail(
						$scope.configuration.serviceUrl,$scope.userDto.email,name).then(function(d) {
					if (d.data != null) {
						$scope.masaniellos = d.data.plan.masanielloDtos;
						$scope.selectedPlan = d.data.plan;
					} else {

					}
				});
			};
			$scope.showMasanielloDetail = function(masaniello){
				DirettaFixtureService.getMasanielloRounds($scope.configuration.serviceUrl,masaniello.masanielloPlanDto.name,masaniello.name,$scope.userDto.email,masaniello.id).then(function(d) {
					console.log("d.data.rounds");
					console.log(d.data.rounds);
					$scope.selectedMasaniello = masaniello;
					$scope.selectedMasanielloRounds = d.data.rounds;
				});
				//$scope.visibleDiv = "showMasanielloRoundsDetail";
			};
			
			$scope.showMasanielloRoundDetail = function(round){
				console.log ("showMasanielloRoundDetail");
				console.log (round);
				$scope.selectedDirettaFixtures = round.fixtures;
			};
			
			$scope.deleteMasaniello = function(planName){
				console.log(planName);
				DirettaFixtureService.deleteMasanielloPlan(
						$scope.configuration.serviceUrl,$scope.userDto.email,planName).then(function(d) {
					if (d.data != null) {
						DirettaFixtureService.getPlansNames(
								$scope.configuration.serviceUrl,$scope.userDto.email).then(function(d) {
							if (d.data != null) {
								$scope.planNames = d.data.planNames;
								$scope.masaniellos = null;
								$scope.selectedPlan = null;
							} else {

							}
						});
					} else {

					}
				});
			};
			
			$scope.createPlan = function(masanielloName,masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloAverageQuote,masanielloAdditionalQuote,masanielloPercentage,patrimonyPercentage,masanielloTests){
				$scope.visibleDiv = 'showLoading';
				
				if(masanielloTests == ''){
					masanielloTests = null;
				}
				

				DirettaFixtureService.createPlan(
						$scope.configuration.serviceUrl,$scope.filtersBase,$scope.userDto.email,masanielloName,masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloAverageQuote,masanielloAdditionalQuote,masanielloPercentage,patrimonyPercentage).then(function(d) {
					if (d.data != null && d.data.result == true) {
						console.log("plan created");
						DirettaFixtureService.getPlansNames(
								$scope.configuration.serviceUrl,$scope.userDto.email).then(function(d) {
							if (d.data != null) {
								$scope.planNames = d.data.planNames;
								$scope.visibleDiv = 'showMasanielloDetails';
							} else {

							}
						});
						for (var i = 1; i<= masanielloTests;i++){
							DirettaFixtureService.createMasaniello(
									$scope.configuration.serviceUrl,
									$scope.filtersBase,
									$scope.userDto.email,
									masanielloName,
									masanielloAmount,
									masanielloRounds,
									masanielloEventToWin,
									masanielloAverageQuote,
									masanielloAdditionalQuote,
									masanielloPercentage,
									patrimonyPercentage,
									i).then(function(d) {
								if (d.data != null && d.data.result == true) {
									
								}
							});
						}
						
					}
				});
				
			}
			
			$scope.createPlanAdvanced = function(masanielloName,masanielloAmount,masanielloRounds,patrimonyPercentage,lowerByWin,raiseByLoss,masanielloTests){
				$scope.visibleDiv = 'showLoading';
				
				if(masanielloTests == ''){
					masanielloTests = null;
				}
				

				DirettaFixtureService.createPlanAdvanced(
						$scope.configuration.serviceUrl,
						$scope.filtersAdvanced,
						$scope.userDto.email,
						masanielloName,
						masanielloAmount,
						masanielloRounds,
						patrimonyPercentage,
						lowerByWin,
						raiseByLoss).then(function(d) {
					if (d.data != null && d.data.result == true) {
						DirettaFixtureService.getPlansNames(
								$scope.configuration.serviceUrl,$scope.userDto.email).then(function(d) {
							if (d.data != null) {
								$scope.planNames = d.data.planNames;
								$scope.visibleDiv = 'showMasanielloDetails';
							} else {

							}
						});
						for (var i = 1; i<= masanielloTests;i++){
							console.log("createMasanielloAdvanced");
							DirettaFixtureService.createMasanielloAdvanced(
									$scope.configuration.serviceUrl,
									$scope.filtersAdvanced,
									$scope.userDto.email,
									masanielloName,
									masanielloAmount,
									masanielloRounds,
									patrimonyPercentage,
									lowerByWin,
									raiseByLoss,
									i).then(function(d) {
								if (d.data != null && d.data.result == true) {
									
								}
							});
						}
						
					}
				});
				
			}
			
			$scope.resetFilters = function(){
				$scope.filtersAdvanced = [];
				$scope.filtersBase = [];
				
			}
			
			$scope.resetFiltersBase = function(){
				$scope.filtersBase = [];
			}

			$scope.removeFilterBase = function(filter){
				if($scope.filtersBase.length == 1){
					$scope.filtersBase.splice(0,0);
				}else{
					for (var i in $scope.filtersBase){
						if($scope.filtersBase[i] === filter){
							$scope.filtersBase.splice(i);
						}
					}
				}
				
			}
			
			$scope.removeFilterAdvanced = function(filter){
				
				for (var i in $scope.filtersAdvanced){
					if($scope.filtersAdvanced[i] === filter){
						$scope.filtersAdvanced.splice(i,1);
					}
				}
				
				
			}
			$scope.addFilterBase = function(competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,dateFrom,dateTo,betType){
				
				//$scope.visibleDiv = 'showLoading';
				if(competition == ''){
					competition = null;
				}
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
				if(dateFrom == ''){
					dateFrom = null;
				}
				if(dateTo == ''){
					dateTo = null;
				}

				var filter = {
					    "quota1From":quota1From,
					    "quota1To":quota1To,
					    "quotaXFrom":quotaXFrom,
					    "quotaXTo":quotaXTo,
					    "quota2From":quota2From,
					    "quota2To":quota2To,
					    "homeTeam":homeTeam,
					    "awayTeam":awayTeam,
					    "dateFrom":dateFrom,
					    "competition" : competition,
					    "dateTo":dateTo,
					    "betType":betType,
					    "amount":"0"
					    
				};
				
				$scope.filtersBase.push(filter);
			}
			
			$scope.addFilterAdvanced = function(competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To,dateFrom,dateTo,betType){
				
				//$scope.visibleDiv = 'showLoading';
				if(competition == ''){
					competition = null;
				}
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
				if(dateFrom == ''){
					dateFrom = null;
				}
				if(dateTo == ''){
					dateTo = null;
				}

				var filter = {
					    "quota1From":quota1From,
					    "quota1To":quota1To,
					    "quotaXFrom":quotaXFrom,
					    "quotaXTo":quotaXTo,
					    "quota2From":quota2From,
					    "quota2To":quota2To,
					    "homeTeam":homeTeam,
					    "awayTeam":awayTeam,
					    "dateFrom":dateFrom,
					    "competition" : competition,
					    "dateTo":dateTo,
					    "betType":betType,
					    "amount":"0"
					    
				};
				
				$scope.filtersAdvanced.push(filter);
			}

		} ]);
