app
	.factory('UploadService', 
			function UploadService($http){
	
		return{
			uploadFixtures: function (input,serviceUrl){
		    	var url = serviceUrl + "/rest/UploadService/uploadFixtures/";
		    	
		    	var fd = new FormData();
		        //Take the first selected file
		        fd.append("file", input);

		        $http.post(url, fd, {
		            withCredentials: true,
		            headers: {'Content-Type': undefined },
		            transformRequest: angular.identity
		        });
	        },
	        
			uploadTennisFixtures: function (input,serviceUrl){
		    	var url = serviceUrl + "/rest/UploadService/uploadTennisFixtures/";
		    	
		    	var fd = new FormData();
		        //Take the first selected file
		        fd.append("file", input);

		        $http.post(url, fd, {
		            withCredentials: true,
		            headers: {'Content-Type': undefined },
		            transformRequest: angular.identity
		        });
	        }
		   
		};
	});
