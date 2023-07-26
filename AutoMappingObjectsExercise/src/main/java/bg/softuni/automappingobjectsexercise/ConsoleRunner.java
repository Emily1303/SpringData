package bg.softuni.automappingobjectsexercise;

import bg.softuni.automappingobjectsexercise.services.GameService;
import bg.softuni.automappingobjectsexercise.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static bg.softuni.automappingobjectsexercise.enums.Commands.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private GameService gameService;

    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals(CLOSE)) {
            String[] arguments = input.split("\\|");
            String commandType = arguments[0];

            switch (commandType) {
                case REGISTER_USER:
                    System.out.println(userService.registerUser(arguments));
                    break;
                case LOG_IN_USER:
                    System.out.println(userService.logInUser(arguments));
                    break;
                case LOGOUT_USER:
                    System.out.println(userService.logoutUser());
                    break;
                case ADD_GAME:
                    System.out.println(gameService.addGame(arguments));
                    break;
                case EDIT_GAME:
                    System.out.println(gameService.editGame(arguments));
                    break;
                case DELETE_GAME:
                    System.out.println(gameService.deleteGame(arguments));
                    break;
                case ALL_GAMES:
                    System.out.println(gameService.getAllGames());
                    break;
                case DETAILS_GAME:
                    System.out.println(gameService.getDetailsAboutGame(arguments));
                    break;
                default:
                    System.out.println("No command found!");
            }

            input = scanner.nextLine();
        }

    }
}
