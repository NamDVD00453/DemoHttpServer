import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlHandle {
    Connection conn;
    Gson gson = new Gson();

    String hostName = "localhost";
    String dbName = "mc_lorencia";
    String userName = "root";
    String password = "123123Aa";
    String accountTable = "account";

    public SqlHandle() {
        try {
            conn = getMySQLConnection(hostName, dbName, userName, password);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public String getAllUser(){
        String SqlQuery = "SELECT * FROM " + accountTable;
        List<UserDBO> listUser = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SqlQuery);
            UserDBO u = null;
            while (rs.next()){
                u = new UserDBO();
                u.setUsername(rs.getString("username"));
                u.setHashPassword(rs.getString("password"));
                listUser.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return gson.toJson(listUser);
    }

    public String saveUser(UserDBO userDBO){
        PreparedStatement preparedStatement = null;
        //String SqlQuery = "INSERT INTO `" + dbName +"`.`" + accountTable + "` (`username`, `password`, `password_salt`) VALUES (? , ? , ?);";
        String SqlQuery = "INSERT INTO " + accountTable + "(username, password, password_salt) VALUES (?, ?, ?);";
        try {
            preparedStatement = conn.prepareStatement(SqlQuery);
            preparedStatement.setString(1, userDBO.username);
            preparedStatement.setString(2, userDBO.hashPassword);
            preparedStatement.setString(3, userDBO.token);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(preparedStatement.toString());
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            System.out.println();
            return e.getErrorCode() + "|" + e.getMessage() + "|";
        }
        return ("User created! ");
    }
}