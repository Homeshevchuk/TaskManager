/**
 * Created by HomePC on 17.06.2015.
 */
var app = angular.module('application', ['ngRoute','ngMaterial','ngMdIcons','ngAnimate','ngAnimate','ngMessages']);

app.config(function($routeProvider, $locationProvider,$httpProvider,$mdThemingProvider) {
    $locationProvider.html5Mode({
        enabled:true
    });
        $routeProvider.when('/home', {
            templateUrl : '/home_page.html',
            controller : 'mainController'
        }).when('/login', {
            templateUrl : 'login_page.html',
            controller : 'authorization'
        }).when('/signIn', {
            templateUrl : 'sign_in.html',
            controller : 'signIn'
        }).otherwise({redirectTo:'/login'});
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $mdThemingProvider.theme('default')
        .primaryPalette('red').accentPalette('red');

    });
app.controller('mainController', function ($rootScope,$scope, $http,$mdDialog,$location) {
    $scope.adding = false;

    if(!$rootScope.authorized) {
        $location.path("/login");
    }
    load();
        function load() {
            $http.get("/Task/getTasks")
                .success(function (data) {
                    $scope.tasks = data;
                });
        };
        $scope.delete = function (index) {
            $http({
                method: "post",
                url: "/Task/deleteTask",
                data: $scope.tasks[index]
            });
            $scope.tasks.splice(index, 1);
        };

        $scope.showAdvanced = function (ev, index) {
            if (index != null) {
                $scope.adding = false;
                $scope.index = index;
                $scope.inputValue = $scope.tasks[index].task;
            } else {
                $scope.adding = true;
            }
            $mdDialog.show({
                scope: $scope,
                preserveScope: true,
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
                    data: $scope.tasks[$scope.index]
                })
                $mdDialog.hide();
            };
            $scope.addTask = function (value) {
                $http({
                    method: "post",
                    url: "/Task/addTask",
                    data: {task: value}
                }).success(function (data) {
                    $scope.tasks.push(data);
                });
                $scope.inputValue = "";
                $mdDialog.hide();
            };
            $scope.cancel = function () {
                if (index != null) {
                    $scope.tasks[index].task = $scope.inputValue;
                }

                $mdDialog.cancel();
            };

        };


});
app.controller('authorization', function($rootScope,$scope,$http,$location) {
    $scope.authorization = function(credentials) {

        var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get("/Task/getTasks", {headers : headers}).success(function(data) {
            $location.path("/home");
            $rootScope.authorized = true;
        }).error(function() {
            $rootScope.authorized = false;
        });
    }
    $scope.authorization();
});
app.controller('navBar', function($rootScope,$scope,$http,$location) {
    $scope.hello = function(){
        console.log("hello");
    };
    $scope.logout = function(){
        console.log("asdaD");
        $http.post('/logout', {}).finally(function() {
            $rootScope.authorized = false;
            $location.path("/login");
        });
    };
});
app.controller('signIn', function($rootScope,$scope,$http,$location,$mdToast) {
    $scope.registration = function(credentials){
        if(credentials.password == credentials.passwordRpt) {
            $scope.match = true;
            $http.post("/register", {
                username: credentials.username,
                password: credentials.password
            }).success(function (data) {
                $mdToast.show($mdToast.simple().textContent("Понял, принял!"));
                $location.path("\login");
            }).error(function (data) {
                console.log(data.value);
            });
        }else{
        }
    };
});
