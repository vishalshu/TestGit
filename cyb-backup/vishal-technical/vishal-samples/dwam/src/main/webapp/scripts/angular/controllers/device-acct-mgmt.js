deviceAcctMgmtModule
		.controller(
				'DeviceRegisterCtrl',
				function($rootScope, $scope, $http, $location, $route, deviceService) {

					$scope.myData = [];
					$scope.showNoDeviceRegistered = true;

					$http({
						method : 'GET',
						url : 'getRegisteredDeviceList.do'
					}).success(function(data) {
						
						$scope.registeredDeviceList = data.registeredDeviceList;
						$scope.remainingDeviceRegistration = data.remainingNumberOfDeviceRegistration;
						$scope.deviceLimit = data.remainingDeviceRegistrations;
						$scope.maxDeviceLimit = $scope.registeredDeviceList.length + data.remainingDeviceRegistrations;
						
						if($scope.deviceLimit == 0){
							
							$scope.showGrid = true;
							$scope.maxDeviceLimitReached = true;
							$scope.myData = $scope.registeredDeviceList;
							
						}else if($scope.deviceLimit == $scope.maxDeviceLimit){
							$scope.showGrid = false;
							$scope.showNoDeviceRegistered = false;
							//No device
						}else{
							$scope.showGrid = true;
							$scope.myData = $scope.registeredDeviceList;
						}
						
					}).error(function(data, status, headers, config) {

					});
					$scope.select = function(device){
						angular.forEach($scope.myData, function(d){
							d.selected=false;
							d.selectionClass='';
						});
						device.selected = true;
						device.selectionClass = 'selectedRow';
					};
					
					
					// Replaced ng-grid with simple table due to caching issues. Kept following implementation for reference.
					/*$scope.deleteImage = '<input type="image" src="images/delete.png" name="image" ng-click="edit(row)">';
					$scope.gridOptions = {
						data : 'myData',
						columnDefs : [ {
							field : 'deviceName',
							displayName : 'Name',
							width : "180"
						}, {
							field : 'deviceType',
							displayName : 'Type',
							width : "120"
						}, {
							field : 'deviceRegisteredDate',
							cellFilter: 'date:"MM/dd/yyyy"',
							displayName : 'Registered',
								width : "150"
						}, {
							displayName : '',
							cellTemplate : $scope.deleteImage
						} ]
					};*/

					$scope.edit = function (row) {
						$scope.responseError = false;
						deviceService.setDevice(row);
						
						// Store device information in session in order to retrieve it on deregister page on page refresh or lang change events.
						$http({
						    method: 'POST',
										url : 'storeDeviceInfoInSession.do',
										data : row,
										headers : {
											'Content-Type' : 'application/json',
											'Accept' : 'application/json'
										}
						}).success(function(data, status) {
							if(data!=='success'){
								$route.reload();
							}
						}).error(function(data, status, headers, config) {
								
						});
						
						if (row.deviceTypeDesc == "DEVICE_TYPE_SC") {
							$rootScope.navigateTo("deregister_device");
						}
						else if (row.deviceTypeDesc == "DEVICE_TYPE_CE") {
							$rootScope.navigateTo("delete_device");
						}
						else if (row.deviceTypeDesc == "DEVICE_TYPE_PC") {
							$http({
							    method: 'POST',
											url : 'deleteDevice.do',
											data : row,
											headers : {
												'Content-Type' : 'application/json',
												'Accept' : 'application/json'
											}
							})		
							.success(function(response) {
								$rootScope.navigateTo("dashboard");
							})
							.error(
								function(data, status, headers, config) {
									$scope.responseError = true;
									$scope.errorMessage = data;
							});
						}
					};
				});

deviceAcctMgmtModule
		.controller(
				'RegisterNewDeviceCtrl',
				function($rootScope, $scope, $http, $location) {
					$scope.registerNewDevice = function(){
						$rootScope.navigateTo("register_newdevice");
					};
				});


deviceAcctMgmtModule
.controller(
		'RegisterDeviceFormCtrl',
		function($scope, $rootScope, $http, $location, deviceService) {
			
			$scope.device = {
					regCode:'', newDeviceName:''
			};
			$scope.registerDeviceResponseError = false;
			
			$scope.registerDevice = function(form) {
				
				$scope.regCodeSizeError = false;
				$scope.maxDeviceNameLimitReached = false;
				$scope.blankDeviceName = false;
							
				if(!form.$valid) {
					
					if(!form.registrationCode.$valid){
						
						$scope.regCodeSizeError =  true;
					}
					if(!form.deviceName.$valid){
						$scope.blankDeviceName = true;
					}
					
				}
				else 
				{
					if(($scope.device.newDeviceName).length < 3 || ($scope.device.newDeviceName).length > 96){
						$scope.maxDeviceNameLimitReached = true;
					}
				
					if(($scope.device.regCode).length < 8 || ($scope.device.regCode).length == 9){
						$("#registrationCode").focus();
						$scope.regCodeSizeError =  true;
					}
					if($scope.maxDeviceNameLimitReached == false && $scope.regCodeSizeError == false && $scope.blankDeviceName == false)
					{
						$http({
						    method: 'POST',
										url : 'registerDevice.do',
										data : $scope.device,
										headers : {
											'Content-Type' : 'application/json',
											'Accept' : 'application/json'
										}
						})		
						.success(function(response) {
							deviceService.setActivationURL(response);
							$rootScope.navigateTo("download");
						})
						.error(function(data, status, headers, config) {
							$scope.registerDeviceResponseError = true;
							$scope.errorMessage = data;
						});
					}
				}
			};
			/*$scope.activationUrl = function(){
				return deviceService.getActivationURL();
			};*/
			
			
		});


