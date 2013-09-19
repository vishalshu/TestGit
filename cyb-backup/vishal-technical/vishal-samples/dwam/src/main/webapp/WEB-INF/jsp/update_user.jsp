  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Pragma" content="no-cache"/>
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#password').bind('cut copy paste', function(event) {
	        event.preventDefault();
		});
		$('#newPassword').bind('cut copy paste', function(event) {
	        event.preventDefault();
		});
		$('#repassword').bind('cut copy paste', function(event) {
	        event.preventDefault();
		});
	});
</script>

<!-- main content-->
<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="label.updateAccountTitle" />
					</h2>
					
					<!-- Main content in this div -->
						<div class="sub-content-box"  ng-controller="UserFormCtrl" >

							<!--Registration form-->
							<div class="ng-scope">
								<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="customerForm" name="customerForm" ui-keypress="{13:'updateUser(customerForm)'}">
											
										<div ng-show="showError" class="error">{{errorMessage}}</div>
										<br />
										
										<div class="divFormInputRow row-fluid">																								
											<div class="span4"><input class="ng-pristine ng-invalid " type="text" ng-maxlength="25" placeholder="<fmt:message key="label.firstName" />"  ng-mouseleave="isEmpty()" name="firstName" ng-model="user.firstName" id="firstName"/></div>
											<span class="error grid-8 bottom-gap" ng-show="firstNameError"><fmt:message key="error.signup.firstname" /></span>
										</div>
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" ng-maxlength="25"  type="text" placeholder="<fmt:message key="label.lastName" />" ng-mouseleave="isEmpty()" ng-model="user.lastName" name="lastName" id="lastName"/></div>
											<span ng-show="lastNameError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.lastname" /></span>
										</div>
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" ng-maxlength="65" type="email" placeholder="<fmt:message key="label.email" />*" ng-mouseleave="isEmpty()" name="email" required="required" ng-model="user.email" id="email"/></div>
											<span ng-show="emailNameError" class="error grid-8 bottom-gap"><fmt:message key="error.signup.email" /></span>
										</div>	
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="password" ng-minlength="6" ng-maxlength="20"  placeholder="<fmt:message key="label.currentPassword" />*" ng-maxlength="20"  name="password" ng-model="user.password" id="password"/></div>
											<span ng-show="currentPasswordError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.passowrd" /></span>
											<span ng-show="currentPasswordOnEmailChangeError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.update.emailChange.currentPassword" /></span>
											<span ng-show="currentPasswordRequired" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="currentPasswordRequired" /></span>
										</div>	
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="password" ng-minlength="6" ng-maxlength="20"  placeholder="<fmt:message key="label.newPassword" />* <fmt:message key="label.passwordLengthRange" />" ng-maxlength="20"  name="newPassword" ng-model="user.newPassword" id="newPassword"/></div>
											<span ng-show="newPasswordError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.passowrd" /></span>
										</div>	
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="password" placeholder="<fmt:message key="label.passwordConf" />*" ng-minlength="6" ng-maxlength="20"  ng-model="user.repassword" name="repassword" id="repassword"/></div>
											<span ng-show="rePasswordError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.passowrd" /></span>
											<span ng-show="passwordNotMatchError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.passowrdonotmatch" /></span>
										</div>
										
										<div class="row-fluid bs-docs-example">
											<button class="btn1" id="customerRegBtnn" ng-click="submitted=true;updateUser(customerForm)" data-toggle="modal" type="button"><fmt:message key="button.updateAccount" /></button>
										</div>
								</form>
								<br>
								
							</div>
							
						</div>
				
				<!-- End main content div -->
				</div>
			<div id="second-column">
					<%@ include file="navigation_panel.jsp"%>
				</div>
			
		</div>
		<!-- /#main-content -->
	</div>
	<!-- /#main-wrapper -->
</div>
</div>							
