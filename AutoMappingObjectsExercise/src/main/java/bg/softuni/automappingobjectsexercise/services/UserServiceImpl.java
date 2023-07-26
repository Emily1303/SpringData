package bg.softuni.automappingobjectsexercise.services;

import bg.softuni.automappingobjectsexercise.entities.User;
import bg.softuni.automappingobjectsexercise.models.UserRegisterDto;
import bg.softuni.automappingobjectsexercise.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    UserRegisterDto userRegisterDto;
    boolean isUserLoggedIn = false;
    User loggedInUser;

    ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(String[] arguments) {
        int lengthOfParameters = arguments.length;

        String email = lengthOfParameters > 1 ? arguments[1] : "";
        String password = lengthOfParameters > 2 ? arguments[2] : "";
        String confirmPassword = lengthOfParameters > 3 ? arguments[3] : "";
        String fullName = lengthOfParameters > 4 ? arguments[4] : "";

        try {
            userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }

        Optional<User> firstByEmail = userRepository.findFirstByEmail(email);
        if (firstByEmail.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists!");
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        if (userRepository.count() == 0) {
            user.setAdministrator(true);
        }

        userRepository.saveAndFlush(user);

        return userRegisterDto.printRegisteredUser();
    }

    @Override
    public String logInUser(String[] arguments) {
        int lengthOfParameters = arguments.length;

        String email = lengthOfParameters > 1 ? arguments[1] : "";
        String password = lengthOfParameters > 2 ? arguments[2] : "";

        Optional<User> firstByEmail = userRepository.findFirstByEmail(email);
        if (firstByEmail.isEmpty()) {
            throw new IllegalArgumentException("Incorrect username / password");
        }

        String realPassword = firstByEmail.get().getPassword();
        if (!realPassword.equals(password)) {
            throw new IllegalArgumentException("Incorrect username / password");
        }

        isUserLoggedIn = true;
        loggedInUser = firstByEmail.get();

        return "Successfully logged in " + loggedInUser.getFullName();
    }

    @Override
    public String logoutUser() {

        if (!isUserLoggedIn) {
            throw new IllegalArgumentException("Cannot log out. No user was logged in.");
        } else {
            isUserLoggedIn = false;
        }

        return "User " + loggedInUser.getFullName() + " successfully logged out";
    }

    @Override
    public boolean isLoggedUserAdmin() {
        return loggedInUser != null && loggedInUser.getAdministrator();
    }
}
