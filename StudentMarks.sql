create database studentsmark_list;
use studentsmark_list;
  
CREATE TABLE IF NOT EXISTS studentsmarks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    Tamil INT,
    English INT,
    Maths INT,
    Java INT,
    MySql INT,
    percentage decimal(5,1)
);
 

select *from studentsmarks;
    
     
     


