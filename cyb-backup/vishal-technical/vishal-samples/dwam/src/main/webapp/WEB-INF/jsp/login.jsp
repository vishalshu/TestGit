<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="userLogin" />
					</h2>
					<p style="width:500px">
						<fmt:message key="singup.required" />
						<a href="http://rovicorp.force.com/apex/Show_DivxB2C_Article?id=kA540000000CabT&url=How-to-register-your-DivX-Certified-device-with-the-DivX-Plus-Player-and-watch-your-purchased-DivX-VOD-content&language=en_US" target="_parent"><fmt:message key="link.learnMore" /></a>
					</p>

					<!-- Main content in this div -->
					<div class="sub-content-box">

						<div class="ng-scope" ng-controller="LoginCtrl">
							<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="login" name="customer_login" ui-keypress="{13:'submitForm(customer_login)'}">
								<div ng-show="showError" class="error" >{{loginReponseError}}</div>
								<br />
									
								<div class="divFormInputRow row-fluid">
									<div class="span4"><input class="ng-pristine ng-invalid" type="email" placeholder="<fmt:message key="label.email" />" ng-mouseleave="isEmpty()" name="email" required="required" ng-model="loginData.j_username" id="email"></div>
									<span class="error grid-8 bottom-gap" ng-show="emailError" type="alert.type" close="closeAlert($index)"><fmt:message key="error.login.email" /></span>
								</div>
								<div class="divFormInputRow row-fluid">
									<div class="span4">
										<input class="ng-pristine ng-invalid" type="password" placeholder="<fmt:message key="label.password" />" name="password" required="required" ng-model="loginData.j_password" id="password" ui-keypress="{13:'submitted=true;submit(customer_login)'}">
									</div>
									<span class="error grid-8 bottom-gap" ng-show="passwordError" type="alert.type" close="closeAlert($index)"><fmt:message key="error.login.password" /></span>
								</div>
								<br />
								<div class="bs-docs-example" style="padding-bottom: 10px; color:white;">
									<button class="btn1" id="customerRegBtn" ng-click="submitForm(customer_login)" data-toggle="modal" type="button" ><fmt:message key="button.login" /></button>
									<span style="margin-left: 5px"><button class="btn2" id="createAccountBtn" ng-click="createCustomer()" data-toggle="modal" type="button" ><fmt:message key="button.createAccount" /></button></span>
								</div>
																
							</form>
						</div>
						<div>
							<a href="#/reset_password_notification"><fmt:message key="label.forgotCredentials" /></a>
						</div>  
					</div>
				</div>
				<div  id="second-column">
					<p></p>
				</div>
				<div class="clearBoth"></div>
			</div>
			<!-- /content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
<!--/ main content"> -->
