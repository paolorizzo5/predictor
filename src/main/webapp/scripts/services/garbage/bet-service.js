app
	.factory('BetService', 
			function BetService($http){
	
		return{
			
		    add: function (email,id,homeTeam,awayTeam,matchDate,amount,bet,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/BetService/add/" + email + "&"+ id + "&" + homeTeam + "&"+ awayTeam + "&"+ matchDate + "&" + amount + "&" + bet + "&"+ new Date().getTime());
		    },
		    getByUserAndTournament: function (email,id,serviceUrl){
				return $http.get("http://" + serviceUrl + "/rest/BetService/getByUserAndTournament/" + email + "&"+ id + "&"+ new Date().getTime());
		    }


		};
	});
