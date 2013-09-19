<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- main content-->
<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="label.accountVerified" />
					</h2>
					<br />
					<!-- Main content in this div -->
					<!--/Registration form-->
					<div>
						<fmt:message key="label.accountVerifiedMsg" />
					</div>
					
					<div class="sub-content-box">

						<div class="ng-scope" ng-controller="accountVerifiedCtrl">
							<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="accountVerified" name="accountVerified" ui-keypress="{13:'submit(accountVerified)'}">
							<div ng-show="responseError" class="error">{{errorMessage}}</div>
						<br />
																
					<div class="divFormInputRow row-fluid">
							<button class="btn1" id="loginBtn" ng-click="login()" data-toggle="modal" type="button" ><fmt:message key="button.login" /></button>
					</div>
					
					</form>
					<!-- End main content div -->
				</div>
			</div>
			</div>
			<!-- /#main-content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>

