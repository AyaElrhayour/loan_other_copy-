<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Requests</title>
</head>
<body>
<header class="header">
    <nav class="nav">
        <img src="./img/icon.png" alt="Bank logo" class="nav__logo" id="logo" data-version-number="3.0" />
        <ul class="nav__links">
            <li class="nav__item"><a class="nav__link" href="${pageContext.servletContext.contextPath}">Home</a></li>
            <li class="nav__item"><a class="nav__link" href="${pageContext.servletContext.contextPath}/clients">Clients</a></li>
            <li class="nav__item"><a class="nav__link" href="${pageContext.servletContext.contextPath}/employees">Employees</a></li>
            <li class="nav__item"><a class="nav__link nav__link--btn btn--show-modal" href="${pageContext.servletContext.contextPath}/simulations">Simulate a credit</a></li>
        </ul>
    </nav>
</header>
<div class="content">
    <button class="add-button">Add Request</button>
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
                        ${reqStatus.status.status}<br/> <!-- Display the status name -->
                    </c:forEach>
                </td>
                <td>
                    <button class="update-button" data-requestid="${request.id}">Update</button>
                    <button class="delete-button" data-requestid="${request.id}">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>




</body>
</html>
