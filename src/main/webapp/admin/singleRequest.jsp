<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Request Details</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-kgj6j3Fl3niJgkfgzdf6xzrmGFeF9vPtvRT1r+J24Ff6Daq24PH2VZ3zJkMhSl+M" crossorigin="anonymous">
</head>
<body class="container mt-5">

<div class="card">
  <div class="card-body">
    <h2 class="card-title">Request Details</h2>
    <p><strong>Request ID:</strong> ${singleRequest.id}</p>
    <p><strong>Project Name:</strong> ${singleRequest.project}</p>
  </div>
</div>

<h3 class="mt-4">Request Statuses</h3>
<table class="table table-striped mt-3">
  <thead class="table-dark">
  <tr>
    <th>Status</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="req" items="${singleRequest.requestStatus}">
    <tr>
      <td>${req.status.status}</td>
      <td>
        <form action="${pageContext.request.contextPath}/status?action=update" method="post" class="d-inline">
          <input type="hidden" name="statusId" value="${req.status.id}">
          <input type="hidden" name="status" value="Approved">
          <button type="submit" class="btn btn-success">Approve</button>
        </form>
        <form action="${pageContext.request.contextPath}/status?action=update" method="post" class="d-inline ms-2">
          <input type="hidden" name="statusId" value="${req.status.id}">
          <input type="hidden" name="status" value="Declined">
          <button type="submit" class="btn btn-danger">Decline</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-cn7l7gDp0eyDfzsN6CMUzoemRjB40tIHZwCt5LkzS7pgOyCyg5lturKfIvoS7H3m" crossorigin="anonymous"></script>
</body>
</html>