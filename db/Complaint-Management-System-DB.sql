create database cms;
use cms;

CREATE TABLE users (
    user_id VARCHAR(255),
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    mobile VARCHAR(15),
    email VARCHAR(100),
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    department VARCHAR(100),
    job_role VARCHAR(100)
);

CREATE TABLE complaints (
    complaint_id VARCHAR(255),
    user_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    status ENUM('PENDING', 'IN_PROGRESS', 'RESOLVED') DEFAULT 'PENDING',
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Insert Queries for User--> Admins
INSERT INTO users 
(user_id, name, address, mobile, email, username, password, department, job_role)
VALUES 
('U001', 'Nuwan Perera', 'No.10, Galle Road, Colombo 03', '0771234567', 'nuwan.admin@example.com',
 'nuwanp', 'admin123', 'Administration', 'System Admin'),

('U002', 'Kanchana Silva', 'No.55, Lake Drive, Kandy', '0712345678', 'kanchana.admin@example.com',
 'kanchanas', 'admin456', 'HR', 'HR Manager');
 
 -- Insert Queries for User--> Employees
 INSERT INTO users 
(user_id, name, address, mobile, email, username, password, department, job_role)
VALUES 
('U003', 'Kasun Fernando', 'No.88, Main Street, Gampaha', '0751234567', 'kasun@example.com',
 'kasunf', 'emp123', 'IT', 'Software Engineer'),

('U004', 'Dilani Jayasinghe', 'No.23, Hospital Road, Kurunegala', '0762345678', 'dilani@example.com',
 'dilanij', 'emp456', 'Finance', 'Accountant'),

('U005', 'Amal Madushanka', 'No.42, Temple Road, Matara', '0783456789', 'amal@example.com',
 'amalm', 'emp789', 'Operations', 'Operations Executive'),

('U006', 'Sachini Ranasinghe', 'No.9, Park Avenue, Negombo', '0744567890', 'sachini@example.com',
 'sachinir', 'emp321', 'Marketing', 'Marketing Officer'),

('U007', 'Tharindu Weerasinghe', 'No.60, Sea Street, Trincomalee', '0725678901', 'tharindu@example.com',
 'tharinduw', 'emp654', 'Logistics', 'Logistics Assistant');

 -- Insert Queries for complaints
INSERT INTO complaints 
(complaint_id, user_id, title, description)
VALUES
('C001', 'U003', 'Printer Not Working', 'Printer in IT room is not functioning.'),
('C002', 'U004', 'Network Down', 'Finance department network is offline.'),
('C003', 'U005', 'Air Conditioner Broken', 'AC not cooling in meeting room.'),
('C004', 'U006', 'Software Bug', 'Marketing system throws error when uploading images.'),
('C005', 'U007', 'Power Fluctuation', 'Frequent power drops in logistics area.');

