'use strict';

app
		.controller(
				'LeagueDetailController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$stateParams',
						'$window',
						'$filter',
						'$location',
						'LeagueService',
						'FixtureService',
						'StandingService',
						'HistoricMatchService',
						'OddService',
						function($rootScope, $scope, $state, $stateParams,
								$window, $filter, $location, LeagueService,
								FixtureService, StandingService,
								HistoricMatchService, OddService) {

							$scope.viewDetail = false;
							if ($stateParams.league == null) {
								$scope.league = angular
										.fromJson($window.sessionStorage
												.getItem("selectedLeague"));
								// $state.go("app.dashboard");
							} else {
								$window.sessionStorage.setItem(
										"selectedLeague", angular
												.toJson($stateParams.league));
								$scope.league = $stateParams.league;
							}

							$scope.season = "1617";

							FixtureService.getFixturesByLeagueAndSeason(
									$scope.league.id, $scope.season,
									$scope.configuration.serviceUrl).then(
									function(d) {
										if (d.data != null) {
											$scope.fixtures = d.data.fixtures;
										} else {

										}
									});

							HistoricMatchService
									.getHistoricMatchesByLeagueAndSeason(
											$scope.league.id, $scope.season,
											$scope.configuration.serviceUrl)
									.then(
											function(d) {
												if (d.data != null) {
													$scope.historicMatches = d.data.historicMatches;
												} else {

												}
											});

							StandingService
									.getStandingByLeagueAndSeason(
											$scope.league.id, $scope.season,
											$scope.configuration.serviceUrl)
									.then(
											function(d) {
												if (d.data != null) {
													$scope.standings = d.data.standings;
												} else {

												}
											});

							$scope.backToSummary = function(fixtureDto) {
								$scope.viewDetail = false;
							};

							$scope.getFixturePreview = function(fixture) {
								HistoricMatchService
										.getFixturePreview(fixture.homeTeam.id,
												fixture.awayTeam.id,
												$scope.league.id,
												$scope.season,
												$scope.configuration.serviceUrl)
										.then(
												function(d) {
													if (d.data != null) {
														$scope.fixture = fixture;

														for ( var i in $scope.standings) {
															if ($scope.fixture.homeTeam.id === $scope.standings[i].team.id) {
																$scope.homeStanding = $scope.standings[i];
																console
																		.log($scope.homeStanding);
															} else if ($scope.fixture.awayTeam.id === $scope.standings[i].team.id) {
																$scope.awayStanding = $scope.standings[i];
																console
																		.log($scope.awayStanding);
															}

														}
														
														OddService.getOddsByFixtureId(
															$scope.fixture.id,
															$scope.configuration.serviceUrl).then(
															function(d) {
																if (d.data != null) {
																	$scope.odds = d.data.odds;
																} else {

																}
															});

														$scope.fixturePreview = d.data.fixturePreview;
														$scope.fixturePreviewThisSeason = d.data.fixturePreviewThisSeason;
														$scope.last5Home = d.data.last5Home;
														$scope.last5Away = d.data.last5Away;
														$scope.viewDetail = true;

													} else {

													}
												});
								

							};

						} ]);
