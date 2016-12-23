app
	.factory('SportService', 
			function SportService($http){
	
		return{
			
		    
		    list: function (serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/SportService/list/" + new Date().getTime());
		    },
//		    addCompetition: function (serviceUrl,areaname,nationname,file){
//		    	var url = "http://" + serviceUrl + "/rest/AreaService/add-competition/";
//		    	var fd = new FormData();
//		        fd.append('file', file);
//		        fd.append('areaname', areaname);
//		        fd.append('nationname', nationname);
//		        fd.append('filename', file.name);
//		        
//		        return $http.post(url, fd, {transformRequest: angular.identity,headers: {'Content-Type': undefined}});
//		    	
//    	    },
//    	    
//    	    getallCompetitions: function(serviceUrl){
//    	    	return $http.get("http://" + serviceUrl + "/rest/AreaService/getall-competitions/" + new Date().getTime());
//    	    }
		   
		};
	});
