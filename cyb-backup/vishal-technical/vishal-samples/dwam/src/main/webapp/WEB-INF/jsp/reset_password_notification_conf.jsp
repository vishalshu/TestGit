<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid" ng-controller="ResetPasswordCtrl">
				<div id="first-column" class="grid-12">
					<h2>
						<fmt:message key="label.resetPasswordNotifTitle" />
					</h2>
					<p>
						<div><fmt:message key="label.resetPasswordNotifConfirm1" /> {{email()}}.</div>
						<div><fmt:message key="label.resetPasswordNotifConfirm2" /></div>
					</p>

					<!-- Main content in this div -->
					<div class="sub-content-box">

						<div class="ng-scope">
							<form class="ng-pristine ng-binding ng-invalid ng-invalid-required" id="resetPassword" name="reset_password_notification">
								<div ng-show="loginReponseError" class="error" ><fmt:message key="error.invalidLoginCredentials" /></div>
									
								<div class="divFormInputRow row-fluid">
									<button class="btn1" id="resetPasswordBtn" ng-click="login()" data-toggle="modal" type="button" ><fmt:message key="button.login" /></button>
								</div>
																
							</form>
						</div>
					</div>
				</div>
				<div  id="second-column" class="span6">
					<p></p>
				</div>
			</div>
			<!-- /content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
<!--/ main content"> -->
