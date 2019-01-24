<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="menu.jsp"/>

<form:form method="post" modelAttribute="author">
    <form:errors path="username"/><br/>
    <form:label path="username"/>
    <form:input path="username"/><br/>

    <form:errors path="password"/><br/>
    <form:label path="password"/>
    <form:input path="password"/><br/>

    <input type="submit" formaction="/auth" value="log">
    <input type="submit" formaction="/reg" value="reg">
</form:form>
</body>
</html>