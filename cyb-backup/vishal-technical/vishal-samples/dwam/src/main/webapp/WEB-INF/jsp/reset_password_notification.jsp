<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="label.resetPasswordNotifTitle" />
					</h2>
					<p><fmt:message key="label.resetPasswordNotifDesc" /></p>

					<!-- Main content in this div -->
					<div class="sub-content-box">

						<div class="ng-scope" ng-controller="ResetPasswordCtrl">
							<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="reset_password_notification" name="reset_password_notification" ui-keypress="{13:'submit(reset_password_notification)'}">
								<br />
								<div class="divFormInputRow">
									<div style="float: left;"><input class="ng-pristine" type="email" placeholder="<fmt:message key="label.emailAddress" />"  name="email" required="required" ng-model="user.email" id="email"></div>
									
									<span style="margin-left: 5px"><button class="btn1" id="resetPasswordBtn" ng-click="submit(reset_password_notification)" data-toggle="modal" type="button" ><fmt:message key="button.resetPassword" /></button></span>
									<div><span class="error grid-8" ng-show="invalidEmail" type="alert.type" close="closeAlert($index)"><fmt:message key="error.login.email" /></span></div>
									
								</div>
									
								<div ng-show="resetPasswordNotifReponseError" class="error" style="margin: 5px 0px 15px 0px;width: 550px;"><fmt:message key="error.forgotPasswordNotifError" /> <a href="#/user_signup"><fmt:message key="link.createAccount" /></a></div> 								
							</form>
						</div>
						<div style="margin: 20px 0px 0px 0px"><fmt:message key="label.resetPasswordNotifForgotEmail" /> <a href="http://rovicorp.force.com/divx" target="_parent"><fmt:message key="label.divxSupport" /></a>.</div>
					</div>
				</div>
				<div id="second-column" >
					<p></p>
				</div>
			</div>
			<!-- /content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
<!--/ main content"> -->
