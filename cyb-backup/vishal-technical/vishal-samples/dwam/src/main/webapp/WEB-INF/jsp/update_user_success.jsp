<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- main content-->
<div id="body-main">
	<div id="body-inner">
		<div id="main-wrapper">
			<div id="main-content" class="clear-block row-fluid">
				<div id="first-column">
					<h2>
						<fmt:message key="label.accountUpdated" />
					</h2>
					<!-- Main content in this div -->
					<!--/Registration form-->
					<div>
						<fmt:message key="updateAccount.email.notification" />
					</div>
					<!-- End main content div -->
				</div>
				<div id="second-column">
					<%@ include file="navigation_panel.jsp"%>
				</div>

			</div>
			<!-- /#main-content -->
		</div>
		<!-- /#main-wrapper -->
	</div>
</div>
