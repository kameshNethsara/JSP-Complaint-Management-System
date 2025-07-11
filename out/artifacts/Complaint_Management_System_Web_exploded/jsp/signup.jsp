<%--
  Created by IntelliJ IDEA.
  User: kames
  Date: 6/12/2025
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<String> usernames = (List<String>) request.getAttribute("existingUsernames");
    if (usernames == null) usernames = new ArrayList<>();
    String jsonUsernames = new Gson().toJson(usernames);
%>
<html>
<head>
    <title>SignUp - Complaint Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="signup-container">
    <div class="signup-header">
        <h2>Create Your Account</h2>
        <p>Join our Complaint Management System to submit and track complaints efficiently</p>
    </div>

    <div class="signup-form">
        <form id="signupForm" action="${pageContext.request.contextPath}/signup" method="post">
            <div class="form-row">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <i class="fas fa-user"></i>
                    <input type="text" id="firstName" name="firstName" required placeholder="Enter your first name">
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <i class="fas fa-user"></i>
                    <input type="text" id="lastName" name="lastName" required placeholder="Enter your last name">
                </div>
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <i class="fas fa-map-marker-alt"></i>
                <input type="text" id="address" name="address" required placeholder="Enter your address">
            </div>

            <div class="form-group">
                <label for="mobile">Mobile Number</label>
                <i class="fas fa-phone"></i>
                <input type="tel" id="mobile" name="mobile" required placeholder="Enter your mobile number">
            </div>

            <div class="form-group">
                <label for="email">Email Address</label>
                <i class="fas fa-envelope"></i>
                <input type="email" id="email" name="email" required placeholder="Enter your email">
            </div>

            <div class="form-group">
                <label for="username">Username</label>
                <i class="fas fa-user-circle"></i>
                <input type="text" id="username" name="username" required placeholder="Choose a username">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <i class="fas fa-lock"></i>
                <div class="password-container">
                    <input type="password" id="password" name="password" required placeholder="Create a password">
                    <span class="toggle-password" onclick="togglePassword()">
                        <i class="fas fa-eye"></i>
                    </span>
                </div>
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <i class="fas fa-lock"></i>
                <div class="password-container">
                    <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Confirm your password">
                    <span class="toggle-password" onclick="toggleConfirmPassword()">
                        <i class="fas fa-eye"></i>
                    </span>
                </div>
            </div>

            <div class="form-group">
                <label for="department">Department</label>
                <i class="fas fa-building"></i>
                <select id="department" name="department" required>
                    <option value="" disabled selected>Select your department</option>
                    <option value="Finance">Finance Department</option>
                    <option value="HR">Human Resources</option>
                    <option value="IT">Information Technology</option>
                    <option value="Operations">Operations</option>
                    <option value="Customer Service">Customer Service</option>
                </select>
            </div>

            <div class="form-group">
                <label for="jobRole">Job Role</label>
                <i class="fas fa-briefcase"></i>
                <select id="jobRole" name="jobRole" required>
                    <option value="" disabled selected>Select your job role</option>
                    <option value="admin">Admin</option>
                    <option value="employee">Employee</option>
                </select>
            </div>

            <div class="terms">
                <input type="checkbox" id="terms" name="terms" required>
                <label for="terms">I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a></label>
            </div>

            <button id="btn-signup" type="submit">Create Account</button>

            <div class="error" id="error-message">Passwords do not match. Please try again.</div>

            <div class="login-link">
                Already have an account? <a href="${pageContext.request.contextPath}/jsp/login.jsp">Log in</a>
            </div>
        </form>
    </div>

    <div class="signup-image">
        <svg class="signup-illustration" viewBox="0 0 500 500" xmlns="http://www.w3.org/2000/svg">
            <circle cx="250" cy="250" r="200" fill="#f0f5ff" opacity="0.8"/>
            <circle cx="250" cy="180" r="70" fill="#1a2a6c" opacity="0.7"/>
            <path d="M200 250 Q250 350 300 250 L250 450 Z" fill="#4a235a" opacity="0.7"/>
            <rect x="150" y="280" width="200" height="120" rx="10" fill="#2c3e50" opacity="0.7"/>
            <rect x="180" y="300" width="50" height="15" rx="5" fill="#f0f5ff"/>
            <rect x="180" y="330" width="50" height="15" rx="5" fill="#f0f5ff"/>
            <rect x="270" y="300" width="50" height="15" rx="5" fill="#f0f5ff"/>
            <rect x="270" y="330" width="50" height="15" rx="5" fill="#f0f5ff"/>
            <circle cx="210" cy="220" r="15" fill="#f0f5ff"/>
            <circle cx="290" cy="220" r="15" fill="#f0f5ff"/>
        </svg>
    </div>
</div>

<input type="hidden" id="existingUsernames" value='<%= jsonUsernames %>'>

<footer>
    <div class="footer-container">
        <div class="footer-section about">
            <h2>Complaint Management System</h2>
            <p>Streamlining internal complaint handling for improved response and accountability.</p>
        </div>
        <div class="footer-section links">
            <h3>Quick Links</h3>
            <ul>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Submit Complaint</a></li>
                <li><a href="#">Admin Panel</a></li>
            </ul>
        </div>
        <div class="footer-section contact">
            <h3>Contact Us</h3>
            <p>Email: support@cms.gov.lk</p>
            <p>Phone: +94 11 222 3333</p>
            <p>Address: 123 Government Complex, Galle, Sri Lanka</p>
        </div>
    </div>
    <div class="footer-bottom">
        <p>&copy; 2025 CMS Project. All Rights Reserved. | Developed by IJSE 72 Batch ID-241722037</p>
    </div>
</footer>
<script>
    // Signup alerts based on parameters
    <%if ("username_taken".equals(request.getParameter("signup"))) { %>
    Swal.fire({
        icon: "warning",
        title: "Username Taken!",
        text: "Please choose a different username."
    });
    <% } else if ("pass_mismatch".equals(request.getParameter("signup"))) { %>
    Swal.fire({ icon: "error", title: "Passwords do not match!", text: "Please try again." });
    <% } else if ("fail".equals(request.getParameter("signup"))) { %>
    Swal.fire({ icon: "error", title: "Oops!", text: "Something went wrong. Please try again." });
    <% } else if ("success".equals(request.getParameter("signup"))) { %>
    Swal.fire({ icon: "success", title: "Account Created!", text: "You can now log in." });
    <% } %>
</script>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>


