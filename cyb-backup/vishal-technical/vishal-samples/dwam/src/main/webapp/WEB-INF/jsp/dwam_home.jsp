<%@ include file="header_info.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="mainDiv" class="container-16">
	<div style="float: right; margin-right: 90px; margin-top: 10px;">

		<select name="languagedropdown" id="lang">
			<option value="en"
				${param.lang == "en" || param.lang == ""? 'selected' : ''}>English</option>
			<option value="es" ${param.lang == "es" ? 'selected' : ''}>Español</option>
			<option value="fr" ${param.lang == "fr" ? 'selected' : ''}>Français</option>
			<option value="pt" ${param.lang == "pt" ? 'selected' : ''}>Português</option>
			<option value="de" ${param.lang == "de" ? 'selected' : ''}>Deutsch</option>
			<option value="zh_CN" ${param.lang == "zh_CN" ? 'selected' : ''}>中文（简体）</option>
			<option value="zh_TW" ${param.lang == "zh_TW" ? 'selected' : ''}>中文（繁體）</option>
			<option value="ja" ${param.lang == "ja" ? 'selected' : ''}>日本人</option>
			<option value="ko" ${param.lang == "ko" ? 'selected' : ''}>한국의</option>
			<option value="ru" ${param.lang == "ru" ? 'selected' : ''}>русский</option>
			<option value="it" ${param.lang == "it" ? 'selected' : ''}>Italiano</option>
		</select>
	</div>

	<script type="text/javascript">
 var urlmenu = document.getElementById( 'lang' );
 var redirect = function(lang){
  window.location =window.location.pathname+'?lang='+lang+window.location.hash;
 };
 function getCookie(name) {
    var parts = document.cookie.split(name + "=");
    if (parts.length == 2)
     {
     var value = parts.pop().split(";").shift();
     return value;
     
     }
    return;
  }
 function setDefaultLang(lang){
  if(window.location.href.indexOf("lang=")==-1){
   if((lang== 'undefined' || lang==null || lang=='')){
    lang='en';
   }
   
   redirect(lang);
  }else if(lang!=urlmenu.options[ urlmenu.selectedIndex ].value){
   redirect(lang);
  }
  
 }
 var lang = getCookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE");
 setDefaultLang(lang);
 
 
  urlmenu.onchange = function() {
   lang = urlmenu.options[ urlmenu.selectedIndex ].value;
  redirect(lang);
  };
</script>

	<div style="height: 100%;" class="ng-scope" ng-controller="MainCtrl">
		<div style="height: 100%;" ng-view></div>
	</div>
</div>
<!-- /#content-container </div>-->
<!-- /#third-container -->
<!-- /#page -->
<script type="text/javascript">        
setTimeout(function () {
 $.html5support();
        }, 2000);
  
    </script>
</body>
</html>