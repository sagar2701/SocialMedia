app.controller('NotificationCtrl',function($scope,NotificationService,$location,$rootScope,$routeParams)
		{
	var blogid=$routeParams.blogid
	function getNotificationsNotViewed(){
		console.log("not viewed")
	NotificationService.getNotificationsNotViewed().then(
	function(response){
	$rootScope.notifications=response.data
	$rootScope.notificationCount=$rootScope.notifications.length
	},function(response){
	$rootScope.error=response.data
	if(response.status==401)
	$location.path('/login')
	})
	}
	if(blogid!=undefined){
	NotificationService.getNotification(blogid).then(
	function(response){
	$scope.notification=response.data
	},function(response){
	$rootScope.error=response.data
	if(response.status==401)
	$location.path('/login')
	})
	}
	
	if(blogid!=undefined){

	NotificationService.updateNotification(blogid).then(
	function(response){
	getNotificationsNotViewed()
	},function(response){
	$rootScope.error=response.data
	if(response.status==401)
	$location.path('/login')
	})
	}
	getNotificationsNotViewed()
		})
