/**
 * 
 */
var myApp = angular.module('myApp',[]);
myApp.controller('chatCtrl',function($scope,$http, $interval){
	$scope.conversations = [];
	$scope.message ={};
	$scope.comment="";
	$scope.rank=0;
	$scope.idOffreur="";
	$scope.init=function(id,from,to){
		var stompClient;
	    var socket = new SockJS('/ws');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/usermsg/'+from+'/topic/msg/'+to, function (msg) {
	        });
	        stompClient.subscribe('/usermsg/'+to+'/topic/msg/'+from, function (msg) {
	        });
	        stompClient.subscribe('/usermsg/'+from+'/topic/msg', function (msg) {
	        });
	    });

	function sendMsg() {
	    stompClient.send("/app/send", {}, JSON.stringify({'msg': $scope.message, 'to': to, 'from': from}));
	    $("#myModal").modal('hide');
	    $(".modal-backdrop").hide();
	    $("#modalConfirm").modal('show');
	    
	}


	$(function () {
	    $('.hireNow').click(function(e) {
	    	  e.preventDefault();
	    	  $scope.message = $('.msg').val()+ "my budget: '"+$('.price').val()+"' for a period: "+$('.period').val()+' '+$("#selectbasic option:selected").val();
	    	  if($.trim($(".msg").val()) != '' && $.trim($(".price").val()) != '' && $.trim($(".period").val()) != ''){
	    		 sendMsg();
	    	  }
	    	  
	    	});
	    
	});
	}
	
	$(".bnt-avis").on("click", function() {
		$scope.comment=$(".cmt").val();
		$scope.idOffreur=$(".idOffreur").val();
		if($scope.comment.trim() != "" && $scope.idOffreur != null && $scope.rank != null){
			$http({
				method:"POST",
				url:"/CLIENT/setAvis",
				params:{
					comment: $scope.comment,
					rank: $scope.rank,
					idOffreur:$scope.idOffreur
				}
			}).then(function(value) {
				window.location.reload();
			}, function(reason) {
				console.log(reson);
			}, function(value) {
				console.log(value);
			})
			
		}
		else{
			console.log("error");
		}
	});
	$(document).ready(function() {
            var ratings = document.getElementsByClassName('rating');

            for (var i = 0; i < ratings.length; i++) {
                var r = new SimpleStarRating(ratings[i]);

                ratings[i].addEventListener('rate', function(e) {
                    console.log('Rating: ' + e.detail);
                    $scope.rank=e.detail;
                });
            }
	});
	
});
myApp.controller('homeCtrl',function($scope,$http, $interval){
	$scope.notifications=[];
	$scope.init = function(id,username){
		connect(username);
			$http({
				method: "GET",
				url: "/CLIENT/getNotif",
				params: {
					idClient: id
				}
			}).then(function(value) {
				$scope.notifications = value.data;
				angular.forEach($scope.notifications, function(value, key) {
					  if(value.etat ==false){
						  $(".not").removeClass("icon-bell");
						  $(".not").addClass("glyphicon glyphicon-bell");
						  fctNotif(value.titre);
					  }
					});
			}, function(reason) {
				console.log(reason);
			}, function(value) {
				console.log(value);
			})
	}
	$(".not").on("click",function(){
		$http({
			method: "POST",
			url: "/CLIENT/readAllNotif"
		}).then(function(value) {
			console.log(value);
			
		}, function(reason) {
			console.log(reason);
		}, function(value) {
			console.log(value);
		})
	});
	var fctNotif = function(titre){
	if (!("Notification" in window)) {
	    alert("This browser does not support desktop notification");
	  }

	  // Let's check whether notification permissions have already been granted
	  else if (Notification.permission === "granted") {
	    // If it's okay let's create a notification
	    var notification = new Notification("vous avez un "+titre);
	  }

	  // Otherwise, we need to ask the user for permission
	  else if (Notification.permission !== "denied") {
	    Notification.requestPermission(function (permission) {

	      // If the user accepts, let's create a notification
	      if (permission === "granted") {
	        var notification = new Notification(titre);
	      }
	    });
	  }

	  // At last, if the user has denied notifications, and you 
	  // want to be respectful there is no need to bother them any more.
	};
	Notification.requestPermission().then(function(result) {
	});function spawnNotification(body, icon, title) {
	  var options = {
	      body: body,
	      icon: icon
	  };
	  var n = new Notification(title, options);
	};
	
	$scope.readNotif = function(id,url){
		$http({
			method: "POST",
			url: "/readNotif",
			params:{
				idNotif : id
			}
		}).then(function(value) {
			console.log(value);
			window.location.replace(url);
			
		}, function(reason) {
			console.log(reason);
		}, function(value) {
			console.log(value);
		})
	}
	
	var connect = function(username){
		  // Create and init the SockJS object
	    var socket = new SockJS('/ws');
	    var stompClient = Stomp.over(socket);
	    // Subscribe the '/notify' channell
	    stompClient.connect({}, function(frame) {
	      stompClient.subscribe('/usermsg/'+username+'/queue/notify', function(notification) {
	        // Call the notify function when receive a notification
	    	  fctNotif(JSON.parse(notification.body).titre);
	    	  $scope.notifications.push(JSON.parse(notification.body));
	    	  $(".not").removeClass("icon-bell"),
	    	  $(".not").addClass("glyphicon glyphicon-bell");
			  console.log($scope.notifications);
			  $scope.$apply();
	    	  
	    	  
	      });
	    });
	    
	    return;
	}
	
	function notify(message) {
	    console.log(message);
	    return;
	  }
	
	$(".not").on("click",function(){
		$(".not").removeClass("glyphicon glyphicon-bell");
		$(".not").addClass("icon-bell");
	    
	});
	
});
myApp.controller('convCtrl',function($scope,$http, $interval){
	
	$scope.conversations = [];
	
	$scope.init=function(id,from){
		getAllConvs(id);
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
		console.log("helllo");
		$http({
			method: "GET",
			url: "/CLIENT/getAllConv",
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