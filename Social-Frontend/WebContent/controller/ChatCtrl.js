app.controller("ChatCtrl", function($scope, ChatService,$rootScope) {
	console.log('ChatCtrl')
  $scope.messages = [];
  $scope.message = ""
  $scope.max = 50;
  $scope.addMessage = function() {
	  console.log("addMessage")
    ChatService.send($scope.message);
    $scope.message = "";
  };

  ChatService.receive().then(null, null, function(message) {
	  console.log("receive")

    $scope.messages.push(message);  // this messages we have to display in html text area
  });
});
