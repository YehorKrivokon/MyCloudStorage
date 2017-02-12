<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<title>MyCLoud</title>
	<link rel="stylesheet" href="../../resources/css/style.css">
	<link rel="stylesheet" href="../../resources/css/menu.css">
	<link rel="stylesheet" href="../../resources/css/menu_left.css">
</head>
<body>

<form id="logoutForm" method="POST" action="${contextPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<div class="main">
	<div class="header">
		<div id="menu_div">
			<div id="navigation">
				<div id="menu">
					<ul id="nav">
						<li><a href="#" name="addfile">Add file</a></li>
						<li><a href="#" name="delete">Delete</a></li>
						<li><a href="#" name="download">Download</a></li>
						<li><a href="#" name="signout" onclick="document.forms['logoutForm'].submit()">Sign out</a></li>


					</ul>

					<form class="searchform" action="#">
						<input class="searchfield" type="text" value="Search..."
							   onfocus="if (this.value == 'Search...') {this.value = '';}"
							   onblur="if (this.value == '') {this.value = 'Search...';}"/>
					</form>

				</div>
			</div>
		</div>
	</div>
	<div class="left">
		<div id="menu_divL">
			<div id="navigationL">
				<div id="menuL">
					<ul id="navL">
						<li><a href="#" name="allfiles">All files</a></li>
						<li><a href="#" name="video">Video</a></li>
						<li><a href="#" name="audio">Audio</a></li>
						<li><a href="#" name="images">Images</a></li>
						<li><a href="#" name="text">Text</a></li>
						<li><a href="#" name="other">Other</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="middle">
		<div class="loader">
			<div class="progress">
				<div class="progress-bar progress-bar-striped progress-bar-success active" role="progressbar"
					 aria-valuenow="100" aria-valuemin="0" aria-valuemax="0" style="width: 100%">
					100%
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
	</div>

	<div class="logo">
	</div>

</div>


</body>
</html>