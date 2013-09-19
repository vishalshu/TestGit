<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#newPassword').bind('cut copy paste', function(event) {
	        event.preventDefault();
		});
		$('#confirmPassword').bind('cut copy paste', function(event) {
	        event.preventDefault();
		});
	});
</script>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column" class="span8">
					<h2>
						<fmt:message key="label.resetPasswordNotifTitle" />
					</h2>
					<p><fmt:message key="label.enterNewPassword" /></p>

					<!-- Main content in this div -->
					<div class="sub-content-box">

						<div class="ng-scope" ng-controller="ResetPasswordPageCtrl">
							<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="reset_password_page" name="reset_password_page" ui-keypress="{13:'submit(reset_password_page)'}">
								<div ng-show="showError" class="error">{{errorMessage}}</div>
								<br />
						
								<div class="divFormInputRow row-fluid">
									<div ><input class="ng-pristine ng-invalid ng-invalid-required ng-ui" type="password" ng-minlength="6" ng-maxlength="20" placeholder="<fmt:message key="label.newPassword" /> <fmt:message key="label.passwordLengthRange" />"   name="newPassword" required="required" ng-model="user.newPassword" id="newPassword"/></div>
									<span class="error" ng-show="newPasswordError" type="alert.type" close="closeAlert($index)"><fmt:message key="error.signup.passowrd" /></span>
								</div>
								<div class="divFormInputRow row-fluid">
									<div ><input class="ng-pristine ng-invalid ng-invalid-required" type="password" ng-minlength="6" ng-maxlength="20" placeholder="<fmt:message key="label.confirmPassword" />"  name="confirmPassword" required="required" ng-model="user.confirmPassword" id="confirmPassword"/></div>
									<span ng-show="confirmPasswordError" class="error" type="alert.type"><fmt:message key="error.signup.passowrd" /></span>
									<span ng-show="passwordNotMatchError" class="error" type="alert.type"><fmt:message key="error.signup.passowrdonotmatch" /></span>
								</div>
							
								<div style="margin: 10px 0px 0px 0px">
								 	<button class="btn1" id="resetPasswordBtn" ng-click="submitted=true;submit(reset_password_page)" data-toggle="modal" type="button"><fmt:message key="button.resetPassword" /></button>
								</div>
															
							</form>
						</div>
					</div>
				</div>
				<div id="second-column" class="span6">
					<p></p>
				</div>
			</div>
			<!-- /content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
<!--/ main content"> -->
