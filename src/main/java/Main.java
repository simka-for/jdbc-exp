import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        String user = "root";
        String pass = "admin";

        try {
            Connection connection = DriverManager.getConnection(url,user,pass);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT course_name, " +
                    "COUNT(MONTH(subscription_date)) / COUNT(DISTINCT MONTH(subscription_date)) " +
                    "AS purchases_per_month FROM purchaselist GROUP BY course_name;");
            while (resultSet.next()){
                String courseName = resultSet.getString("course_name");
                String purchasesPerMonth = resultSet.getString("purchases_per_month");
                System.out.println("Название курса: <<" + courseName + ">>\tсреднее колличество " +
                        "приобретений в месяц - " + purchasesPerMonth);
            }
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
