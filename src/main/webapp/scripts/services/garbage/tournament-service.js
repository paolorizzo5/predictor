app
	.factory('TournamentService', 
			function TournamentService($http){
	
		return{
			addTournament: function (id,description,competitions,margin,guaranteed,startDate,endDate,serviceUrl){
				
				var req = {
				 method: "POST",
				 url: "http://" + serviceUrl + "/rest/TournamentService/add-tournament/",
				 headers: {
				   'Content-Type': 'application/json'
				 },
				 data: { 
					 id: id ,
					 description: description,
					 competitions: competitions,
					 margin: margin,
					 guaranteed: guaranteed,
					 startDate: startDate,
					 endDate: endDate
				 }
				}
				console.log(req);
				return $http(req);
		    },
		    deleteTournament: function (name,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/TournamentRestService/delete-tournament/" + name + "&" + new Date().getTime());
		    },
		    list: function (email,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/TournamentService/list/" + email + "&" + new Date().getTime());
		    },
		    listTournamentsToRegister: function (email,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/TournamentService/listTournamentsToRegister/" + email + "&" + new Date().getTime());
		    },
		    subscribeTournament: function (email,id,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/TournamentService/subscribeTournament/" + email + "&" + id + "&" + new Date().getTime());
		    },
		    getByIdWithFixturesRange: function (id,days,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/TournamentService/getByIdWithFixturesRange/" + id + "&" + days + "&" + new Date().getTime());
		    }

		};
	});
