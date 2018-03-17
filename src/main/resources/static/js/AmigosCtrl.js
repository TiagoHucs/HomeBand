app.controller('amigosCtrl', function($scope,$http) {
	
	$scope.jaCarregou = false;
	$scope.titulo = "Amigos"
		
	$http.get("/obterTodosPerfis").then(function(response) {
		$scope.perfis = response.data;
		$scope.jaCarregou = true;	
	});

});