/**
 * 
 */
app.controller('blogController',function($scope,BlogService,$location,$rootScope){//get PersonService from factory
	
	//Define a function
	
	$scope.blogsWaitingForApproval= getBlogsWaitingForApproval
	

	getAllBlog()
	//call the function only for ADMIN role
	if($rootScope.user.role=='ADMIN')
	getBlogsWaitingForApproval()
	
	
	
	$scope.addBlog=function(blog){
		console.log(blog)
		BlogService.addBlog(blog).then(
				function(response){
					alert('Blog added sucessfully')
					$location.path('/home')
				},function(response){
					$scope.error=response.data//ErrorClazz object in JSON fmt
				})
	}
	function getAllBlog(){
		BlogService.getAllblogs().then(
			function(response){
				$scope.getBlogs=response.data
			},function(response){
				if(response.status==401)
				$location.path('/login')
			})
			}
	
	function getBlogsWaitingForApproval(){
		console.log("getBlogsWaitingForApproval")
		BlogService.getBlogsWaitingForApproval().then(function(response){
			$scope.blogsWaitingForApproval=response.data
		},function(response){
			if(response.status==401)
	    		$location.path('/login')
		})
	}	
	
})