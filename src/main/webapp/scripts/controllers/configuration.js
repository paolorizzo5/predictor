app
  .controller('ConfigurationController', [
	'$scope','$http', function($scope,$http) {
		
		$http.get('scripts/config/config.properties').then(function (response) {
			$scope.configuration = response.data.configuration;
		});
    }]);
