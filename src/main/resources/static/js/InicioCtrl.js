app.controller('inicioCtrl', function($scope,$http) {

	$scope.jaCarregou = false;
	$scope.titulo = "In�cio"
		
	$http.get("/geteventos").then(function(response) {
		$scope.eventos = response.data;
		$scope.jaCarregou = true;
	});	

});