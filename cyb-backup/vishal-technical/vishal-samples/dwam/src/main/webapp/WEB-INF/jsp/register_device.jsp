<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<!--content -->
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="registerNewDevice" />
					</h2>
					<p><fmt:message key="register.Instruction" /></p>

					<!-- Main content in this div -->
					<div class="sub-content-box">
						<!--Register New Device Form-->
						<div class="ng-scope" ng-controller="RegisterDeviceFormCtrl">
							<form class="registerDeviceForm ng-pristine ng-binding ng-invalid ng-invalid-required" id="registerNewDeviceForm" name="registerNewDeviceForm" ui-keypress="{13:'registerDevice(registerNewDeviceForm)'}">
							    <div ng-show="registerDeviceResponseError" class="error">{{errorMessage}}</div>
								<h4>
									<fmt:message key="registrationCode" />
								</h4>
								<div class="text-background-box">
									<div><input class="divInputRow ng-pristine ng-invalid ng-required ng-invalid-required" type="text" maxlength="10" placeholder="<fmt:message key="label.registrationCode" />" name="registrationCode" ng-required="true" ng-model="device.regCode" id="registrationCode"></div>
								</div>
								<span ng-show="regCodeSizeError" class="error" type="alert.type"><fmt:message key="error.registerDevice.registrationCode" /></span>
								<!--  <span ng-show="submitted " class="error" type="alert.type"><fmt:message key="error.registerDevice.registrationCode" /></span> -->
								<p style="width:500px">
									<fmt:message key="registrationCode.instruction" />
									<a href="http://rovicorp.force.com/apex/Show_DivxB2C_Article?id=kA540000000CabT&url=How-to-register-your-DivX-Certified-device-with-the-DivX-Plus-Player-and-watch-your-purchased-DivX-VOD-content&language=en_US" target="_parent"><fmt:message key="link.learnMore" /></a>
								</p>
														
								<h4>
									<fmt:message key="deviceName" /> 
								</h4>
								<div class="text-background-box">
									<div class="divLabelRow">
										<label class="deviceLabel" for="deviceName"><fmt:message key="label.deviceName" /></label>
										<div><input class="divFormInput ng-pristine ng-invalid ng-required ng-invalid-required" type="text" name="deviceName" ng-required="true" ng-model="device.newDeviceName" id="deviceName"></div>
									</div>
								</div>
								<span class="error" ng-show="maxDeviceNameLimitReached"  type="alert.type"><fmt:message key="error.registerDevice.deviceNameLength" /></span>
								<span class="error"  ng-show="blankDeviceName" type="alert.type"><fmt:message key="error.registerDevice.deviceName" /></span>
								<p><fmt:message key="deviceName.instruction" /></p>
								<div class="bs-docs-example" style="padding-bottom: 10px; color:white;">
									<button class="btn1" id="RegDeviceBtn" ng-click="submitted=true;registerDevice(registerNewDeviceForm)" data-toggle="modal" type="button"><fmt:message key="button.next" /></button>
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

