package bg.softuni.automappingobjectsexercise.models;

import java.util.regex.Pattern;

import static bg.softuni.automappingobjectsexercise.enums.Commands.VALIDATION_EMAIL;
import static bg.softuni.automappingobjectsexercise.enums.Commands.VALIDATION_PASSWORD;

public class UserRegisterDto {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    private void validate() {
        boolean isEmailValid = Pattern.matches(VALIDATION_EMAIL, email);
        if (!isEmailValid) {
            throw new IllegalArgumentException("Email not valid!");
        }

        boolean isPasswordValid = Pattern.matches(VALIDATION_PASSWORD, password);
        if (!isPasswordValid) {
            throw new IllegalArgumentException("Password not valid!");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password confirmation is not valid!");
        }

    }

    public String printRegisteredUser() {
        return fullName + " was registered";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}
