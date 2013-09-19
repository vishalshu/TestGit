'use strict';
var userAcctMgmtModule = angular.module('dwamApp.userAcctMgmt', []);
var deviceAcctMgmtModule = angular.module('dwamApp.deviceAcctMgmt', []);
var ajaxCallInterceptorModule = angular.module('dwamApp.ajaxCallInterceptor',
		[]);
var services = angular.module('dwamApp.services', []);
var app = angular.module('dwamApp',
		[ 'dwamApp.deviceAcctMgmt', 'dwamApp.userAcctMgmt', 'ui','ngGrid' , 'dwamApp.services' ],
		function($locationProvider) {
			$locationProvider.hashPrefix('');
			// Make code pretty
			window.prettyPrint && prettyPrint();
		}).config([ '$routeProvider', function($routeProvider) {

} ]).config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
	var $http, interceptor = [ '$q', '$injector', function($q, $injector) {
		var error;
		 
		function success(response) {
			// get $http via $injector because of circular dependency problem
			$http = $http || $injector.get('$http');
			var templateCache = $injector.get('$templateCache');
			templateCache.removeAll();
			/*if(templateCache.get('getPage.do?pageName=dashboard')){
				templateCache.remove('getPage.do?pageName=dashboard');	
			}
			
			if(templateCache.get('getPage.do?pageName=update_user')){
				templateCache.remove('getPage.do?pageName=update_user');	
			}*/
			
			
			if ($http.pendingRequests.length < 1) {
				$('#loadingWidget').hide();
			}
			return response;
		}

		function error(response) {
			var rootScope = $injector.get('$rootScope');
			
			var templateCache = $injector.get('$templateCache');
			templateCache.removeAll();
			
			if(response.status==403){
				rootScope.navigateTo("\show_login");
			}
			$http = $http || $injector.get('$http');
			if ($http.pendingRequests.length < 1) {
				$('#loadingWidget').hide();
			}
			
			return $q.reject(response);
		}

		return function(promise) {
			$('#loadingWidget').show();
			return promise.then(success, error);
		}
	} ];
/*	var $http, securityInterceptor = ['$q',function($q) {
		  return function(promise) {
			    return promise.then(function(response) {
			      // do something on success
			    }, function(response) {
			      // do something on error
			    	console.print(response);
			      
			      return $q.reject(response);
			    });
			  }
			}];
		*/

	$httpProvider.responseInterceptors.push(interceptor);
//	$httpProvider.responseInterceptors.push(securityInterceptor);
} ]).run(function($rootScope) {
	$rootScope.alertMessage = function(value) {
		alert(value);
	};
});

app.factory('rulesFactory', ['$q', '$timeout',
                             function ($q, $timeout) {
                            
                                var def = $q.defer();
                                $timeout(function () {
                                    def.resolve();
                                }, 100);
                            
                                return def.promise;
                            }
                        ]);

app.config(['$routeProvider','$locationProvider', function($routeProvider, $locationProvider) {
	$routeProvider.when('/', { redirectTo:'/show_login'});
	$routeProvider.when('/show_login', { templateUrl:'showLogin.htm'});
    $routeProvider.when('/user_signup', { templateUrl:'userSignUp.htm'});
    $routeProvider.when('/register_newdevice', { templateUrl:'registerDevice.htm'});
    $routeProvider.when('/download', { templateUrl:'download.htm'});
    $routeProvider.when('/dashboard', { templateUrl:'getPage.do?pageName=dashboard'});
    $routeProvider.when('/update_account', { templateUrl:'getPage.do?pageName=update_user', resolve: ['rulesFactory']});
    $routeProvider.when('/update_account_success', { templateUrl:'getPage.do?pageName=update_user_success'});
    $routeProvider.when('/reset_password_notification', { templateUrl:'resetPasswordNotification.htm'});
    $routeProvider.when('/reset_password_notif_conf', { templateUrl:'resetPasswordNotifConf.htm'});
    $routeProvider.when('/reset_password_page', { templateUrl:'reset_password_page.htm'});
    $routeProvider.when('/confirm_password_active', { templateUrl:'confirm_password_active.htm'});
    $routeProvider.when('/deregister_device', { templateUrl:'deregisterDevice.htm'});
    $routeProvider.when('/deregister_success', { templateUrl:'deregisterSuccess.htm'});
    $routeProvider.when('/delete_device', { templateUrl:'delete_device.htm'});
    $routeProvider.when('/account_verified', { templateUrl:'accountVerified.htm'});
    $routeProvider.when('/delete_device_success', { templateUrl:'delete_device_success.htm'});
    $routeProvider.otherwise({templateUrl:'unknownPage.htm'});
    
    $locationProvider.html5Mode(false).hashPrefix('');
 }]);

app.controller('MainCtrl',function($rootScope, $scope, $http,$location, $filter,$route, $window, $timeout){
	$scope.pageLoaded = false;
	$rootScope.loggedIn=false;
	$rootScope.navigateTo = function(path){
		
		$route.reload();
		$location.path(path);
	};	
});