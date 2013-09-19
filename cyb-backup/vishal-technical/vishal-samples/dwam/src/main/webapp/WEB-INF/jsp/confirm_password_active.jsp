<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column" class="grid-8">
					<h2>
						<fmt:message key="label.resetPasswordNotifTitle" />
					</h2>
					<p><fmt:message key="label.resetPasswordConfirm" /></p>

					<!-- Main content in this div -->
					<div class="sub-content-box">

						<div class="ng-scope" ng-controller="ResetPasswordConfirmCtrl">
							<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="resetPasswordConfirm" name="resetPasswordConfirm" ui-keypress="{13:'submit(resetPasswordConfirm)'}">
						<br />
																
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
