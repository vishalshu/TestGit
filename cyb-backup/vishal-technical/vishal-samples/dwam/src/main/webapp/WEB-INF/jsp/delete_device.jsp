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

						<div class="ng-scope" ng-controller="DeleteDeviceCtrl">
							<form class="userForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="delete_device" name="delete_device" ui-keypress="{13:'deregisterDevice(delete_device)'}">
								<div ng-show="responseError" class="error">{{errorMessage}}</div>

								<div>
									<p>
										<fmt:message key="label.deleteDevice1" /> "{{deviceName()}}"<fmt:message key="label.questionMark" />
									</p>
									<p>
										<fmt:message key="label.deleteDevice2" />
									</p>
								</div>

								<div class="divFormInputRow row-fluid">
									<a href="http://rovicorp.force.com/apex/Show_DivxB2C_Article?id=kA540000000CabT&url=How-to-register-your-DivX-Certified-device-with-the-DivX-Plus-Player-and-watch-your-purchased-DivX-VOD-content&language=en_US" target="_parent"><fmt:message key="link.deleteDeviceHelp" /></a>
								</p>
								</div>
							
								<div style="margin: 10px 0px 0px 0px">
								 	<button class="btn1" id="deleteDeviceBtn" ng-click="deregisterDevice(delete_device)" data-toggle="modal" type="button"><fmt:message key="button.delete" /></button>
								</div>
															
							</form>
						</div>
					</div>
				</div>
				
				<div id="second-column">
					<%@ include file="navigation_panel.jsp"%>
				</div>
			<!-- /content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
<!--/ main content"> -->
