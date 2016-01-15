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
        count:0,
        isOpen:false,
        selectedDirection: 'left'

    };
    $scope.toHide = [];
    $http.get("/Task/getTasks")
        .success(function (data) {
            $scope.tasks = data;
            var l = $scope.tasks.length;

            while (l--) {
                console.log(l);
               $scope.toHide[l] = true;
            };
            console.log($scope.toHide);
        });
    $scope.hide = function (index) {
        console.log($scope.fuckingBar.count);
        $scope.toHide[index] = !$scope.toHide[index];
    };
    $scope.addTask = function (keyEvent, value) {
        if (keyEvent.which === 13) {
            $http({
                method: "post",
                url: "/Task/addTask",
                data: { task : value }
            }).success(function(data){
                  $scope.tasks.push({number: data.number, task: value});
            });
          

            

        }

    }
    $scope.select = function (idSelectedVote) {
        index = $scope.idSelectedVotes.indexOf(idSelectedVote);
        if (index !== -1){
            $scope.idSelectedVotes.splice(index,1);
            return
        }
        $scope.idSelectedVotes.push(idSelectedVote);
    };
    $scope.userSelected =  function(task){
    return $scope.idSelectedVotes.indexOf(task.number) !== -1;
    };
    $scope.delete = function () {
        toDelete = [];
        for(i=0;i<$scope.idSelectedVotes.length;i++){
            for(j = 0;j<$scope.tasks.length;j++){
                if($scope.tasks[j].number===$scope.idSelectedVotes[i]){
                    toDelete.push($scope.tasks[j]);
                   $scope.tasks.splice(j,1); 
                    
            }
              
            }
        }
        $scope.idSelectedVotes = [];
        $http({
                method: "post",
                url: "/Task/deleteTasks",
                data: toDelete
            });
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
                controller: DialogController,
                templateUrl: 'dialog.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
            })
            .then(function(answer) {
                $scope.status = 'You said the information was "' + answer + '".';
            }, function() {
                $scope.status = 'You cancelled the dialog.';
            })};
});
app.controller('navigation', function($rootScope) {
});
app.controller('authorization', function($rootScope) {
    $rootScope.active = false;
});
function DialogController($scope, $mdDialog) {
    $scope.hide = function() {
        $mdDialog.hide();
    };
    $scope.cancel = function() {
        $mdDialog.cancel();
    };
    $scope.answer = function(answer) {
        $mdDialog.hide(answer);
    };
}