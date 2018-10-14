app.controller('Blogindetailctrl',function($scope,$location,BlogService,$routeParams,$sce){
	var blogid=$routeParams.blogid
	$scope.isRejected=false
	
	BlogService.getBlog(blogid).then(function(response){
				$scope.blog=response.data//Blog object
		$scope.content=$sce.trustAsHtml($scope.blog.blogContent)
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	
	$scope.approveBlog=function(blog){
		console.log(blog.blogid)
		BlogService.approveBlog(blog).then(function(response){
			$location.path('/blogsWaitingForApproval')
			
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
    
	$scope.rejectBlog=function(blog,rejectionReason){
		console.log(blog.blogid)
		BlogService.rejectBlog(blog,rejectionReason).then(function(response){
			$location.path('/blogsWaitingForApproval')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})}
	$scope.showTextArea=function(){
		$scope.isRejected=!$scope.isRejected
	}
		$scope.addComment=function(blog,commentTxt){
			if(commentTxt==undefined || commentTxt=="")
				$scope.error='please enter some comments.. '
				else 
			BlogService.addComment(blog,commentTxt).then(function(response){
				$scope.commentTxt=""
				$scope.error=""
				$scope.blogComment=response.data
			},function(response){
				if(response.status==401)
					$location.path('/login')
			})
		}
		
		$scope.getBlogComments=function(blogId){
			BlogService.getBlogComments(blogId).then(
					function(response){
						$scope.blogComments=response.data
					},function(response){
						if(response.status==401)
							$location.path('/login')
					})
		}
	
	
	
})