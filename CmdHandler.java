import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CmdHandler {
    Connection conn;
    Gson gson = new Gson();
    SqlHandle sqlHandle = new SqlHandle();


    public CmdHandler() {

        String hostName = "localhost";
        String dbName = "mc_lorencia";
        String userName = "root";
        String password = "123123Aa";

        try {
            conn = getMySQLConnection(hostName,dbName,userName,password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException {

        //Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        return DriverManager.getConnection(connectionURL, userName, password);

    }

    public String handle(String result) {
        String resp = "";
        CmdPacket cmdPacket = gson.fromJson(result, CmdPacket.class);
        if (cmdPacket == null){
            return "";
        }
        switch (cmdPacket.getCmdType()){
            case "USER_DB":{
                UserDBO userDBO = new UserDBO().fromStr(cmdPacket.getCmdParams());
                if (userDBO == null){
                    return ("Formation error! <TOKEN>,<TYPE>,<USERNAME>,<EMAIL>,<HASHPASSWORD>");
                }
                System.out.println(gson.toJson(userDBO));
                resp = sqlHandle.saveUser(userDBO);
                System.out.println(resp);
            }
            case "USER_DB_ALL":{
                return sqlHandle.getAllUser();
            }
        }
        return resp;
    }
}
