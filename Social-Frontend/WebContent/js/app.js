var app=angular.module("app",['ngRoute','ngCookies'])

app.config(function($routeProvider){
	$routeProvider
	.when('/registration',{controller:'UserController',templateUrl:'View/addUser.html'})
	.when('/login',{controller:'UserController',templateUrl:'View/login.html'})
	.when('/home',{templateUrl:'View/home.html'})
	.when('/updateprofile',{controller:'UserController',templateUrl:'View/updateUser.html'})
	.when('/addjob',{controller:'JobCtrl',templateUrl:'	View/jobform.html'})
	.when('/getalljobs',{controller:'JobCtrl',templateUrl:'View/listofjobs.html'})
	.when('/addBlog',{controller:'blogController',templateUrl:'View/addBlog.html'})
	.when('/getBlogs',{controller:'blogController',templateUrl:'View/getBlogs.html'})
	.when('/getblog/:blogid',{controller:'Blogindetailctrl',templateUrl:'View/blogindetail.html'})
	.when('/getblogwaitingforapproval/:blogid',{controller:'Blogindetailctrl',templateUrl:'View/blogapprovalform.html'})
	.when('/blogsWaitingForApproval',{controller:'blogController',templateUrl:'View/approveBlog.html'})
     .when('/suggestedusers',{controller:'FriendCtrl',templateUrl:'View/suggesteduserslist.html'})
	.when('/pendingrequests',{controller:'FriendCtrl',templateUrl:'View/pendingrequests.html'})
	.when('/listoffriends',{controller:'FriendCtrl',templateUrl:'View/friendslist.html'})
	.when('/uploadprofilepic',{templateUrl:'View/uploadprofilepicture.html'})
	.when('/getnotification/:blogid',{controller:'NotificationCtrl',templateUrl:'View/notificationdetails.html'})
	.when('/chat',{controller:'ChatCtrl',templateUrl:'View/chat.html',})
	.otherwise({controller:'FriendCtrl',templateUrl:'View/home.html'})
})
//Angular module gets instantiated, app.run() will get executed
//restore userdetails to the variable user in $rootScope
app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.user==undefined)
		$rootScope.user=$cookieStore.get('userDetails')
		
		$rootScope.logout=function(){
		   alert('Entering the function logout')
		   UserService.logout().then(function(response){
			   //delete the user variable from $rootScope
			   //clear the cookie
			   //redirect the user to login page
			   delete $rootScope.user
			   $cookieStore.remove('userDetails')
			   $location.path('/login')
		   },function(response){
               //if $rootScope.user is not undefined,clear the cookie and redirect the user to login page
			   if($rootScope.user!=undefined)
			   delete $rootScope.user
			   $cookieStore.remove('userDetails')
			   $location.path('/login')
		   })
	   }	
		
})