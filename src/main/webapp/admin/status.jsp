<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Manage Statuses</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/style/status.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1>Status Management</h1>

  <!-- Create Button to trigger the modal -->
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createStatusModal">
    Create Status
  </button>

  <h2>Existing Statuses</h2>
  <table class="table">
    <thead>
    <tr>
      <th>Status</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sts" items="${status}">
      <tr>
        <td>${sts.status}</td>
        <td>
          <form action="${pageContext.request.contextPath}/status?action=delete" method="post" style="display:inline;">
            <input type="hidden" name="id" value="${sts.id}">
            <input type="hidden" name="action" value="delete">
            <button type="submit" class="btn btn-danger">Delete</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<!-- Modal for creating a new status -->
<div class="modal fade" id="createStatusModal" tabindex="-1" aria-labelledby="createStatusModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="createStatusModalLabel">Create New Status</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="createStatusForm" action="${pageContext.request.contextPath}/status?action=insert" method="post">
          <input type="hidden" name="action" value="insert">
          <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <input type="text" class="form-control" id="status" name="status" required>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-primary" form="createStatusForm">Create</button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/status.js"></script>
</body>
</html>
