package Hospital.Conexion;
/**
 * @author Wilson
 */
public class UserSession {
    private static UserSession instancia;
    private static String username;
    private static String role;

    public UserSession() {
    }

    public static UserSession getInstancia() {
        return instancia;
    }

    public static void setInstancia(UserSession instancia) {
        UserSession.instancia = instancia;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserSession.username = username;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserSession.role = role;
    }
    
    

}
