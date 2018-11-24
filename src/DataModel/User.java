package DataModel;

import java.security.InvalidParameterException;

public class User {
    public enum Type {
        VISITOR, STAFF, ADMIN;
    }
    public String username;
    public String email;
    public Type type;
    public String password;

    public User(String username, String email, String password, Type type){
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
    }
    public User(){

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Type getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static Type stringToType(String typeAsString){
        if(typeAsString.equals("Visitor")){
            return Type.VISITOR;
        }
        else if (typeAsString.equals("Staff")){
            return Type.STAFF;
        }
        else if(typeAsString.equals("Admin")) {
            return Type.ADMIN;
        }
        else {
            throw new InvalidParameterException();
        }
    }
}
