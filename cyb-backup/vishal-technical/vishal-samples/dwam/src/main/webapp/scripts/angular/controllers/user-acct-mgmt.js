userAcctMgmtModule.controller('UserFormCtrl', function($scope, $rootScope,
		$http, $location, userService, $route, $templateCache, $timeout) {

	$scope.user = {};
	$scope.divxTermsConf = false;
	$scope.signupDivxTermsError = false;
	$scope.signupResponseError = false;
	$scope.errorMessage = '';
	$scope.showError = false;

	$scope.register = function(form) {
		
		$scope.firstNameError = false;
		$scope.lastNameError = false;
		$scope.emailNameError = false;
		$scope.passwordError = false;
		$scope.rePasswordError = false;
		$scope.passwordNotMatchError = false;
		$scope.divxTermsConfError = false;
		
		if(!form.$valid || !$scope.divxTermsConf){
			
			if(!form.firstName.$valid){
				$scope.firstNameError = true;
	    	}
	    	if(!form.lastName.$valid){
	    		$scope.lastNameError = true;
	    	}
	    	if(!form.email.$valid){
	    		$scope.emailNameError = true;
	    	}
	    	if(!form.password.$valid){
	    		$scope.passwordError = true;
	    	}
	    	if(!form.repassword.$valid){
	    		$scope.rePasswordError = true;
	    	}
	    	if ($scope.repassword != $scope.user.password){
	    		$scope.passwordNotMatchError = true;	        	
	        }
	    	if(!$scope.divxTermsConf){
	    		$scope.divxTermsConfError  = true;
	    	}
	    	
		}
		else{
			$http({
				method : 'POST',
				url : 'registerUser.do',
				data : $scope.user,
				headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
			}).success(function(data) {
				$scope.signupFlag = true;
			}).error(function(data, status, headers, config) {
				$scope.showError = true;
				$scope.errorMessage = data;
			});
			
		}
	};

	$scope.updateUser = function(form) {
		
		$scope.firstNameError = false;
		$scope.lastNameError = false;
		$scope.emailNameError = false;
		$scope.currentPasswordOnEmailChangeError = false;
		$scope.passwordNotMatchError = false;
		$scope.currentPasswordRequired = false;
		$scope.currentPasswordError = false;
		$scope.newPasswordError = false;
		$scope.rePasswordError = false;
	
		if(!form.$valid){
			if(!form.firstName.$valid){
				$scope.firstNameError = true;
	    	}
	    	if(!form.lastName.$valid){
	    		$scope.lastNameError = true;
	    	}
	    	if(!form.email.$valid){
	    		$scope.emailNameError = true;
	    	}
	    	if(!form.password.$valid){
	    		$scope.currentPasswordError =  true;
	    	}
	    	if(!form.newPassword.$valid){
	    		$scope.newPasswordError = true;
	    	}
	    	if(!form.repassword.$valid){
	    		$scope.rePasswordError =  true;
	    	}
	    	
	    	return;
		}
		if ($scope.user.password == null && $scope.user.newPassword != null) {
			$scope.currentPasswordRequired = true;
			return;
		}
		if ($scope.user.repassword != $scope.user.newPassword) {
			$scope.passwordNotMatchError = true;
			return;
		}
	
		if (!userService.isDirty($scope.user)) {
			return;
		}
		;

		if (userService.isEmailDirty($scope.user.email)) {
			if ($scope.user.password == undefined || $scope.user.password == ''
					|| form.password.$invalid) {
				$scope.currentPasswordOnEmailChangeError = true;
				return;
			}
		}
		
			$http({
				method : 'POST',
				url : 'updateUser.do',
				data : $scope.user,
				headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
			}).success(function(data) {
				userService.markUnloaded();
				$rootScope.navigateTo("update_account_success");
			}).error(function(data, status, headers, config) {
				$scope.showError = true;
				$scope.errorMessage = data;
			});	
		
	};

	$rootScope.$on('UserLoadedEvent',function(scope,user){
		$scope.user = angular.copy(user);
	});
	
	$scope.showLogin = function() {
		$rootScope.navigateTo("show_login");
	};
	
	if($location.path().indexOf('update_account') !== -1){
		$scope.user = angular.copy(userService.getUser());
	}
});

userAcctMgmtModule.controller('accountVerifiedCtrl', function($rootScope,
		$scope,$route, $http, $location,$window) {
	
	$scope.user = {};
	
	$scope.verified=false;
	$scope.responseError = false;
	$scope.user.email =  $route.current.params.email;
	$scope.user.secKey = $route.current.params.secKey;

		$http({
			method : 'POST',
			url : 'accountVerified.do',
			data : $scope.user,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json,text/plain'
			}
		}).success(function(data) {
			$scope.verified=true;
			
		}).error(function(data, status, headers, config) {
			$scope.responseError = true;
			$scope.errorMessage = data;
		});
	

	$scope.login = function() {
		//$rootScope.navigateTo("show_login");
		$window.location.href="home.htm";
	};
});

