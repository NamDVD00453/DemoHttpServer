public class UserDBO {
    public String token;
    public String cmdType;
    public String username;
    public String email;
    public String hashPassword;

    public UserDBO(String token, String cmdType, String username, String email, String hashPassword) {
        this.token = token;
        this.cmdType = cmdType;
        this.username = username;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public UserDBO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public UserDBO fromStr(String st){
        String[] params = st.split(",");
        if (params.length != 5){
            return null;
        }
        return new UserDBO(params[0], params[1], params[2], params[3], params[4]);
    }
}
