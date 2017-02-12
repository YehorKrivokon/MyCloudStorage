<%--suppress ALL --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>MyCloud</title>
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/menu.css">
    <link rel="stylesheet" href="../../resources/css/menu_left.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

</head>
<li>


    <div class="main">
        <div class="header">
            <div id="menu_div">
                <div id="navigation">
                    <div id="menu">
                        <ul id="nav">
                            <label for="add-file">
                                <li>Add file</li>
                            </label>
                            <label for="delete_file">
                                <li>Delete</li>
                            </label>
                            <label for="download_file">
                                <li>Download</li>
                            </label>
                            <li><a href="#" name="signout" onclick="document.forms['logoutForm'].submit()">Sign out</a>
                            </li>
                        </ul>

                        <form class="searchform" action="${contextPath}/search?${_csrf.parameterName}=${_csrf.token}"
                              method="POST">
                            <input class="searchfield" type="text" value="Search..." name="search"
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
                            <li><a href="#" onclick="document.forms['all'].submit()">All files</a></li>
                            <li><a href="#" onclick="document.forms['video'].submit()">Video</a></li>
                            <li><a href="#" onclick="document.forms['audio'].submit()">Audio</a></li>
                            <li><a href="#" onclick="document.forms['images'].submit()">Images</a></li>
                            <li><a href="#" onclick="document.forms['text'].submit()">Text</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="middle">
            <div class="loader">
                <div class="progress">
                    <div class="progress-bar progress-bar-striped progress-bar-success active" role="progressbar"
                         aria-valuenow="0" aria-valuemin="0" aria-valuemax="0" style="width: 0%">
                    </div>
                </div>
            </div>
            <form action="${contextPath}/delete_file?${_csrf.parameterName}=${_csrf.token}" method="POST">
                <c:forEach items="${userDataList}" var="data">
                <div class="content">
                    <input type="checkbox" class="checkbox" name="toSelect[]" value="${data.id}"
                           id="checkbox_${data.id}"/>
                    <div class="content_c">
                        <p class="type">${data.type}</p>
                        <p class="info">${data.name}</p>
                        <hr style="margin-top: -12px;">
                        <p class="info" style="margin-top: -10px;">${data.size} MB</p>
                    </div>
                    <div class="open">
<li>
    <a target="_blank" name="${data.id}"
       href="file:///${path}${data.name}">Open</a>
</li>
</div>
</div>
</c:forEach>
<input id="delete_file" type="submit" value="del" style="display: none; z-index:4; margin-top: -10px"/>
</form>
</div>

<div class="footer">
</div>

<div class="logo">
</div>

<div class="username">
    Welcome,&nbsp; ${pageContext.request.userPrincipal.name}
</div>
</div>

<form id="logoutForm" method="POST" action="${contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form id="addForm" action="${contextPath}/add_file?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data"
      method="POST">
    <input id="add-file" name="file" type="file" style="display: none"
           onChange="document.getElementById('addForm').submit();"/>
</form>

<form id="all" action="${contextPath}/index?${_csrf.parameterName}=${_csrf.token}" method="GET">
    <input type="hidden" name="type" value="all"/>
</form>

<form id="video" action="${contextPath}/index?${_csrf.parameterName}=${_csrf.token}" method="GET">
    <input type="hidden" name="type" value="video"/>
</form>

<form id="audio" action="${contextPath}/index?${_csrf.parameterName}=${_csrf.token}" method="GET">
    <input type="hidden" name="type" value="audio"/>
</form>

<form id="images" action="${contextPath}/index?${_csrf.parameterName}=${_csrf.token}" method="GET">
    <input type="hidden" name="type" value="images"/>
</form>

<form id="text" action="${contextPath}/index?${_csrf.parameterName}=${_csrf.token}" method="GET">
    <input type="hidden" name="type" value="text"/>
</form>
    <button style="display:none" type="button" id="download_file">Dow file</button>

<div style="display: none; z-index: -1;">
    <c:forEach items="${DownloadList}" var="link">
        <a id="download${link}" download
           href="${path}${link}">download</a>
        <script>
            document.getElementById('download${link}').click();
        </script>
    </c:forEach>
</div>


<%--suppress JSUnresolvedFunction, JSUnresolvedVariable --%>
<script>
    $('#download_file').click(function () {
        var data = {'toSelect[]': []};
        $(":checked").each(function () {
            data['toSelect[]'].push($(this).val());
        });
        $.post("${contextPath}/download_file?${_csrf.parameterName}=${_csrf.token}", data, function (data, status) {
            window.location.reload();
        });
    });
</script>
</body>
</html>