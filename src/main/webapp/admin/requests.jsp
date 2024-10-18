<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Requests</title>
</head>
<body>

<div class="content">

    <div class="search-container">
        <form action="${pageContext.request.contextPath}/requests?action=search" method="POST">
            <input type="search" id="requestSearch" name="code" placeholder="Search by code...">
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>Project</th>
            <th>Occupation</th>
            <th>Amount</th>
            <th>Period</th>
            <th>Monthly Payment</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Title</th>
            <th>Name</th>
            <th>Last Name</th>
            <th>ID Card</th>
            <th>Birthdate</th>
            <th>Hiring Date</th>
            <th>Monthly Income</th>
            <th>Old Loan</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="requests" scope="request" type="java.util.List"/>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td>${request.project}</td>
                <td>${request.occupation}</td>
                <td>${request.amount}</td>
                <td>${request.period}</td>
                <td>${request.monthlyPayment}</td>
                <td>${request.email}</td>
                <td>${request.phone}</td>
                <td>${request.title}</td>
                <td>${request.name}</td>
                <td>${request.lastName}</td>
                <td>${request.idCard}</td>
                <td>${request.birthdate}</td>
                <td>${request.hiringDate}</td>
                <td>${request.monthlyIncome}</td>
                <td>${request.oldLoan}</td>

                <td>
                    <c:forEach var="reqStatus" items="${request.requestStatus}">
                        ${reqStatus.status.status}<br/>
                    </c:forEach>
                </td>
                <form action="${pageContext.request.contextPath}/requests" method="get">
                    <input type="hidden" name="requestId" value="${request.id}">
                    <button type="submit">View Details</button>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>




</body>
</html>
