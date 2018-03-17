app.controller('composicaoCtrl', function($scope, $http,$window) {

	$http.get("/composicao/"+id)
	   .then(function(response) {
	   $scope.composicao = response.data;
	});

});