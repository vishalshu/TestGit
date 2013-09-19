<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="label.deRegisterDeviceTitle" />
					</h2>
					<p style="width:450px"><fmt:message key="label.deRegisterInstruction" /></p>

					<!-- Main content in this div -->
					<div class="sub-content-box">
						<!--Deregister Device Form-->
						<div class="ng-scope" ng-controller="DeRegisterDeviceFormCtrl">
							<form class="registerDeviceForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="deRegisterDeviceForm" name="deRegisterDeviceForm" ui-keypress="{13:'deRegisterDevice(deRegisterDeviceForm)'}">
							    <div ng-show="responseError" class="error">{{errorMessage}}</div>
								<div class="text-background-box">
									<div><input class="divInputRow ng-pristine ng-invalid ng-invalid-required" type="text" maxlength="10" placeholder="<fmt:message key="label.deRegistrationCode" />" name="deRegistrationCode" ng-required="true" ng-model="device.deregCode" id="deRegistrationCode"></div>
								</div>
								<span ng-show="deRegCodeSizeError" class="error" type="alert.type"><fmt:message key="error.deRegisterDevice.deRegistrationCodeLength" /></span>
								<p style="width:450px">
									<a id="deleteDevice" href ng-click="deleteDevice()" ><fmt:message key="label.deRegistrationCodeInstruction" /></a>
								</p>
														
								<div class="bs-docs-example" style="padding-bottom: 10px; color:white;">
									<button class="btn1" id="RegDeviceBtn" ng-click="deRegisterDevice(deRegisterDeviceForm)" data-toggle="modal" type="button" ><fmt:message key="button.deRegisterDevice" /></button>
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

