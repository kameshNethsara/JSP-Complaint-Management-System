# Web-Based Complaint Management System (CMS)

## Project Overview
This project is a web-based Complaint Management System developed using JSP, Servlets, and MySQL. It follows the MVC architecture pattern and provides functionality for employees to submit complaints and for admins to manage and resolve them.

## Features

### Employee Role
- Submit new complaints.
- View a list of their own complaints.
- Edit or delete complaints that are not yet resolved.

### Admin Role
- View all complaints submitted by employees.
- Update complaint status and add remarks.
- Delete complaints if necessary.

## Technology Stack
- **Frontend:** JSP, HTML, CSS, JavaScript (for form validation only)
- **Backend:** Jakarta EE Servlets, Apache Commons DBCP (Database Connection Pooling)
- **Database:** MySQL
- **Server:** Apache Tomcat

## Architecture
- **View Layer:** JSP pages (for UI)
- **Controller Layer:** Java Servlets (handle request/response)
- **Model Layer:** JavaBeans (POJOs) and DAO classes (data access)

## Project Structure

- `/src`
    - `/controller` — Servlet classes
    - `/dao` — Data Access Object classes
    - `/model` — JavaBeans (POJOs)
- `/webapp`
    - `/css` — Stylesheets
    - `/js` — JavaScript files (validation only)
    - `/jsp` — JSP files
    - `/WEB-INF`
- `schema.sql` — Database schema and initial scripts
- `README.md` — This file



## Setup Instructions

1. **Database Setup**
    - Import `schema.sql` into your MySQL database.
    - Configure database credentials in the connection pool config.

2. **Build and Deploy**
    - Use Apache Tomcat server to deploy the project.
    - Ensure Jakarta EE libraries and Apache Commons DBCP are included.

3. **Running the Application**
    - Access via browser at `http://localhost:8080/YourAppName`.
    - Login as Employee or Admin to access respective functionalities.

## Important Notes
- This system uses synchronous form submissions only (no AJAX or asynchronous requests).
- All development follows MVC architecture strictly.
- The project is developed as an individual assignment.

## Author
- Your Name: N.H.Kamesh Nethsara De Silva
- GitHub: https://github.com/kameshNethsara

## License
This project is for educational purposes and individual assignment only.

---


