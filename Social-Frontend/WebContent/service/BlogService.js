/**
 * 
 */
app.factory('BlogService',function($http)
{var BlogService={}

BlogService.addBlog=function(blog)
{var url="http://localhost:8081/middleware/project/addblog"
return $http.post(url,blog) 
	}
BlogService.getAllblogs=function(){
	//make rest call
	//call the middleware using $http service object
	var url="http://localhost:8081/middleware/project/getAllBlogs";
	return $http.get(url);//return the response to the controller
}


BlogService.getBlogsWaitingForApproval=function(){
	var url="http://localhost:8081/middleware/project/getUnApproveBlogs"	
	return $http.get(url)
}
BlogService.approveBlog=function(blog){
var url="http://localhost:8081/middleware/approveblog"
	return $http.put(url,blog)
}
BlogService.rejectBlog=function(blog,rejectionReason){
	console.log(blog)
	
	return $http.put("http://localhost:8081/middleware/project/rejectblog?rejectionReason="+rejectionReason,blog)
}
BlogService.getBlog=function(blogid){
	var url="http://localhost:8081/middleware/project/getBlog/"+blogid
	return $http.get(url)
}
BlogService.addComment=function(blog,commentTxt)
{console.log(blog)
	return $http.post("http://localhost:8081/middleware/project/addComment?commentTxt="+commentTxt,blog)
	}
BlogService.getBlogComments=function(blogId){
	return $http.get("http://localhost:8081/middleware/project/getcomments/"+blogId)
}

return BlogService
}

)