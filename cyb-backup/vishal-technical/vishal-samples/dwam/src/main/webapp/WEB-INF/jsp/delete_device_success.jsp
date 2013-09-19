<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column" class="span8">
					<h2>
						<fmt:message key="label.deleteDeviceTitle" />
					</h2>
					<br />

					<!-- Main content in this div -->
					<div class="sub-content-box">

						<div class="ng-scope" ng-controller="DeleteDeviceSuccessCtrl">
							<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="delete_device_success" name="delete_device_success" ui-keypress="{13:'submit(delete_device_success)'}">
										
								<div>
									<fmt:message key="label.deleteDeviceSuccess" />
								</div>
							</br>
										
								<div style="margin: 10px 0px 0px 0px">
								 	<button class="btn1" id="deleteDeviceSuccessBtn" ng-click="goToDashboard()" data-toggle="modal" type="button"><fmt:message key="button.deviceManager" /></button>
								</div>
															
							</form>
						</div>
					</div>
				</div>
				<div id="second-column">
					<%@ include file="navigation_panel.jsp"%>
				</div>
			</div>
			<!-- /content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
<!--/ main content"> -->
