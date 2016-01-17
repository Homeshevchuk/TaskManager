/**
 * Created by HomePC on 17.06.2015.
 */
var app = angular.module('application', ['ngRoute','ngMaterial','ngMdIcons']);
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
            url: "/Task/deleteTasks",
            data: $scope.tasks[index]
        });
        $scope.tasks.splice(index, 1);
    };
    
    $scope.edit = function () {
        toEdit = [];
        for(i=0;i<$scope.idSelectedVotes.length;i++){
            for(j = 0;j<$scope.tasks.length;j++){
                if($scope.tasks[j].number===$scope.idSelectedVotes[i]){
                   $scope.tasks[j].task = $scope.task; 
                    toEdit.push($scope.tasks[j]);
            }
              
            }
        }
        $scope.idSelectedVotes = [];
        $http({
                method: "post",
                url: "/Task/editTasks",
                data: toEdit
            });
    };

    $scope.showAdvanced = function(ev) {

        $mdDialog.show({
                controller : 'mainController',
                templateUrl: 'dialog.html',
                scope:$scope,
                clickOutsideToClose:true,
            })};
    $scope.hide = function() {
        $mdDialog.hide();
    };
    $scope.addTask = function (value) {
        $http({
            method: "post",
            url: "/Task/addTask",
            data: { task : value}
        }).success(function(data){
          $scope.tasks.push({number: data.number, task: data.task});
        });
        console.log($scope.tasks);
        console.log(value);
        $mdDialog.hide();
    }
    $scope.cancel = function() {
        $scope.tasks.push({number: 123123123, task: 123123123});
        $mdDialog.cancel();
    };
    $scope.answer = function(answer) {
        $mdDialog.hide(answer);
    };

});
app.controller('navigation', function($rootScope) {
});
app.controller('authorization', function($rootScope) {
    $rootScope.active = false;
});
