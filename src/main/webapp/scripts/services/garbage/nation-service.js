app
	.factory('NationService', 
			function NationService($http){
	
		return{
			
		    addNation: function (areaname,nationname,serviceUrl){
		    	return $http.get("http://" + serviceUrl + "/rest/NationService/add-nation/" + areaname + "&" + nationname + "&" + new Date().getTime());
		    },
		    deleteNation: function (nationname,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/NationService/delete-nation/" + nationname + "&" + new Date().getTime());
		    },
		    list: function (serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/NationService/list/" + new Date().getTime());
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
