'use strict';

app
		.controller(
				'AccountDetailController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$stateParams',
						'$window',
						'$filter',
						'$location',
						'AccountService',
						'ProspectService',
						function($rootScope, $scope, $state, $stateParams,
								$window, $filter, $location, AccountService, ProspectService) {
							
							$scope.storeAccountInSession = function(account) {
								console.log ("metto in sessione l'account");
								console.log (account);
								$window.sessionStorage.setItem("selectedAccount", angular.toJson(account));
								$scope.account = account;
							};

							/*
							 * recupero sessione utente
							 * 
							 */
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
							
							$scope.currentTimeInMillis = new Date().getTime();
							
							/*
							 * recupero account o da sessione o da click su nav
							 * via state params
							 * 
							 */
							if ($stateParams.account == null) {
								console.log ("$stateParams.account nullo");
								$scope.account = angular
										.fromJson($window.sessionStorage
												.getItem("selectedAccount"));
								
								console.log ("scope.account: " + $scope.account);
								
								
							} else {
								console.log ("$stateParams.account valorizzato");
								$scope.account = $stateParams.account;
								console.log ("scope.account: " + $scope.account);
								AccountService.get($stateParams.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
								.then(
									function(d) {
										if (d.data != null) {
											if(d.data.result == true){
												$scope.storeAccountInSession(d.data.account);
											}
										} else {

										}
									});
							}
							
							/*
							 * recupero prospetto linkatto all'account
							 * 
							 */
							if($scope.selectedProspect == null){
								ProspectService.get($scope.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
								.then(
										function(d) {
											if (d.data != null) {
												$scope.selectedProspect = d.data.prospect;
											}
										});
								
							}
							
							/*
							 * funzionalità di cancellazione account
							 * 
							 */
							$scope.deleteAccount = function(name) {
								AccountService.delete(name,$scope.userDto.email,$scope.configuration.serviceUrl)
									.then(
										function(d) {
											if (d.data != null) {
												if(d.data.result == true){
													$rootScope.$broadcast("onAccountDeleted");
												}
											} else {

											}
										});
							};
							
							/*
							 * funzionalità di aggiunta prospetto
							 * 
							 */
							$scope.addProspect = function(name,initialAmount,duration,dailyPercentageExpected) {
								ProspectService.add(name,initialAmount,duration,dailyPercentageExpected,$scope.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
									.then(
										function(d) {
											if (d.data != null) {
												if(d.data.result == true){
													$scope.selectedProspect = d.data.prospect;
												}
											}
										});
							};
							
							/*
							 * funzionalità di aggiunta scommessa
							 * 
							 */
							$scope.addBet = function(eventDescription,bettypeDescription,amount,moltiplicator) {
								AccountService.addBet(eventDescription,bettypeDescription,amount,moltiplicator,$scope.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
									.then(
										function(d) {
											if (d.data != null) {
												if(d.data.result == true){
													$scope.storeAccountInSession(d.data.account);
												}
											}
										});
							};
							
							/*
							 * funzionalità di aggiunta scommessa
							 * 
							 */
							$scope.markBet = function(bet,isWinning) {
								AccountService.markBet(bet.insertDate,isWinning,$scope.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
									.then(
										function(d) {
											if (d.data != null) {
												if(d.data.result == true){
													$scope.storeAccountInSession(d.data.account);
												}
											}
										});
							};
							
							$scope.archiveBet = function(bet) {
								AccountService.archiveBet(bet.insertDate,$scope.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
									.then(
										function(d) {
											if (d.data != null) {
												if(d.data.result == true){
													$scope.storeAccountInSession(d.data.account);
													$scope.selectedProspect = d.data.account.prospect;
													
												}
											}
										});
							};
							
							
							/*
							 * funzionalità di versamente
							 * 
							 */
							$scope.deposit = function(accountName,amount) {
								AccountService.deposit(accountName,amount,$scope.userDto.email,$scope.configuration.serviceUrl)
										.then(
											function(d) {
												if (d.data != null) {
													if(d.data.result == true){
														$scope.storeAccountInSession(d.data.account);
													}
												} else {
												}
											});
							};
							
							/*
							 * funzionalità di prelievo
							 * 
							 */
							$scope.withdraw = function(accountName,amount) {
								AccountService.withdraw(accountName,amount,$scope.userDto.email,$scope.configuration.serviceUrl)
										.then(
											function(d) {
												if (d.data != null) {
													if(d.data.result == true){
														$scope.storeAccountInSession(d.data.account);
													}
												} else {

												}
											});
							};
							
							$scope.pushProspectElement = function(prospectName,incremental) {
								ProspectService.pushProspectElement(prospectName,incremental,$scope.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
										.then(
											function(d) {
												if (d.data != null) {
													if(d.data.result == true){
														$scope.currentTimeInMillis = new Date().getTime();
														
														$scope.selectedProspect = d.data.prospect;
													}
												} else {

												}
											});
							};
							
							$scope.popProspectElement = function(prospectName,incremental) {
								ProspectService.popProspectElement(prospectName,incremental,$scope.account.name,$scope.userDto.email,$scope.configuration.serviceUrl)
										.then(
											function(d) {
												if (d.data != null) {
													if(d.data.result == true){
														$scope.currentTimeInMillis = new Date().getTime();
														
														$scope.selectedProspect = d.data.prospect;
													}
												} else {

												}
											});
							};
							
							$scope.dayDiff = function(endDate,startDate){
								var ms = endDate - startDate;
								
								var date = new Date(ms);
								
								var days = Math.floor(ms / 86400000);
								
								
								var hours;
								if(date.getUTCHours() < 10){
									hours = "0" + date.getUTCHours();
								}else{
									hours = date.getUTCHours();
								}
								var minutes;
								if(date.getUTCMinutes() < 10){
									minutes = "0" + date.getUTCMinutes();
								}else{
									minutes = date.getUTCMinutes();
								}
								
								var seconds;
								if(date.getUTCSeconds() < 10){
									seconds = "0" + date.getUTCSeconds();
								}else{
									seconds = date.getUTCSeconds();
								}
								if (days == 0){
									return hours + ":" + minutes + ":" + seconds;
								}else{
									return days + "d " + hours + ":" + minutes + ":" + seconds;
								}
								
							};
							
							

						} ]);



