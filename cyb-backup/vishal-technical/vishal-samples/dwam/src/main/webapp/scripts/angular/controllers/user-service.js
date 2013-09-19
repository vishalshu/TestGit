userAcctMgmtModule.factory('userService', function ($http) {
	var userService = {user:{},loaded : false};
	
	userService.loadUser = function(){
		return $http({
			method : 'POST',
			url : 'loadUser.do'
		});
	};
	
	userService.setEmail = function(emailAdd){
		userService.user.email = emailAdd;
	};
	
	userService.getEmail = function(){
		return userService.user.email ;
	};
	
	userService.isDirty = function(user){
		var dirty = false;
		
		if(user.firstName!=userService.user.firstName || user.lastName != userService.user.lastName ){
			dirty = true;
		}
		if(userService.isEmailDirty(user.email)){
			dirty = true; 
		}
		if(user.newPassword!=undefined && user.newPassword!=''){
			dirty = true;
		}
		
		return dirty;
	};
	
	userService.isEmailDirty = function(newEmail){
		if(userService.user.email.toUpperCase()!=newEmail.toUpperCase()){
			return true;
		}
		return false;
	};
	
	userService.setUser = function(user){
		 angular.copy(user, userService.user);
	};
	
	userService.getUser = function(){
		return userService.user;
	};
	
	userService.markLoaded = function(){
		userService.loaded = true;
	};
	
	userService.markUnloaded = function(){
		userService.loaded = false;
	};
	
	userService.isLoaded = function(){
		return userService.loaded;
	};
	
	return userService;
});
