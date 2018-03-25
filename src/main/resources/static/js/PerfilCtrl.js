app.controller('perfilCtrl', function($scope, $http, $route) {
	
	$scope.novoPerfil;
	$scope.isEditando = false;
	$scope.msg;

	$http.get("/getloggedperfil").then(function(response) {
		$scope.perfil = response.data;
		$scope.novoPerfil = $scope.perfil;
		
	});

	$scope.habilitarEdicao = function(){
		$scope.isEditando = true;
	}
	
	$scope.descartarEdicao = function(){
		$route.reload();
	}
	
	$scope.salvarEdicao = function(){
		$scope.novoPerfil.foto = document.getElementById("fotoPerfil").getAttribute("src");
		
		$scope.perfil = $scope.novoPerfil;
		
		$http({
			method : "POST",
			url : '/atualizaPerfilUsuario',
			data : $scope.perfil
		}).then(function mySuccess(response) {
			$route.reload();
		}, function myError(response) {
			console.log('erro ao atualizar perfil')
		});
		
		$scope.isEditando = false;
	}
	
	$scope.trocarSenha = function(){
		$http({
			method : "POST",
			url : '/trocarSenha',
			data : {senhaAnterior:$scope.senhaAnterior,novaSenha1:$scope.novaSenha1,novaSenha2:$scope.novaSenha2}
		}).then(function mySuccess(response) {
			$scope.msg = response.data;
			console.log($scope.msg)
			$('#modal').modal('hide'); 
			$('body').removeClass('modal-open'); 
 			$('.modal-backdrop').remove();
		}, function myError(response) {
			console.log('erro ao trocar senha')
		});
	}

});

function visualizarImg() {

	var preview = document.querySelectorAll('img').item(3);
	var preview2 = document.querySelectorAll('img').item(2);
	var file = document.querySelector('input[type=file]').files[0];	
	var reader = new FileReader();
	
	reader.onloadend = function(){
		preview.src = reader.result;
		preview2.src = reader.result;
	};

	if(file){
		reader.readAsDataURL(file);
	} else {
		preview.src = "";
	}
}