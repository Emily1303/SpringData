package bg.softuni.automappingobjectsexercise.services;

import bg.softuni.automappingobjectsexercise.entities.Game;
import bg.softuni.automappingobjectsexercise.models.AddGameDto;
import bg.softuni.automappingobjectsexercise.models.EditGameDto;
import bg.softuni.automappingobjectsexercise.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private UserService userService;

    ModelMapper modelMapper;

    public AddGameDto addGameDto;

    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(String[] arguments) {
        boolean isLoggedUserAdmin = userService.isLoggedUserAdmin();
        if (!isLoggedUserAdmin) {
            throw new IllegalArgumentException("User is not administrator!");
        }

        int lengthOfParameters = arguments.length;

        String title = lengthOfParameters > 1 ? arguments[1] : "";
        BigDecimal price = lengthOfParameters > 2 ?
                new BigDecimal(arguments[2]) : BigDecimal.ZERO;
        Double size = lengthOfParameters > 3 ? Double.parseDouble(arguments[3]) : 0.0;
        String trailer = lengthOfParameters > 4 ? arguments[4] : "";
        String imageThumbnail = lengthOfParameters > 5 ? arguments[5] : "";
        String description = lengthOfParameters > 6 ? arguments[6] : "";

        String[] date = arguments[7].split("-");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        LocalDate releaseDate = lengthOfParameters > 7 ? LocalDate.of(year, month, day) : LocalDate.now();

        try {
            addGameDto = new AddGameDto(title, price, size, trailer, imageThumbnail, description, releaseDate);
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }

        Game game = modelMapper.map(addGameDto, Game.class);
        gameRepository.saveAndFlush(game);

        return "Added " + game.getTitle();
    }

    @Override
    public String editGame(String[] arguments) {
        boolean isLoggedUserAdmin = userService.isLoggedUserAdmin();
        if (!isLoggedUserAdmin) {
            throw new IllegalArgumentException("User is not administrator!");
        }

        Long idToBeEdited = Long.parseLong(arguments[1]);
        Optional<Game> byId = gameRepository.findById(idToBeEdited);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("This game does not exist!");
        }

        Game gameToBeEdited = byId.get();

        Map<String, String> newValues = new HashMap<>();
        for (int i = 2; i < arguments.length; i++) {
            String command = arguments[i];
            String key = command.split("=")[0];
            String value = command.split("=")[1];
            newValues.put(key, value);
        }

        EditGameDto editGameDto = modelMapper.map(gameToBeEdited, EditGameDto.class);
        editGameDto.updateFields(newValues);

        gameToBeEdited = modelMapper.map(editGameDto, Game.class);
        gameRepository.saveAndFlush(gameToBeEdited);

        return "Edited " + gameToBeEdited.getTitle();
    }

    @Override
    public String deleteGame(String[] arguments) {

        boolean isLoggedUserAdmin = userService.isLoggedUserAdmin();
        if (!isLoggedUserAdmin) {
            throw new IllegalArgumentException("User is not administrator!");
        }

        Long idToBeDeleted = Long.parseLong(arguments[1]);
        Optional<Game> byId = gameRepository.findById(idToBeDeleted);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("This game does not exist!");
        }

        Game gameToBeDeleted = byId.get();
        gameRepository.delete(gameToBeDeleted);

        return "Deleted " + gameToBeDeleted.getTitle();
    }

    @Override
    public String getAllGames() {
        List<Game> allGames = gameRepository.findAll();
        for (Game game : allGames) {
            return game.getTitle() + " " + game.getPrice();
        }

        return null;
    }

    @Override
    public String getDetailsAboutGame(String[] arguments) {
        String title = arguments[1];
        Game gameByTitle = gameRepository.findByTitle(title);

        String output = String.format("Title: %s\n" +
                        "Price: %s\n" +
                        "Description: %s\n" +
                        "Release date: %s\n", gameByTitle.getTitle(), gameByTitle.getPrice(),
                gameByTitle.getDescription(), gameByTitle.getReleaseDate());

        return output;
    }
}
