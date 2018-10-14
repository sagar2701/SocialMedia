/**
 * 
 */
app.factory('UserService',function($http)
{var UserService={}
UserService.getAllUsers=function()
{var url="http://localhost:8081/middleware/project/getAllUsers";
return $http.get(url);
	
	}
UserService.addUser=function(user)
{var url="http://localhost:8081/middleware/project/addUser";
return $http.post(url,user) 
	}
UserService.login=function(user){
	var url="http://localhost:8081/middleware/login"
	return $http.put(url,user)
}

UserService.logout=function(){
	var url="http://localhost:8081/middleware/logout"
	return $http.put(url)
}

UserService.updateProfile=function(user){
	var url="http://localhost:8081/middleware/project/updateUser"
	return $http.put(url,user)
}
UserService.getUnapproveBlogs=function()
{var url="http://localhost:8081/middleware/project/getUnApproveBlogs";
return $http.get(url);
	
	}

	return UserService;
	})