deviceAcctMgmtModule
.controller(
		'DownloadCtrl',
		function($scope, $rootScope, $http, $location, $window, deviceService) {
			
			// Fetch activation URL information from server on the event of page refresh or language change or click of Download button repeatedly
			if(deviceService.getActivationURL() == ""){
				$http({
				    method: 'POST',
						url : 'fetchActivationUrlFromSession.do',
						headers : {
							'Content-Type' : 'application/json',
						    'Accept' : 'application/json'
								}
				}).success(function(response) { 
					deviceService.setActivationURL(response);
					return;
				}).error(function(data, status, headers, config) {
					return;
				});
			}
			$scope.download = function() {
				$window.location.href=deviceService.getActivationURL();
			};
		});


deviceAcctMgmtModule
.controller(
		'DeRegisterDeviceFormCtrl',
		function($scope, $rootScope, $http, $location, deviceService) {
			
			$scope.device = {
					session:'', deviceId:'', deregCode:''
			};
			$scope.responseError = false;
			
			// Fetch device information from server on the event of page refresh or language change
			if(deviceService.getDevice() == null){
				$http({
				    method: 'POST',
						url : 'fetchDeviceInfoFromSession.do',
						headers : {
							'Content-Type' : 'application/json',
						    'Accept' : 'application/json'
								}
				}).success(function(response) { 
					deviceService.setDevice(response);
					return;
				}).error(function(data, status, headers, config) {
					return;
				});
			}
			
			$scope.deRegisterDevice = function(form) {
				$scope.device.deviceId = deviceService.getDeviceId();
				$scope.deRegCodeSizeError = false;
				
				if(!form.$valid) {
					if(!form.deRegistrationCode.$valid){
						$scope.deRegCodeSizeError =  true;
					}
					return;
				}
				if(($scope.device.deregCode).length < 8 || ($scope.device.deregCode).length == 9)
				{
					$("#deRegistrationCode").focus();
					$scope.deRegCodeSizeError =  true;
					return;
				}
				
					$http({
					    method: 'POST',
									url : 'deregisterDevice.do',
									data : $scope.device,
									headers : {
										'Content-Type' : 'application/json',
										'Accept' : 'application/json'
									}
					})		
					.success(function(response) {
						$rootScope.navigateTo("deregister_success");
					})
					.error(
							function(data, status, headers, config) {
								$scope.responseError = true;
								$scope.errorMessage = data;
							});
		};
		
		$scope.deviceManager=function(){
			$rootScope.navigateTo("dashboard");
		};
		
		$scope.deleteDevice = function(){
			$rootScope.navigateTo("delete_device");
		};
});


deviceAcctMgmtModule
.controller(
		'DeleteDeviceCtrl',
	function($scope, $rootScope, $http, $location, deviceService) {
			
			$scope.device = {
					session:'', deviceId:''
			};
			$scope.responseError = false;
			
			if(deviceService.getDevice() == null){
				$http({
				    method: 'POST',
						url : 'fetchDeviceInfoFromSession.do',
						headers : {
							'Content-Type' : 'application/json',
						    'Accept' : 'application/json'
								}
				}).success(function(response) { 
					deviceService.setDevice(response);
					return;
				}).error(function(data, status, headers, config) {
					return;
				});
			}
			
			$scope.deregisterDevice = function(form) {
				$scope.device.deviceId = deviceService.getDeviceId();
				$http({
					    method: 'POST',
									url : 'deleteDevice.do',
									data : $scope.device,
									headers : {
										'Content-Type' : 'application/json',
										'Accept' : 'application/json'
									}
					})		
					.success(function(response) {
						$rootScope.navigateTo("delete_device_success");
					})
					.error(
						function(data, status, headers, config) {
							$scope.responseError = true;
							$scope.errorMessage = data;
						});
		};
		
		$scope.deviceName = function() {
			return deviceService.getDeviceName();
		};
		
		$scope.deviceManager=function(){
			$rootScope.navigateTo("dashboard");
		};
		
		$scope.deleteDevice = function(){
			$rootScope.navigateTo("delete_device_success");
		};
});


deviceAcctMgmtModule
.controller(
		'DeleteDeviceSuccessCtrl',
		function($scope, $rootScope, $http, $location, $window) {
			
			$scope.device = {};
			$scope.goToDashboard = function() {
				 {
					$rootScope.navigateTo("dashboard");
				}
			};
		});
	
	
deviceAcctMgmtModule.$inject = ['deviceService'];