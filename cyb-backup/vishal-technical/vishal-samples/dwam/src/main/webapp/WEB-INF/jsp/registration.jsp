<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- /#second-container -->

<script type="text/javascript">
	$(document).ready(function() {
		$('#password').bind('cut copy paste', function(event) {
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
						<fmt:message key="label.createAccountTitle" />
					</h2>
					
					<!-- Main content in this div -->
					<div class="sub-content-box">

								<!--Registration form-->
							<div class="ng-scope" ng-controller="UserFormCtrl" >
								<div ng-show="!signupFlag">	
								<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="customerForm" name="customerForm" ui-keypress="{13:'register($event)'}">
								
										<div ng-show="showError" class="error" >{{errorMessage}}</div>
										<br />
								
										<div style="margin-bottom: 10px; width: 550px;"><fmt:message key="label.createAccountTitleDesc" /></div>
								
										<div ng-show="signupResponseError" class="error" style="margin-bottom: 10px"><fmt:message key="error.signup.response" /></div>
										
										<div class="divFormInputRow row-fluid">																	
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="text" ng-maxlength="25" placeholder="<fmt:message key="label.firstName" />" ng-mouseleave="isEmpty()"  name="firstName" ng-model="user.firstName" id="firstName"/></div>
											<span class="error grid-8" ng-show="firstNameError"><fmt:message key="error.signup.firstname" /></span>
										</div>
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="text" ng-maxlength="25" placeholder="<fmt:message key="label.lastName" />" ng-mouseleave="isEmpty()"  ng-model="user.lastName" name="lastName" id="lastName"/></div>
											<span ng-show="lastNameError" class="error grid-8" type="alert.type"><fmt:message key="error.signup.lastname" /></span>
										</div>
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="email" ng-maxlength="65"  placeholder="<fmt:message key="label.email" />*" ng-mouseleave="isEmpty()" name="email" required="required" ng-model="user.email" id="email"/></div>
											<span ng-show="emailNameError"  type="alert.type" class="error grid-8 bottom-gap"><fmt:message key="error.signup.email" /></span>
										</div>	
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="password" ng-minlength="6" placeholder="<fmt:message key="label.password" />*<fmt:message key="label.passwordLengthRange" />" ng-maxlength="20" name="password" ng-model="user.password" required="required" id="password"/></div>
											<span ng-show="passwordError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.passowrd" /></span>									
										</div>	
										
										<div class="divFormInputRow row-fluid">
											<div class="span4"><input class="ng-pristine ng-invalid ng-invalid-required" type="password" placeholder="<fmt:message key="label.passwordConf" />*" ng-minlength="6" ng-maxlength="20"  ng-model="repassword" name="repassword" required="required" id="repassword"/></div>
											<span ng-show="rePasswordError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.passowrd" /></span>
											<span ng-show="passwordNotMatchError" class="error grid-8 bottom-gap" type="alert.type"><fmt:message key="error.signup.passowrdonotmatch" /></span>
										</div>
										<br />
										<div>
											
											<div>
												<input class="ng-pristine ng-invalid ng-invalid-required" style="margin: -2px 7px 0px 0px" ng-required="divxTermsConf" type="checkbox" ng-model="divxTermsConf" name="divxTermsConf" id="divxTermsConf" />
												<span ng-show="divxTermsConfError" style="color:red;"><fmt:message key="label.divxTermsConf" /> </span>
												<span ng-show="!divxTermsConfError"><fmt:message key="label.divxTermsConf" /> </span>
											</div>
											
											<a href="http://www.divx.com/en/terms-of-use" target="_parent"><fmt:message key="label.termsAndConditions" /></a> 
											<span ng-show="divxTermsConfError" style="color:red;"><fmt:message key="label.divxTermsConf2"/></span>
											<span ng-show="!divxTermsConfError"><fmt:message key="label.divxTermsConf2"/></span>
											<a href="http://www.divx.com/en/privacy-policy" target="_parent"><fmt:message key="label.privacyPolicy" /></a>.
											&nbsp;&nbsp;
											
										</div>
										<br />
										<div class="row-fluid bs-docs-example">
											<button class="btn1" id="customerRegBtnn" ng-click="submitted=true;register(customerForm)" data-toggle="modal" type="button"><fmt:message key="button.createAccount" /></button>
										</div>

									</form>
								</div>	
								<div ng-show="signupFlag">
								    <fmt:message key="signup.email.notification1" />
	 								<fmt:message key="signup.email.notification2" />{{user.email}}.
	 								<fmt:message key="signup.email.notification3" />
	 								
	 								<div style="margin-top: 15px;">
	 								<button class="btn1" id="loginBtn" ng-click="showLogin()" data-toggle="modal" type="button" ><fmt:message key="button.login" /></button>
	 								</div>
								</div>
					</div>
					<!-- End main content div -->
				</div>
			</div>
				
			</div>
			<!-- /#main-content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
