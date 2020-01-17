var wafepaApp = angular.module("wafepaApp",["ngRoute"]);

wafepaApp.controller("HomeCtrl", function($scope){
	$scope.message="Hello JWD40";
});

wafepaApp.controller("ActivitiesCtrl", function($scope, $http, $location){
	
	var url = "/api/activities";
	
	$scope.activities = [];
	
	var getActivities = function() {
		var promise = $http.get(url);
		promise.then(
			function success(res) {
				$scope.activities = res.data;
			},
			function error(res) {
				alert("Coldn't fetch activities.");
			}
		);
	}
	
	getActivities();
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id);
	}
	
	$scope.goToAdd = function() {
		$location.path("/activities/add");
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getActivities();
			},
			function error(){
				alert("Couldn't delete the activity.");
			}
		);
	}
});

wafepaApp.controller("AddActivityCtrl", function($scope, $http, $location) {
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var url = "/api/activities";
	
	$scope.doAdd = function() {
		$http.post(url, $scope.activity).then(
			function success() {
				$location.path("/activities");
			},
			function error() {
				alert("Couldn't save the activity.");
			}
		);
	}
});

wafepaApp.controller("EditActivityCtrl", function($scope, $http, $routeParams, $location) {
	
	var url = "/api/activities/" + $routeParams.aid;
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function(){
		var promise = $http.get(url);
		promise.then(
			function uspeh(odg){
				$scope.activity = odg.data;
			},
			function neuspeh(odg) {
				alert("Couldn't fetch the activity.");
			}
		);
	}
	
	getActivity();
	
	$scope.doEdit = function() {
		var promise = $http.put(url, $scope.activity);
		promise.then(
			function success(res) {
				$location.path("/activities")
			},
			function error() {
				alert("Couldn't save the activity");
			}
		);
	}
});

wafepaApp.controller("UsersCtrl", function($scope, $http, $location){
	
	var url = "/api/users";
	
	$scope.users = [];
	
	var getUsers = function(){
		var promise = $http.get(url);
		promise.then(
			function success(res){
				$scope.users = res.data;
				// console.log("test1");
				// console.log(res);
			},
			function error(res){
				alert("Couldn't fetch activities.");
			}
		);
		// console.log("test2");
	}
	
	getUsers();
	
	$scope.goToEdit = function(id){
		$location.path("/users/edit/" + id);
	}
	
	$scope.goToAdd = function(){
		$location.path("/users/add");
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getUsers();
			},
			function error(){
				alert("Couldn't delete the userss.");
			}
		);
	}
	
});

wafepaApp.controller("EditUserCtrl", function($scope,$http, $routeParams, $location){
	// console.log($routeParams);
	
	var url = "/api/users/" + $routeParams.id;
	
	$scope.user = {};
	$scope.user.firstName = "";
	$scope.user.lastName = "";
	
	
	var getUser = function(){
		var promise = $http.get(url);
		promise.then(
			function uspeh(odg){
				$scope.user = odg.data;
			},
			function neuspeh(odg){
				alert("Couldn't fetch the user.");
			}
		);
	}
	
	getUser();
	
	$scope.doEdit = function(){
		var promise = $http.put(url, $scope.user);
		promise.then(
			function success(res){
				$location.path("/users");
			},
			function error(){
				alert("Couldn't save the user");
			}
		);
	}
	
});

wafepaApp.controller("AddUserCtrl", function($scope, $http, $location){
	
	$scope.user = {};
	$scope.user.firstName = "";
	$scope.user.lastName = "";
	$scope.user.email = "";
	$scope.user.password = "";
	$scope.user.password2 = "";
	
	var url = "/api/users";
	
	$scope.doAdd = function(){
		$http.post(url, $scope.user).then(
			function success(){
				$location.path("/users");
			},
			function error(){
				alert("Couldn't save the activity.");
			}
		);
	}
	
});

wafepaApp.controller("RecordCtrl", function($scope, $http, $location) {
		
	var url = "/api/records";
	var urlActivities = "/api/activities";
	var urlUsers = "/api/users";
	
	$scope.records = [];
	$scope.activities = [];
	$scope.users = [];
	
	$scope.newRecord = {};
	$scope.newRecord.time = "";
	$scope.newRecord.duration = "";
	$scope.newRecord.intensity = "";
	$scope.newRecord.activityId = "";
	$scope.newRecord.userId = "";
	
	// TODO: dodati obeležja kojim se povezuje sa korisnikom i aktivnošću

	var getRecords = function(){
		var promise = $http.get(url);
		promise.then(
			function success(res){
				$scope.records = res.data;
			},
			function error(){
				alert("Couldn't fetch records");
			}
		);
	}
	
	getRecords();
	
	// TODO: Obezbediti prihvat korisnika i aktivnosti
	var getActivities = function(){
		var promise = $http.get(urlActivities);
		promise.then(
			function success(res){
				$scope.activities = res.data;
			},
			function error(res){
				alert("Couldn't fetch activities");
			}
		);
	}
	
	getActivities();
	
	
	
	var getUsers = function(){
		var promise = $http.get(urlUsers);
		promise.then(
			function success(res){
				$scope.users = res.data;
			},
			function error(res){
				alert("Couldn't fetch users");
			}
		);
	}
	
	getUsers();
	
$scope.doAdd = function(){
		
		$http.post(url, $scope.newRecord).then(
			function success(res){
				getRecords();
	
			},
			function error(){
				alert("Couldn't save the record");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/records/edit/" + id);
	}
});


wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/add-activity.html'
		})
		.when('/activities/edit/:aid', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.when('/users', {
			templateUrl : '/app/html/users.html'
		})
		.when('/users/edit/:id', {
			templateUrl : '/app/html/edit-user.html'
		})
		.when('/users/add', {
			templateUrl : '/app/html/add-user.html'
		})
		.when('/records', {
			templateUrl : '/app/html/records.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);