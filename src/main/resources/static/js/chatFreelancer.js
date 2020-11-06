/**
 * 
 */
var myApp = angular.module('myApp',[]);
myApp.controller('chatCtrl',function($scope,$http, $interval){
	$scope.conversations = [];
	$scope.init=function(id,to,from){
		getAllConvs(id);
		$(".messages").animate({ scrollTop: $(document).height() }, "fast");
		var stompClient;
	    var socket = new SockJS('/ws');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/usermsg/'+from+'/topic/msg/'+to, function (msg) {
	            showHisMsg(msg.body);
	            getAllConvs(id);
	        });
	        stompClient.subscribe('/usermsg/'+to+'/topic/msg/'+from, function (msg) {
	            showMymsg(msg.body);
	            getAllConvs(id);
	        });
	        stompClient.subscribe('/usermsg/'+from+'/topic/msg', function (msg) {
	            getAllConvs(id);
	        });
	    });

	function sendMsg() {
	    stompClient.send("/app/send", {}, JSON.stringify({'msg': $("#msg").val(), 'to': to, 'from': from}));
	    $(".messages").animate({ scrollTop: $(document).height() }, "fast");
	    $('.message-input input').val(null);
	    getAllConvs(id);
	}

	function showHisMsg(message) {
	    $("#convList").append("<li class='sent'><img src='"+$(".sentimg").attr('src')+"' alt='' /><p>" + message + "</p></li>");
	}
	
	function showMymsg(message) {
	    $("#convList").append("<li class='replies'><img src='"+$(".repliesimg").attr('src')+"' alt='' /><p>" + message + "</p></li>");
	}

	$(function () {
	    $('.submit').click(function(e) {
	    	  e.preventDefault();
	    	});
	    $( "#send" ).click(function() { 
	    	if($.trim($("#msg").val()) != ''){
	    		sendMsg(); }
	    	});
	    
	    $(window).on('keydown', function(e) {
	    	  if (e.which == 13) {
	    		  if($.trim($("#msg").val()) != ''){
	    			  sendMsg();
	    			  return false;
	    		  }
	    	  }
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
myApp.controller('homeCtrl',function($scope,$http, $interval){
	$scope.notifications=[];
	$scope.init = function(id,username){
		connect(username);
			$http({
				method: "GET",
				url: "/FREELANCER/getNotif",
				params: {
					idOffreur: id
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
			url: "/FREELANCER/readAllNotif"
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
	        var notification = new Notification("vous avez un "+titre);
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
		console.log(id);
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
		$http({
			method: "GET",
			url: "/FREELANCER/getAllConv",
			params:{
				idOffreur: id
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