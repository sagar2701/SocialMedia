/**
 * 
 */
app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){//get PersonService from factory
	
	//Define a function
	function getAllUsers(){
	UserService.getAllUsers().then(function(response){
		$scope.users=response.data //response.data is Array of Person objects
	},function(response){
		$scope.err=response.data //response.data is ErrorClazz object
		console.log(response.data)
		console.log(response.status)
	})
	}
	$scope.addUser=function(user){
		console.log(user)
		UserService.addUser(user).then(
				function(response){
					alert('Registered successfully.. please login again')
					$location.path('/login')
				},function(response){
					$scope.error=response.data//ErrorClazz object in JSON fmt
				})
	}
	
	$scope.login=function(user){
		UserService.login(user).then(function(response){
			$cookieStore.put('userDetails',response.data)
			$rootScope.user=response.data // User Object
			$location.path('/home')
		},function(response){
			$scope.error=response.data
		})
	}
	
	$scope.updateProfile=function(user){
		UserService.updateProfile(user).then(function(response){
			$rootScope.user=response.data
			$cookieStore.put('userDetails',response.data)
			alert('Updated user profile successfully..')
			console.log(user)
			$location.path('/home')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
	}
})