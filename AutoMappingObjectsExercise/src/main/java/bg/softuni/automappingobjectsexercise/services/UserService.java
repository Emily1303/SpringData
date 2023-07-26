package bg.softuni.automappingobjectsexercise.services;

public interface UserService {

    String registerUser(String[] arguments);

    String logInUser(String[] arguments);

    String logoutUser();

    boolean isLoggedUserAdmin();
}
