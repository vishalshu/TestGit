<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- main content-->
<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="label.deviceManager" />
					</h2>
					<div>
						<div style="width: 500px">
							<fmt:message key="manageDevice.instruction" />
						</div>
					</div>

					<!-- Main content in this div -->
					<div class="sub-content-box">

						<div ng-controller="DeviceRegisterCtrl">
							<div style="margin-top: 10px; font: bold;"
								ng-hide="showNoDeviceRegistered">
								<fmt:message key="emptyRegisteredDevice" />
							</div>

							<div class="bs-docs-example"
								ng-controller="RegisterNewDeviceCtrl"
								style="padding-bottom: 10px; color: white;">
								<button ng-disabled="maxDeviceLimitReached" class="btn1"
									style="margin-top: 15px; margin-bottom: 10 px;"
									id="RegNewDeviceBtn" ng-click="registerNewDevice()"
									data-toggle="modal" type="button">
									<fmt:message key="button.registerNewDevice" />
								</button>
							</div>
							<div ng-show="responseError" class="error">
								<p>{{errorMessage}}</p>
							</div>
							<div ng-show="showGrid">
								<div ng-show="maxDeviceLimitReached"
									style="color: red;; margin-bottom: 10 px;">
									<fmt:message key="deviceLimitReached" />
								</div>
								<div ng-hide="maxDeviceLimitReached">
									<fmt:message key="availableDevice1" />
									{{remainingDeviceRegistration}}
									<fmt:message key="availableDevice2" />
								</div>
								<div>
								<table style="border: 1px solid #D4D4D4;">
									<tr>
										<th><fmt:message key="device.grid.name" /></th>
										<th><fmt:message key="device.grid.type" /></th>
										<th><fmt:message key="device.grid.registered" /></th>
										<th></th>
									</tr>
									<tr ng-repeat="device in myData" ng-class="device.selectionClass" ng-click="select(device)" >
										<td class="cellText" style="width:180px;">{{device.deviceName}}</td>
										<td class="cellText" style="width:140px;">{{device.deviceType}}</td>
										<td class="cellText" style="width:150px;">{{device.deviceRegisteredDate | date:'MM/dd/yyyy'}}</td>
										<td class="cellText" style="width:70px;">
											<input type="image" src="images/delete.png" name="image" ng-click="edit(device)">
										</td>
									</tr>
								</table>
							</div>
							<!--  <div class="gridStyle" ng-grid="gridOptions"></div> -->
							</div>
							<div style="margin-top: 10px;">
								<a href="http://rovicorp.force.com" style="color: #0CC3F4;"
									target="_parent"><fmt:message key="link.needHelp" /></a>
							</div>
						</div>
						<br />

					</div>
					<!-- End main content div -->

				</div>
				<div id="second-column">
					<%@ include file="navigation_panel.jsp"%>
				</div>
				<div class="clearBoth"></div>

			</div>
			<!-- /#main-content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>