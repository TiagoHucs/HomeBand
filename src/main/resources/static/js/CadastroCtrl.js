var app = angular.module('cadastroApp', []);
app.controller('cadastroCtrl', function($scope,$http) {
	
	$scope.senhasDiferentes = ($scope.senha == $scope.senha2);

});