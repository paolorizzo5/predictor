'use strict';

app
		.controller(
				'DailyController',
				[
						'$rootScope',
						'$scope',
						'$state',
						'$stateParams',
						'$window',
						'$filter',
						'$location',
						'$interval',
						'FixtureService',
						'HistoricMatchService',
						'OddService',
						'StandingService',
						function($rootScope, $scope, $state, $stateParams,
								$window, $filter, $location, $interval, FixtureService,
								HistoricMatchService, OddService,
								StandingService) {

							$scope.viewDetail = false;

							FixtureService.getDailyFixtures(
									$scope.configuration.serviceUrl).then(
									function(d) {
										if (d.data != null) {

											$scope.fixtures = d.data.fixtures;
											console.log($scope.fixtures);
										} else {

										}
									});

							FixtureService
									.getLivescores(
											$scope.configuration.serviceUrl)
									.then(
											function(d) {
												if (d.data != null) {

													$scope.livescores = d.data.livescores;
													console
															.log($scope.livescores);
												} else {

												}
											});

							$scope.season = "LAST";

							$scope.getFixturePreview = function(fixture) {

								StandingService
										.getStandingByLeagueAndSeason(
												fixture.league.id,
												$scope.season,
												$scope.configuration.serviceUrl)
										.then(
												function(d) {
													if (d.data != null) {
														$scope.standings = d.data.standings;
													} else {

													}
												});

								HistoricMatchService
										.getFixturePreview(fixture.homeTeam.id,
												fixture.awayTeam.id,
												fixture.league.id,
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

														OddService
																.getOddsByFixtureId(
																		$scope.fixture.id,
																		$scope.configuration.serviceUrl)
																.then(
																		function(
																				d) {
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

							$scope.backToSummary = function(fixtureDto) {
								$scope.viewDetail = false;
							};

							var theInterval = $interval(
									function() {
										console
										.log("update livescores");
										FixtureService
												.getLivescores(
														$scope.configuration.serviceUrl)
												.then(
														function(d) {
															if (d.data != null) {

																$scope.livescores = d.data.livescores;
																
															} else {

															}
														});
									}.bind(this), 20000);

						} ]);
