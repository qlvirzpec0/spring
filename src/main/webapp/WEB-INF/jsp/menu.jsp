<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul>
    <li><a href="<c:url value="/"/>">Home</a></li>
    <sec:authorize access="isAuthenticated()">
        <li>${pageContext.request.userPrincipal.name}</li>
        <li><a href="<c:url value="/logout"/>">Logout</a></li>
        <li><a href="<c:url value="/manage/news/add"/>">Add news</a></li>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <li><a href="<c:url value="/auth"/>">Login/Reg</a></li>
    </sec:authorize>
</ul>
