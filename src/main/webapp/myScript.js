/**
 * Created by HomePC on 17.06.2015.
 */
var app = angular.module('application', []);
app.controller('mainController', function ($scope, $http) {
    $http.get("http://localhost:8080/getTasks")
        .success(function (data) {
            $scope.tasks = data;
        });
    $scope.addTask = function (keyEvent, value) {
        if (keyEvent.which === 13) {
            $scope.tasks.push({number: $scope.tasks.length + 1, task: value});

            $http({
                method: "post",
                url: "http://localhost:8080/addTask",
                data: { task : value }
            });

        }

    }
    $scope.select = function (idSelectedVote) {
        if (idSelectedVote === $scope.idSelectedVote) {
            $scope.idSelectedVote = null;
            return
        }
        $scope.idSelectedVote = idSelectedVote;
    };
    $scope.delete = function () {
        toDelete = $scope.idSelectedVote;
        if (toDelete !== null) {
            $scope.tasks.splice(toDelete - 1, 1);
            for (i = 0; i < $scope.tasks.length; i++) {
                $scope.tasks[i].number = i + 1;
            }
        }
    };
    $scope.edit = function () {
        toEdit = $scope.idSelectedVote;
        if (toEdit !== null) {
            $scope.tasks[toEdit - 1].task = $scope.task;
        }
    };
});