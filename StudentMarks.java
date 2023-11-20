import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMarks {

     
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/studentsmark_list?user=root&password=root";
    private static final int TOTAL_SUBJECTS = 5;

    public static void main(String[] args) {
        // Example usage:
        registerStudent("gowtham", 75, 90, 78, 92, 78);
        registerStudent("jeevan", 85, 20, 90, 92, 98);
        registerStudent("jega", 68, 90, 78, 92, 88);
        registerStudent("harini", 78, 90, 70, 95, 86);



    }

    public static void registerStudent(String name, int Tamil,
    		int English, int Maths, int Java, int MySql) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            // Create the Student table if not exists
            createStudentTable(connection);

            // Insert student marks
            String insertQuery = "INSERT INTO studentsmarks(name, Tamil, English, Maths, Java, MySql) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, Tamil);
                preparedStatement.setInt(3, English);
                preparedStatement.setInt(4, Maths);
                preparedStatement.setInt(5, Java);
                preparedStatement.setInt(6, MySql);

                preparedStatement.executeUpdate();
                System.out.println(" ");

                // Calculate and display overall percentage
                calculatePercentage(name, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
        
    public static void calculatePercentage(String name, Connection connection) {
        try {
            String query = "SELECT ((Tamil + English + Maths + Java + MySql) * 100 / (? * ?)) AS percentage FROM studentsmarks WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, TOTAL_SUBJECTS);
                preparedStatement.setInt(2, 100); // Assuming 100 as the maximum marks for each subject
                preparedStatement.setString(3, name);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double percentage = resultSet.getDouble("percentage");
                    System.out.println(name + "'s Overall Percentage: " + percentage + "%");
                    
                    // Update the percentage in the database
                    updatePercentage(name, percentage, connection);
                } else {
                    System.out.println("Student not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updatePercentage(String name, double percentage, Connection connection) {
        try {
            String updateQuery = "UPDATE studentsmarks SET percentage = ? WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, percentage);
                preparedStatement.setString(2, name);

                preparedStatement.executeUpdate();
                System.out.println(" ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
            private static void createStudentTable(Connection connection) throws SQLException {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS studentsmarks (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "Tamil INT," +
                        "English INT," +
                        "Maths INT," +
                        "Java INT," +
                        "MySql INT," +
                        "percentage DECIMAL(5,2)" +  // Add the new column
                        ")";
                try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
                    preparedStatement.executeUpdate();
                }
            }
        
    
}
