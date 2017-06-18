'use strict';


app
  .controller('UploadExcelFixturesController',['$rootScope','$scope','$state','$stateParams','$window', '$filter','$location', 'UploadService','FileUploader', function ($rootScope, $scope, $state, $stateParams,$window,$filter,$location,UploadService,FileUploader) {
  		
	  var uploader = $scope.uploader = new FileUploader({
	      url: 'scripts/modules/fileupload/upload.php' //enable this option to get f
	    });

	    // FILTERS
//
//	    uploader.filters.push({
//	      name: 'customFilter',
//	      fn: function() {
//	        return this.queue.length < 10;
//	      }
//	    });

	    uploader.filters.push({
	      name: 'imageFilter',
	      fn: function(item /*{File|FileLikeObject}*/, options) {
	          var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
	          return '|xls|xlsx|'.indexOf(type) !== -1;
	      }
	    });

	    // CALLBACKS

	    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
	      console.info('onWhenAddingFileFailed', item, filter, options);
	    };
	    uploader.onAfterAddingFile = function(fileItem) {
	      console.info('onAfterAddingFile', fileItem);
	    };
	    uploader.onAfterAddingAll = function(addedFileItems) {
	      console.info('onAfterAddingAll', addedFileItems);
	    };
	    uploader.onBeforeUploadItem = function(item) {
	    	UploadService.uploadFixtures(item,$scope.configuration.serviceUrl);
	      console.info('onBeforeUploadItem', item);
	    };
	    uploader.onProgressItem = function(fileItem, progress) {
	      console.info('onProgressItem', fileItem, progress);
	    };
	    uploader.onProgressAll = function(progress) {
	      console.info('onProgressAll', progress);
	    };
	    uploader.onSuccessItem = function(fileItem, response, status, headers) {
	      console.info('onSuccessItem', fileItem, response, status, headers);
	    };
	    uploader.onErrorItem = function(fileItem, response, status, headers) {
	      console.info('onErrorItem', fileItem, response, status, headers);
	    };
	    uploader.onCancelItem = function(fileItem, response, status, headers) {
	      console.info('onCancelItem', fileItem, response, status, headers);
	    };
	    uploader.onCompleteItem = function(fileItem, response, status, headers) {
	    	
	      console.info('onCompleteItem', fileItem, response, status, headers);
	    };
	    uploader.onCompleteAll = function() {
	      console.info('onCompleteAll');
	    };

	    console.info('uploader', uploader);
		
	    
	    $scope.uploadFile = function(files) {
	    	UploadService.uploadFixtures(files[0],$scope.configuration.serviceUrl);
	    };
	    
	    $scope.uploadTennisFile = function(files) {
	    	UploadService.uploadTennisFixtures(files[0],$scope.configuration.serviceUrl);
	    };
	    
	 
    
  }]);
