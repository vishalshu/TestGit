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
					<p><fmt:message key="download.instruction" /></p>	
					<!-- Main content in this div -->
					<div class="sub-content-box">
						<!--Download Form-->
						<div class="ng-scope" ng-controller="DownloadCtrl">
							<form class="downloadForm ng-pristine ng-binding ng-invalid ng-invalid-required" method="link" id="downloadForm" name="downloadForm" ui-keypress="{13:'download()'}">
								<div class="text-background-box">
									<div class="divLabelRow row-fluid">
										<label class="span5" for="activationVideo"><fmt:message key="label.activationVideo" /></label>
										<div class="bs-docs-example" style="padding-bottom: 10px; color:white;">
											<button class="btn1 divFormButton" id="DownloadBtn" ng-click="submitted=true;showDetails=!showDetails;download()" data-toggle="modal" type="button" ><fmt:message key="button.download" /></button>
										</div>
									</div>
								</div>
								<div ng-show="showDetails" style="margin-top:40px">
									<%@ include file="post_download.jsp"%>
								</div>
								<p style="width:500px">
									<a href="http://rovicorp.force.com/apex/Show_DivxB2C_Article?id=kA540000000CaWB&language=en_US&url=What-is-DivX-Certified-Device-Registration" target="_parent"><fmt:message key="play.instruction" /></a>
								</p>
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