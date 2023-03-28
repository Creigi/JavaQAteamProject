package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldInstall() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Fedor");

        player.installGame(game);
        int expected = 0;
        int actual = player.sumGenre("Аркады");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        int expected = 3;
        int actual = player.play(game, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldErrorIfNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game, 3);
        });
    }

    @Test
    public void shouldNotInstallAlreadyInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Fedor");

        player.installGame(game);
        player.play(game, 3);
        player.installGame(game);

        int expected = 3;
        int actual = player.play(game, 0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumByGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл Онлайн 2", "Аркады");
        Game game2 = store.publishGame("Counter - Strike 2", "FPS");
        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.play(game, 4);
        player.play(game1, 3);
        player.play(game2, 5);

        int expected = 7;
        int actual = player.sumGenre("Аркады");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMaxPlayedGameByGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл Онлайн 2", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн 3", "FPS");
        Game game3 = store.publishGame("Нетология Баттл Онлайн 4", "FPS");
        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game, 3);
        player.play(game1, 5);
        player.play(game2, 6);
        player.play(game3, 8);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Аркады");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnNullIfNoGameOfGenreWasPlayed() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл Онлайн 2", "FPS");
        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 3);
        player.play(game1, 5);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стратегия");

        Assertions.assertEquals(expected, actual);
    }
}
