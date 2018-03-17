app.controller('composicoesCtrl', function($scope, $http,$window) {

	$scope.jaCarregou = false;

	$http.get("/obterMinhasComposicoes")
	   .then(function(response) {
	   $scope.composicoes = response.data;
	   $scope.jaCarregou = true;
	 });

});