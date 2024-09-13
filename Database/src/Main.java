import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/stafftable";
        String username = "root";
        String password = "Pa$$word";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from stafftable");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6) + " " + resultSet.getString(7) + " " + resultSet.getString(8));
            }

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Never(){
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "test";
        String password = "Pa$$word";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from course");

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3) + " " + resultSet.getString(4) + " " + resultSet.getInt(5));
            }

            connection.close();

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}