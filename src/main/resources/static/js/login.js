/**
 * 
 */
var app = angular.module('myApp',[]);
app.controller('loginCtrl',function($scope,$http, $interval){
	var test1 = false;
	var test2 = false;
	var test3 = false;
	var test4 = false;
	var test5 = false;
	var test6 = false;
	var hasNumber = /\d/;
	$scope.usernames = [];
	$scope.userName = "";
	$scope.firstName = "";
	$scope.lastName = "";
	$scope.address = "";
	$scope.city = "";
	$scope.password = "";
	$scope.re_password = "";
	$scope.title = "";
	$scope.summary = "";
	$scope.email = "";
	$scope.emails = [];
	$scope.init=function(u,e){
		$(".error").hide();
		$(".success").hide();
		$scope.city = $("#selectCity option:selected").val();
		$scope.usernames = JSON.parse(u);
		$scope.emails = JSON.parse(e);
		}
	$scope.usernameCheck = function(e){
		if($scope.usernames.length>0){
			for( var i=0; i < $scope.usernames.length; i++){
				if($scope.userName == $scope.usernames[i].username){
					$(".user").addClass("usernameError");
					$(".userError").show();
					$(".user").removeClass("Success");
					test1 = false;
					break;
				}
				
				else if(!($scope.userName == $scope.usernames[i].username) && $scope.userName && !/\s/.test($scope.userName)){
					$(".user").removeClass("usernameError");
					$(".userError").hide();
					$(".user").addClass("Success");
					test1 = true;
				}
				
				if(!$scope.userName ){
					$(".user").addClass("usernameError");
					$(".userEmptyError").show();
					$(".user").removeClass("Success");
					test1 = false;
					break;
				}
				
				else{
					$(".userEmptyError").hide();
				}
				
				if(/\s/.test($scope.userName)){
					$(".user").addClass("usernameError");
					$(".userBlankError").show();
					$(".user").removeClass("Success");
		            test1 = false;
				}
				
				else{
					$(".userBlankError").hide();
				}
			}
		}
		else{
			$(".user").addClass("Success");
			test1=true;
		}
		enableButton();
	}
	
	
	$scope.emailCheck = function(){
		if($scope.emails.length>0){
			for( var i=0; i < $scope.emails.length; i++){
				if($scope.email == $scope.emails[i].email){
					$(".email").addClass("usernameError");
					$(".emailError").show();
					$(".email").removeClass("Success");
					test2 = false;
					break;
				}
				else{
					$(".email").removeClass("usernameError");
					$(".emailError").hide();
					$(".email").addClass("Success");
					test2 = true;
				}
				
				if($scope.email ==undefined){
					$(".email").addClass("usernameError");
					$(".emailValidError").show();
					$(".email").removeClass("Success");
					test2 = false;
					break;
				}
				else{
					$(".email").removeClass("usernameError");
					$(".emailValidError").hide();
					$(".email").addClass("Success");
					test2 = true;
				}
				
			}
		}
		else{
			$(".email").addClass("Success");
			test2 = true;
		}
		enableButton();
	}
	
	$scope.firstCheck = function(){
			if(!$scope.firstName){
				$(".first").addClass("usernameError");
				$(".firstEmptyError").show();
				$(".first").removeClass("Success");
				test3 = false;
			}
			else{
				$(".first").removeClass("usernameError");
				$(".firstEmptyError").hide();
				$(".first").addClass("Success");
			}
			
			if(hasNumber.test($scope.firstName)){
				$(".first").addClass("usernameError");
				$(".firstNumberError").show();
				$(".first").removeClass("Success");
				test3 = false;
			}
			else if(!hasNumber.test($scope.firstName) && $scope.firstName){
				$(".firstNumberError").hide();
				test3 = true;
				
			}
			enableButton();
	}
	
	$scope.lastCheck = function(){
				
		if(!$scope.lastName){
			$(".last").addClass("usernameError");
			$(".lastEmptyError").show();
			$(".last").removeClass("Success");
			test4 = false;
		}
		else{
			$(".last").removeClass("usernameError");
			$(".lastEmptyError").hide();
			$(".last").addClass("Success");
		}
		
		if(hasNumber.test($scope.lastName)){
			$(".last").addClass("usernameError");
			$(".lastNumberError").show();
			$(".last").removeClass("Success");
			test4 = false;
		}
		else if(!hasNumber.test($scope.lastName) && $scope.lastName){
			$(".lastNumberError").hide();
			test4 = true;
		}
		enableButton();
	}
	
	
		
	$("#selectCity").on("change",function(){
		$scope.city = $("#selectCity option:selected").val();
	});
		
	$scope.passwordCheck = function(){
		if(!$scope.password){
			$(".password").addClass("usernameError");
			$(".passwordEmptyError").show();
			test5 = false;
		}
		else{
			$(".password").removeClass("usernameError");
			$(".passwordEmptyError").hide();
			test5 = true;
			
		}
		
		if((!$scope.re_password || $scope.re_password !==$scope.password) && $scope.re_password){
			$(".re_password").addClass("usernameError");
			$(".re_passwordEmptyError").show();
			$(".re_passwordEmptySuccess").hide();
			$(".re_password").removeClass("Success");
			$(".password").removeClass("Success");
			test5 = false;
			
		}
		else if($scope.re_password ===$scope.password && $scope.re_password){
			$(".re_password").removeClass("usernameError");
			$(".re_passwordEmptyError").hide();
			$(".re_passwordEmptySuccess").show();
			$(".re_password").addClass("Success");
			$(".password").addClass("Success");
			test5 = true;
			test6 =true;
			
		}
		
		enableButton();
	}
	
	$scope.re_passwordCheck = function(){
		if(!$scope.re_password || $scope.re_password !==$scope.password){
			$(".re_password").addClass("usernameError");
			$(".re_passwordEmptyError").show();
			$(".re_passwordEmptySuccess").hide();
			$(".re_password").removeClass("Success");
			$(".password").removeClass("Success");
			test6 = false;
		}
		else{
			$(".re_password").removeClass("usernameError");
			$(".re_passwordEmptyError").hide();
			$(".re_passwordEmptySuccess").show();
			$(".re_password").addClass("Success");
			$(".password").addClass("Success");
			test6 = true;
			
		}
		enableButton();
	}	
			
	var enableButton = function(){
		if(test1 && test2 && test3 && test4 && test5 && test6 && $scope.email && $scope.userName && $scope.firstName && $scope.lastName  && $scope.password && $scope.re_password){
			$(".sub").prop('disabled', false);
		}
		else{
			$(".sub").prop('disabled', true);
		}
	}
	
	/*$(function() {
	    $('.user').on('keypress', function(e) {
	        if (e.which == 32){
	        	$(".user").addClass("usernameError");
				$(".userBlankError").show();
				$(".user").removeClass("Success");
	            test1 = false;
	        }
	    });
	});*/
	
	/*$(".radioInput").on("change",function(){
		$("#frmSignUp").attr("action","/"+$(".radioInput:checked").val()+"/register");
	});*/
	
	$scope.singup=function(){
		var role=$(".radioInput:checked").val();
		var info= {"role":role,"username":$scope.userName, "email":$scope.email, "first":$scope.firstName, "last":$scope.lastName, "city":$scope.city,"address":$scope.address,"password":$scope.password,"title":$scope.title,"summary":$scope.summary};
		$http({
			method: "POST",
			url: "singUp",
			params:{
				info: JSON.stringify(info)
			}
			
		}).then(function(value) {
			if(role=="CLIENT"){
			window.location.replace("/"+role+"/profile");
			}
			else{
				window.location.replace("/"+role+"/register");
			}
			
		}, function(reason) {
			console.log(reason);
		}, function(value) {
			console.log(value);
		})
	}
	})