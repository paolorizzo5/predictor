app
	.factory('CompetitionService', 
			function CompetitionService($http){
	
		return{
			
		    addCompetition: function (id,name,nationName,sportId,startDate,endDate,serviceUrl){
		    	return $http.get("http://" + serviceUrl + "/rest/CompetitionService/add-competition/" + id + "&" + name + "&" + nationName + "&" + sportId + "&"  + startDate + "&" + endDate + "&" + new Date().getTime());
		    },
		    deleteCompetition: function (id,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/CompetitionService/delete-competition/" + id + "&" + new Date().getTime());
		    },
		    list: function (serviceUrl){
		    	
		    	var url = "http://" + serviceUrl + "/rest/CompetitionService/list/";
		    	var fd = {
		    			'uniquecallid': new Date().getTime()
		    		  };
		    	
		    	console.log(angular.toJson(fd));
		        return $http.post(url, angular.toJson(fd), {transformRequest: angular.identity,headers: {'Content-Type': 'application/json'}});
	    		
				//return $http.get("http://" + serviceUrl + "/rest/CompetitionService/list/" + new Date().getTime());
		    }
		    
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
