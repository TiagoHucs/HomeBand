app.controller('novaComposicaoCtrl', function($scope, $http,$window) {

	 
	 $scope.incluircomposicao = function() {
		$http({
			url : '/salvarMinhaComposicao',
			method : 'POST',
			data : $scope.novaComposicao
		}).then(function mySuccess(response) {
			$window.location.href = '#!composicoes';
		}, function myError(response) {
			console.log('erro ao atualizar salvar composicao')
		});

	};

});