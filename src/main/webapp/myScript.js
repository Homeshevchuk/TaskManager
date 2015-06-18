/**
 * Created by HomePC on 17.06.2015.
 */
var app = angular.module('application', []);
app.controller('mainController', function ($scope, $http) {
    $scope.idSelectedVotes = [];
    $http.get("http://localhost:8080/getTasks")
        .success(function (data) {
            $scope.tasks = data;
        });
    $scope.addTask = function (keyEvent, value) {
        if (keyEvent.which === 13) {
            $http({
                method: "post",
                url: "http://localhost:8080/addTask",
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
                url: "http://localhost:8080/deleteTasks",
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
                url: "http://localhost:8080/editTasks",
                data: toEdit
            });
            
      
    };
});