package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    GameStore store = new GameStore();
    GameStore store1 = new GameStore();
    Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game1 = store1.publishGame("TES", "RPG");
    Game game2 = store.publishGame("NFS1", "Races");
    Game game3 = store.publishGame("NFS2", "Races");
    Game game4 = store1.publishGame("The Witcher", "RPG");
    Game game5 = store.publishGame("CS GO", "Shooter");


    @Test
    public void shouldAddGame() {
        assertTrue(store.containsGame(game));
    }

    @Test
    public void noGameInSuchStore() {
        assertFalse(store.containsGame(game1));
    }

    @Test void sumPlayedTimeByPlayer() {
        store.addPlayTime("Petya", 1);
        store.addPlayTime("Vitya", 3);
        store.addPlayTime("Petya", 3);

        String expected = "Petya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sumPlayedTimeFewPlayers() {
        store.addPlayTime("Petya", 10);
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Vitya", 12);
        store1.addPlayTime("Vitya", 5);
        store.addPlayTime("Boris", 3);

        int expected = 30;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sumPlayedTimeOnePlayer() {
        store.addPlayTime("Petya", 10);
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Vitya", 12);
        store1.addPlayTime("Vitya", 5);
        store.addPlayTime("Boris", 3);

        int expected = 5;
        int actual = store1.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void findMostPlayerInCatalogIfNoPlayer() {
        store.addPlayTime("Petya", 10);
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Vitya", 12);

        String expected = null;
        String actual = store1.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findMostPlayerInCatalogSmallTime() {
        store.addPlayTime("Petya", 0);
        store.addPlayTime("Vitya", 1);

        String expected = "Vitya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }
}
