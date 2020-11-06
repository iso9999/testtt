/**
 * 
 */
var myApp = angular.module('myApp',[]);
myApp.controller('convCtrl',function($scope,$http, $interval){
	
	$scope.conversations = [];
	
	$scope.init=function(id,from){
		getAllConvs(id);
		$(".messages").animate({ scrollTop: $(document).height() }, "fast");
		var stompClient;
	    var socket = new SockJS('/ws');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/usermsg/'+from+'/topic/msg', function (msg) {
	            getAllConvs(id);
	        });
	    });
	}
	var getAllConvs = function(id){
		$http({
			method: "GET",
			url: "getAllConv",
			params:{
				idClient: id
			}
		}).then(function(value) {
			$scope.conversations = value.data;
			
		}, function(reason) {
			console.log(reason);
		}, function(value) {
			console.log(value);
		})
	}
});