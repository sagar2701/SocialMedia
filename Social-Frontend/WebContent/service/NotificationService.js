app.factory('NotificationService',function($http)
{var NotificationService={}
NotificationService.getNotificationsNotViewed=function()
{
	return $http.get("http://localhost:8081/middleware/notifications")
	}
NotificationService.getNotification=function(blogid)
{
	return $http.get("http://localhost:8081/middleware/notification/"+blogid)
	}

NotificationService.updateNotification(blogid)
{return $http.put("http://localhost:8081/middleware/updatenotification/"+blogid)
	}

return NotificationService
})