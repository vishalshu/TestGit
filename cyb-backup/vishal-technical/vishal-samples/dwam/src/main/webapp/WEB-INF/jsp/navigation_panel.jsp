<div class="body-right-signup">
	<div id="navigation_panel" ng-controller="UserDashboardCtrl">
	<div><span style="font-weight: bold; font-size: 1.2em"><fmt:message key="label.customerGreeting" />,</span> ({{customer.email}})</div>
		<ul class="nav">
			<li>
				<a  href="#/update_account"  ng-show="loggedIn"><fmt:message key="link.manageAccount" /></a>
			</li>

			<li>
				<a  href="#/dashboard" ng-show="loggedIn"><fmt:message key="link.manageDevice" /></a>
			</li>
			
			<li>
				<a  href="j_spring_security_logout" ng-show="loggedIn"><fmt:message key="link.logout" /></a>
			</li>
		</ul> 
	</div>
 </div>