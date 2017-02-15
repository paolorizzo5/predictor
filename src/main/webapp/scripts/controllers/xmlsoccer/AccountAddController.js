'use strict';

app
		.controller(
				'AccountAddController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$stateParams',
						'$window',
						'$filter',
						'$location',
						'AccountService',
						function($rootScope, $scope, $state, $stateParams,
								$window, $filter, $location, AccountService) {

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
							
							
							$scope.addAccount = function(name,description) {
								AccountService
										.add(name,description,
											$scope.userDto.email,$scope.configuration.serviceUrl)
										.then(
												function(d) {
													if (d.data != null) {
														if(d.data.result == true){
															console.log("inserimento avvenuto con successo");
															$scope.accountDescription = null;
															$rootScope.$broadcast("onAccountCreated");
														}

													} else {

													}
												});

							};

						} ]);
