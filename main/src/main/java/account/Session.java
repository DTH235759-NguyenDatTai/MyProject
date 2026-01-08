package account;

public class Session {
    public static boolean isLogin = false;
    public static String username;
    public static String email;

    public static void clear() {
        isLogin = false;
        username = null;
        email = null;
    }
}
