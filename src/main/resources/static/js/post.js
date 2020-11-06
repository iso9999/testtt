var myApp = angular.module('myApp', []);
myApp.controller('postCtrl', function($http,$scope) {
	$scope.json = {};
		$scope.init = function(j){
			$scope.json = JSON.parse(j);
			
		
		  
		    $('.dropdown-mul-1').dropdown({
		      data: $scope.json.data,
		      limitCount: 40,
		      multipleMode: 'label',
		      choice: function () {
		        // console.log(arguments,this);
		      }
		    });
		}
	   $('.dropdown-mul-1 div div span input').on("focusout",function(){
	    	$(".dropdown-main").hide();
	    })
	  
	    $('.dropdown-mul-1').on("click",function(){
	      console.log("click");
	      $('.dropdown-mul-1 div div span input').val("");
	      $(".dropdown-main").hide();
	     });
	   
	   $(function(){
		   $('.dropdown-mul-1>div>div>span>input').on("keyup",function(){
		    	if($.trim($(this).val())===''){
		    		$(".dropdown-main").hide();	
		    		}
		    	else{
		    		$(".dropdown-main").show();
		    	}
		      
		     });
	   });

	    $(".optValue").text($("#selectbasic option:selected").val());
	     if($("#selectbasic option:selected").val() == "Days"){
	     	$(".hourPrice").hide();
	     }
	     else{
	     	$(".hourPrice").show();
	     }
		$("#selectbasic").on("change",function(){
			$(".optValue").text($("#selectbasic option:selected").val());
			if($("#selectbasic option:selected").val() == "Days"){
		     	$(".hourPrice").hide();
		     }
		     else{
		     	$(".hourPrice").show();
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
			console.log($scope.conversations);
			
		}, function(reason) {
			console.log(reason);
		}, function(value) {
			console.log(value);
		})
	}
});