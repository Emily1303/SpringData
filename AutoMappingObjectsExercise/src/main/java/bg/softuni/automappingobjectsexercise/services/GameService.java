package bg.softuni.automappingobjectsexercise.services;

public interface GameService {

    String addGame(String[] arguments);

    String editGame(String[] arguments);

    String deleteGame(String[] arguments);

    String getAllGames();

    String getDetailsAboutGame(String[] arguments);
}
