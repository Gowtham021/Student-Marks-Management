# Student-Marks-Management

This Java application manages student marks and calculates their overall percentage.

## Prerequisites

- Java 8 
- MySQL database server
- JDBC driver for MySQL

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/StudentMarksManagement.git.
   
1)Set up your MySQL database and update the URL, user, and password fields in the StudentMarks class.

2)Build and run the application:

cd StudentMarksManagement
javac StudentMarks.java
java StudentMarks


## Usage
1)Edit the main method in StudentMarks to register students and their marks.

2)Run the application to see the overall percentage for each student.

## Database Structure
The application uses a MySQL database with the following table structure:
   
   ```bash
CREATE TABLE IF NOT EXISTS studentsmarks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    Tamil INT,
    English INT,
    Maths INT,
    Java INT,
    MySql INT,
    percentage DECIMAL(5,2)
);


 
 



 


