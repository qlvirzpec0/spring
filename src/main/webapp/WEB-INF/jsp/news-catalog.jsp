<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>
<jsp:include page="menu.jsp"/>

<c:if test="${not empty news}">
    <form action="<c:url value="/manage/news/remove"/>" method="post">
        <table border="1" style="width: 50%; margin: auto;">
            <c:forEach var="news_" items="${news}">
                <tr>
                    <td>
                        <table border="1" style="width: 100%;">
                            <tr>
                                <td>
                                    <a href="<c:url value="/news/${news_.changeDate}/${news_.uri}"/>">
                                        <c:out value="${news_.title}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${news_.changeDate}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <c:out value="${news_.brief}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="<c:url value="/news/${news_.changeDate}/${news_.uri}"/>">
                                        View
                                    </a>
                                </td>
                                <td>
                                    <a href="<c:url value="/manage/news/edit/${news_.id}"/>">
                                        Edit
                                    </a>
                                </td>
                                <td><input type="checkbox" name="id" value="${news_.id}"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <input type="submit" value="Delete"/>
                </td>
            </tr>
        </table>
    </form>
</c:if>
</body>
</html>