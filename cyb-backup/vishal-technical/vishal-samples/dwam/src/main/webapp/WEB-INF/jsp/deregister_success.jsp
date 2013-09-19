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
					<p style="width:450px"><fmt:message key="label.deRegisterSuccessMessage" /></p>

					<!-- Main content in this div -->
					<div class="sub-content-box">
						<!--Deregister Device Success-->
						<div class="ng-scope" ng-controller="DeRegisterDeviceFormCtrl">
							<form class="ng-pristine ng-binding ng-invalid ng-invalid-required" id="deRegisterDeviceSuccessForm" name="deRegisterDeviceSuccessForm">
							    <!-- <div ng-show="registerDeviceResponseError" class="error">{{errorMessage}}</div>-->
								<div class="bs-docs-example" style="padding-bottom: 10px; color:white;">
									<button class="btn1" id="RegDeviceBtn" ng-click="deviceManager()" data-toggle="modal" type="button" ><fmt:message key="button.deviceManager" /></button>
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

