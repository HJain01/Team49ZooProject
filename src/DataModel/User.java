package DataModel;

public class User {
    public enum Type {
        VISITOR, STAFF, ADMIN;
    }
    public String username;
    public String email;
    public Type type;
    public String password;
}
