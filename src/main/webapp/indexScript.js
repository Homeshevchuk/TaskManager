/**
 * Created by HomePC on 17.06.2015.
 */
var app = angular.module('application', ['ngRoute','ngMaterial','ngMdIcons','ngAnimate','ngAnimate']);
app.config(function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode({
        enabled:true,
        requireBase:false
    });
        $routeProvider.when('/', {
            templateUrl : 'home.html',
            controller : 'mainController'
        }).when('/login', {
            templateUrl : 'Login.html',
            controller : 'authorization'
        }).otherwise({redirectTo:'/'});

    });
app.controller('mainController', function ($scope, $http, $rootScope,$mdDialog) {
    $rootScope.active = true;
    $scope.adding = false;
    $scope.idSelectedVotes = [];
    $scope.fuckingBar = {
        count: 0,
        isOpen: false,
        selectedDirection: 'left'

    };
    $scope.toHide = [];
    $http.get("/Task/getTasks")
        .success(function (data) {
            $scope.tasks = data;
            var l = $scope.tasks.length;
        });


    $scope.delete = function (index) {
        $http({
            method: "post",
            url: "/Task/deleteTask",
            data: $scope.tasks[index]
        });
        $scope.tasks.splice(index, 1);
    };

    $scope.showAdvanced = function (ev,index) {
        if(index != null){
            $scope.adding = false;
            $scope.index = index;
            $scope.inputValue = $scope.tasks[index].task;
        }else{
            $scope.adding = true;
        }
        $mdDialog.show({
            scope:$scope,
            preserveScope:true,
            templateUrl: 'addTaskDialog.html',
            parent: angular.element(document.body),
            targetEvent: ev,

        });
        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.editTask = function (value) {
            $http({
                method: "post",
                url: "/Task/editTask",
                data: $scope.tasks[index]
            })
            $mdDialog.hide();
        };
       /* $scope.cancel = function() {

            $mdDialog.cancel();
        };*/
        $scope.addTask = function (value) {
            $http({
                method: "post",
                url: "/Task/addTask",
                data: {task : value}
            }).success(function (data) {
                $scope.tasks.push({number: data.number, task: data.task});
            });
            $scope.inputValue = "";
            $mdDialog.hide();
        };
        $scope.cancel = function() {
            if(index!=null){
                $scope.tasks[index].task = $scope.inputValue;
            }

            $mdDialog.cancel();
        };
    };

});
app.controller('navigation', function($rootScope) {
});
app.controller('authorization', function($rootScope) {
    $rootScope.active = false;
});

