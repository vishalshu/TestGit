deviceAcctMgmtModule.factory('deviceService', function ($http) {
	
	var deviceService = {};
	var activationURL = '';

	deviceService.setActivationURL = function(url){
		activationURL = url;
	};
	
	deviceService.getActivationURL = function(){
		activationURL = activationURL.replace(/\"/g, "");
		return activationURL ;
	};
	
	deviceService.getDevice = function() {
		return deviceService.device;
	};
	
	deviceService.setDevice = function(device) {
		deviceService.device = device;
	};
	
	deviceService.getDeviceName = function() {
		return deviceService.device.deviceName;
	};
	
	deviceService.getDeviceId = function() {
		return deviceService.device.deviceId;
	};

	return deviceService;
});
