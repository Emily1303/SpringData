package bg.softuni.automappingobjectsexercise.enums;

public enum Commands {

    ;

    public static final String REGISTER_USER = "RegisterUser";
    public static final String LOG_IN_USER = "LoginUser";
    public static final String LOGOUT_USER = "Logout";
    public static final String ADD_GAME = "AddGame";
    public static final String EDIT_GAME = "EditGame";
    public static final String DELETE_GAME = "DeleteGame";
    public static final String ALL_GAMES = "AllGames";
    public static final String DETAILS_GAME = "DetailGame";
    public static final String CLOSE = "Close";

    public static final String VALIDATION_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$";
    public static final String VALIDATION_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
    public static final String VALIDATION_TITLE = "^[A-Z][a-z]{3,100}$";
    public static final String VALIDATION_TRAILER = "[a-zA-Z0-9_-]{11}$";
}
