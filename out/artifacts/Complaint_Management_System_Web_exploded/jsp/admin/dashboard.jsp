<%--
  Created by IntelliJ IDEA.
  User: kames
  Date: 6/12/2025
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ijse.model.Complaint" %>

<%
    List<Complaint> complaintList = (List<Complaint>) request.getAttribute("complaintList");
    String success = request.getParameter("message");
    String error = request.getParameter("error");
%>

<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-dashboard.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>

<!-- 🔝 Admin Navigation -->
<div class="navbar">
    <a href="${pageContext.request.contextPath}/admin-complaint">All Complaints</a>
    <a href="${pageContext.request.contextPath}/jsp/login.jsp">Logout</a>
</div>

<!-- ✅ SweetAlert Notification -->
<% if (success != null) { %>
<script>
    Swal.fire({
        icon: 'success',
        title: 'Success',
        text: '<%= success %>',
        confirmButtonColor: '#3085d6'
    });
</script>
<% } else if (error != null) { %>
<script>
    Swal.fire({
        icon: 'error',
        title: 'Error',
        text: '<%= error %>',
        confirmButtonColor: '#d33'
    });
</script>
<% } %>

<!-- 📋 Complaint Management Table -->
<div class="container">
    <h2>All Submitted Complaints</h2>
    <p>Logged in as: ${sessionScope.username} (${sessionScope.user_id})</p>
    <form method="get" action="${pageContext.request.contextPath}/admin-complaint" style="display: inline;">
        <button type="submit">Reload Complaints</button>
    </form>
    <table class="complaints-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Remarks</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% if (complaintList == null || complaintList.isEmpty()) { %>
        <tr><td colspan="8">No complaints available.</td></tr>
        <% } else {
            for (Complaint c : complaintList) { %>
        <tr>
            <td><%= c.getComplaintId() %></td>
            <td><%= c.getUserId() %></td>
            <td><%= c.getTitle() %></td>
            <td><%= c.getDescription() %></td>
            <td><%= c.getStatus() %></td>
            <td><%= c.getRemarks() != null ? c.getRemarks() : "N/A" %></td>
            <td><%= c.getCreatedAt() %></td>
            <td class="action-buttons">
                <form method="post" action="${pageContext.request.contextPath}/admin-complaint" class="action-form">
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="complaint_id" value="<%= c.getComplaintId() %>" />

                    <select name="status" class="status-select">
                        <option value="PENDING" <%= "PENDING".equals(c.getStatus()) ? "selected" : "" %>>PENDING</option>
                        <option value="IN_PROGRESS" <%= "IN_PROGRESS".equals(c.getStatus()) ? "selected" : "" %>>IN_PROGRESS</option>
                        <option value="RESOLVED" <%= "RESOLVED".equals(c.getStatus()) ? "selected" : "" %>>RESOLVED</option>
                    </select>

                    <input type="text" name="remarks" value="<%= c.getRemarks() != null ? c.getRemarks() : "" %>"
                           placeholder="Enter remarks" class="remarks-input" />

                    <button type="submit" class="update-btn">Update</button>
                </form>

                <form method="post" action="${pageContext.request.contextPath}/admin-complaint" class="action-form">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="complaint_id" value="<%= c.getComplaintId() %>" />
                    <button type="submit" class="delete-btn"
                            onclick="return confirm('Are you sure you want to delete this complaint?')">
                        Delete
                    </button>
                </form>
            </td>
        </tr>
        <%   }
        } %>
        </tbody>
    </table>
</div>

</body>
</html>