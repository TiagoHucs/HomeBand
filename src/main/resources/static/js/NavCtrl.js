app.controller('navCtrl', function($scope, $http) {

    $http.get("/getloggeduser")
    .then(function(response) {
    	$scope.user = response.data;
    });

});