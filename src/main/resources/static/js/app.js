var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "views/inicio.html",
        controller : "inicioCtrl"
    })
    .when("/perfil", {
        templateUrl : "views/perfil.html",
        controller : "perfilCtrl"
    })
    .when("/composicao/{id}", {
        templateUrl : "views/composicao.html",
        controller : "composicaoCtrl"
    })
    .when("/composicoes", {
        templateUrl : "views/composicoes.html",
        controller : "composicoesCtrl"
    })
    .when("/novacomposicao", {
        templateUrl : "views/novaComposicao.html",
        controller : "novaComposicaoCtrl"
    })
    .when("/amigos", {
        templateUrl : "views/amigos.html",
        controller : "amigosCtrl"
    })
    .when("/producao", {
        templateUrl : "views/producao.html",
        controller : "producoesCtrl"
    });;
});