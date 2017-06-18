'use strict';

/**
 * @ngdoc function
 * @name minovateApp.controller:FormsValidateCtrl
 * @description
 * # FormsValidateCtrl
 * Controller of the minovateApp
 */
app
  .controller('FormsValidateCtrl', function ($scope) {
    $scope.page = {
      title: 'Validation Elements',
      subtitle: 'Place subtitle here...'
    };

    // function to submit the form after all validation has occurred
		$scope.submitForm = function(isValid) {
      

			// check to make sure the form is completely valid
			if (isValid) {
				
			} else {

      }

		};

    $scope.notBlackListed = function(value) {
      var blacklist = ['bad@domain.com','verybad@domain.com'];
      return blacklist.indexOf(value) === -1;
    };
  });
