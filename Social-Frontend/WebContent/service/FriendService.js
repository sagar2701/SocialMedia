
app.factory('FriendService',function($http){
	var FriendService={}
	var BASE_URL="http://localhost:8081/middleware/project"
	FriendService.getSuggestedUsers=function(){
		return $http.get(BASE_URL + "/suggestedusers")
	}
	
	FriendService.sendFriendRequest=function(toId){
		return $http.post(BASE_URL + "/friendrequest",toId)
	}
	
	FriendService.getPendingRequests=function(){
		return $http.get(BASE_URL + "/pendingrequests")
	}
	
	FriendService.acceptRequest=function(pendingRequest){
		return $http.put(BASE_URL + "/acceptRequest",pendingRequest)
	}
	
	FriendService.deleteRequest=function(pendingRequest){
		return $http.put(BASE_URL + "/deleteRequest",pendingRequest)
	}
	
    FriendService.listOfFriends=function(){
    	return $http.get(BASE_URL + "/listofFriends")
    }	
	return FriendService;
})
