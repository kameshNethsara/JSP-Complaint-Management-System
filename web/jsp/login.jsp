<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Complaint Management System</title>
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
<div class="login-container">
    <h2>Login to Your Account</h2>
    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required placeholder="Enter your username">
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required placeholder="Enter your password">
        </div>

        <div class="remember-group">
            <input type="checkbox" id="remember-me" name="rememberMe">
            <label for="remember-me">Remember Me</label>
        </div>

        <button id="btn-login" type="submit">Login</button>
    </form>

    <div class="error">
        Invalid username or password. Please try again.
    </div>
</div>

<footer>
    <div class="footer-container">
        <div class="footer-section about">
            <h2>Complaint Management System</h2>
            <p>Streamlining internal complaint handling for improved response and accountability. Our system ensures efficient tracking and resolution of complaints across departments.</p>
        </div>

        <div class="footer-section links">
            <h3>Quick Links</h3>
            <ul>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Submit Complaint</a></li>
                <li><a href="#">Admin Panel</a></li>
<%--                <li><a href="#">Knowledge Base</a></li>--%>
            </ul>
        </div>

        <div class="footer-section contact">
            <h3>Contact Us</h3>
            <p>Email: support@cms.gov.lk</p>
            <p>Phone: +94 11 222 3333</p>
            <p>Address: 123 Government Complex, Galle, Sri Lanka</p>
            <p>Working Hours: Mon-Fri, 8:30 AM - 4:30 PM</p>
        </div>
    </div>

    <div class="footer-bottom">
        <p>&copy; 2025 CMS Project. All Rights Reserved. | Developed by IJSE 72 Batch ID-241722037</p>
    </div>
</footer>
</body>
</html>