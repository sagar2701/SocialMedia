/**
 * 
 */
app.controller('JobCtrl',function($scope,$location,JobService){
	$scope.showJob=false
	//Data is from View to Controller
	$scope.addJob=function(job){
		JobService.addJob(job).then(function(response){
			alert('Job Details inserted successfully...')
			$location.path('/getalljobs')
		},function(response){
			$scope.error=response.data
		
				
		})
	}
	
    function getAllJob(){
	JobService.getAllJobs().then(function(response){
		$scope.jobs=response.data
	},function(response){
		$scope.err=response.data //response.data is ErrorClazz object
		console.log(response.data)
		console.log(response.status)
	})
    }
	$scope.showJobDetails=function(id){
		$scope.id=id
		$scope.showJob=!$scope.showJob
	}
	getAllJob()
})