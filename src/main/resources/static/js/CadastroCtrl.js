var app = angular.module('cadastroApp', []);
app.controller('cadastroCtrl', function($scope,$http) {
	
	$scope.cadastrar = function(){
		$http({
			method : "POST",
			url : '/cadastrar',
			data : $scope.cadastroForm
		}).then(function mySuccess(response) {
			$scope.msg = response.data
		}, function myError(response) {
			$scope.msg = response.data
		});
	}
	
});