userAcctMgmtModule.controller('LoginCtrl', function($rootScope, $scope, $http,
		$location, $window, $route, $timeout, $templateCache) {

	$scope.loginData = {};
	$scope.loginReponseError = "";
	$scope.showError = false;

	$scope.submitForm = function(form) {
		
		$scope.emailError = false;
		$scope.passwordError = false;
		
		if(!form.$valid){
			if(!$scope.loginData.j_username){
				$scope.emailError = true;
				$("#email").focus();
				
			}
			if(!$scope.loginData.j_password){
				$scope.loginData.j_password='';
				$scope.passwordError = true;
				$("#password").focus();
				
			}
			return;
		}
		else{
			
			$http({
				method : 'POST',
				url : 'j_spring_security_check',
				data : $.param($scope.loginData),
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(userDashboard) {
				$rootScope.navigateTo(userDashboard);
			}).error(function(data, status, headers, config) {
				$scope.loginReponseError = data;
				$scope.showError = true;
			});
			
		}
		
	};

	$scope.createCustomer = function() {
		$rootScope.navigateTo("user_signup");
	};
	
});

userAcctMgmtModule.controller('ResetPasswordCtrl', function($scope, $rootScope,
		$http, $location, userService) {

	$scope.user = {};
	$scope.showError = false;
	
	$scope.errorMessage = '';
	$scope.submit = function(form) {
		$scope.invalidEmail = false;
		$scope.resetPasswordNotifReponseError = false;
		
		if (!form.$valid) {
			if(!form.email.$valid){
				$scope.invalidEmail = true;
				return;
			}
		}
		
		$http({
			method : 'POST',
			url : 'forgotPasswordRequest.do',
			data : $scope.user,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		}).success(function(data) {
			userService.setUser($scope.user);
			$rootScope.navigateTo("reset_password_notif_conf");
		}).error(function(data, status, headers, config) {
			$scope.resetPasswordNotifReponseError = true;
			$scope.showError = true;
			$scope.errorMessage = data;
		});
	};

	$scope.login = function() {
		$rootScope.navigateTo("show_login");
	};

	$scope.email = function() {
		return userService.getEmail();
	};

});
userAcctMgmtModule.controller('ResetPasswordPageCtrl', function($scope,
		$rootScope, $route, $http, $location, userService) {

	$scope.user = {};
	$scope.showError = false;
	$scope.user.email = $route.current.params.email;
	$scope.user.secKey = $route.current.params.secKey;
	$scope.submit = function(form) {
		
		$scope.newPasswordError =  false;
		$scope.confirmPasswordError = false;
		$scope.passwordNotMatchError = false;
		
		if(!form.$valid){
			
			if(!form.newPassword.$valid){
	    		$scope.newPasswordError =  true;
	    	}
	    	if(!form.confirmPassword.$valid){
	    		$scope.confirmPasswordError = true;
	    	}
			return;
		}
		
		if ($scope.user.newPassword != $scope.user.confirmPassword) {
			$scope.passwordNotMatchError = true;
			return;
		}
		
		$http({
			method : 'POST',
			url : 'reset_password.do',
			data : $scope.user,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json,text/plain'
			}
		}).success(function(data) {
			$rootScope.navigateTo("confirm_password_active");
		}).error(function(data, status, headers, config) {
			$scope.showError = true;
			$scope.errorMessage = data;
		});
	};

	$scope.login = function() {
		$rootScope.navigateTo("show_login");
	};

	$scope.email = function() {
		return userService.getEmail();
	};

});

userAcctMgmtModule.controller('ResetPasswordConfirmCtrl', function($rootScope,
		$scope, $http, $location,$window) {
	$scope.user = {};
	$scope.login = function() {
		$window.location.href="home.htm";
	};
});

userAcctMgmtModule.controller('UserDashboardCtrl', function($scope, $rootScope,
		$http, $location, userService) {
	$scope.customer = {};
	$rootScope.loggedIn = true;
	$scope.loadCustomerResponseError = false;
	if(userService.isLoaded()){
		$scope.customer = userService.getUser();
	}else{
	userService.loadUser().success(function(data) {
		userService.setUser(data);
		userService.markLoaded();
		$rootScope.$emit('UserLoadedEvent',data);
		$scope.customer = userService.getUser();
		
	}).error(function(data, status, headers, config) {
		$scope.loadCustomerResponseError = true;
	});
	}

});

userAcctMgmtModule.$inject = [ 'userService' ];
