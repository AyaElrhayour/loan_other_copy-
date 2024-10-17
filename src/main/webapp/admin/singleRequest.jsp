<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

<h2>Request Details</h2>
<p><strong>Request ID:</strong> ${singleRequest.id}</p>
<p><strong>Request Name:</strong> ${singleRequest.project}</p>

<h3>Request Statuses</h3>
<table>
  <thead>
  <tr>
    <th>Status</th>
    <th>Status Date</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="req" items="${singleRequest.requestStatus}">
    <tr>
      <td>${req.status}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>