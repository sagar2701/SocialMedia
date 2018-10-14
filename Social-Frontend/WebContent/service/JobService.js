app.factory('JobService',function($http){
	var JobService={}
	JobService.addJob=function(job){
		return $http.post("http://localhost:8081/middleware/project/addJob",job)
	}
	
	JobService.getAllJobs=function(){
		return $http.get("http://localhost:8081/middleware/project/getAllJobs")
	}
	
	return JobService;
})