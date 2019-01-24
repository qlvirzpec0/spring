<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><c:out value="${news.title}"/></title>
</head>
<body>
<jsp:include page="menu.jsp"/>

<form:form modelAttribute="news" method="post">
    <table border="1" style="width: 50%; margin: auto;">
        <tr>
            <td>Title</td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>Date</td>
            <td>
                <form:input path="changeDate"/>
            </td>
        </tr>
        <tr>
            <td>Brief</td>
            <td>
                <form:input path="brief"/>
            </td>
        </tr>
        <tr>
            <td>Content</td>
            <td>
                <form:input path="content"/>
            </td>
        </tr>
        <tr>
            <td>Uri</td>
            <td>
                <form:input path="uri"/>
            </td>
        </tr>
        <tr>
            <td>Author</td>
            <td>
                <form:input disabled="true" path="author.username"/>
            </td>
        </tr>
        <tr>
            <td>
                <c:url var="saveUrl" value="/manage/news/add"/>
                <c:if test="${not empty news.id}">
                    <form:hidden path="id"/>
                    <form:hidden path="author.id"/>
                    <c:set var="saveUrl" value="/manage/news/edit"/>
                </c:if>
                <input type="submit" formaction="${saveUrl}" value="Save"/>
            </td>
            <td>
                <input type="submit" formaction="<c:url value="/"/>" value="Cancel"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
