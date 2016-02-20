/**
 * Created by HomePC on 17.06.2015.
 */
var app = angular.module('application', ['ngRoute','ngMaterial','ngMdIcons','ngAnimate','ngAnimate','ngMessages','ngLetterAvatar']);

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
        .primaryPalette('green').accentPalette('green');

    });
app.controller('mainController', function ($rootScope,$scope, $http,$mdDialog,$location,$mdSidenav) {
    $scope.adding = false;
    $scope.customColors=["#81C784"];
    if(!$rootScope.authorized){
        $location.path("/login");
    }

    load();
    function load() {
            console.log("load");
            $http.get("/userData")
                .success(function (data) {
                    console.log("get");
                    $scope.tasks = data.taskList;
                    $rootScope.user.username  = data.username;
                    $rootScope.user.loaded = true;
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

        $http.get("/userData", {headers : headers}).success(function(data) {
            $location.path("/home");
            $rootScope.authorized = true;
            $rootScope.user = {name:data.username};
        }).error(function() {
            $rootScope.authorized = false;
        });
    }
    $scope.authorization();
});
app.controller('navBar', function($rootScope,$scope,$http,$location,$mdSidenav) {
    $scope.logout = function(){
        $http.post('/logout', {}).finally(function() {
            $rootScope.authorized = false;
            $location.path("/login");
        });
    };

    $scope.openLeftMenu = function() {
        $mdSidenav('left').toggle();
        $http({
            method: "get",
            url: "/getFriends"
        }).success(function (data){
            $scope.user.friends = data;

        });
    };
    $scope.addFriend = function(val){

        $http({
            method: "post",
            url: "/addFriend",
            data: val
        }).success(function(){
            $scope.user.friends.requestsFromUser.push(val);

        });
        $scope.friendName = ""
    }
    $scope.acceptFriendRequest = function(index){

        $http({
            method: "post",
            url: "/addFriend",
            data: $scope.user.friends.requestsToUser[index]
        }).success(function(){
            $scope.user.friends.friends.push($scope.user.friends.requestsToUser[index]);
            $scope.user.friends.requestsToUser.splice(index,1);

        });


    }
    $scope.deleteFriend = function(index){
        $http({
            method: "post",
            url: "/deleteFriend",
            data: $scope.user.friends.friends[index]
        }).success(function(){
            $scope.user.friends.friends.splice(index,1);

        });


    }

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
                $location.path("/login");
            }).error(function (data) {
                console.log(data.value);
            });
        }else{
        }
    };
});

