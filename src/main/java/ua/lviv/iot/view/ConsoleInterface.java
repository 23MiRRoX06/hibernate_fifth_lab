package ua.lviv.iot.view;

import lombok.Getter;
import ua.lviv.iot.controller.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInterface {
  @Getter
  private static final ConsoleInterface INSTANCE = new ConsoleInterface();
  private ArtistController artistController = ArtistController.getINSTANCE();
  private GenreController genreController = GenreController.getINSTANCE();
  private MusicVideoController musicVideoController = MusicVideoController.getINSTANCE();
  private СustomerController customerController = СustomerController.getINSTANCE();
  private RecordLabelController recordLabelController = RecordLabelController.getINSTANCE();
  private SongController songController = SongController.getINSTANCE();
  private AlbumController albumController = AlbumController.getINSTANCE();
  private Map<String, String> menu;
  private Map<String, Printable> menuMethods;
  private Scanner input = new Scanner(System.in, "UTF-8");

  private ConsoleInterface() {
    menu = new LinkedHashMap<String, String>();
    menuMethods = new LinkedHashMap<String, Printable>();

    menu.put("A", "  A - select all tables");

    menu.put("1", "  1 - Table: Artist");
    menu.put("11", "  11 - Create Artist");
    menu.put("12", "  12 - Update Artist");
    menu.put("13", "  13 - Delete from Artist");
    menu.put("14", "  14 - Select all from Artist");
    menu.put("15", "  15 - Select by ID from Artist");

    menu.put("2", "  2 - Table: Genre");
    menu.put("21", "  21 - Create Genre");
    menu.put("22", "  22 - Update Genre");
    menu.put("23", "  23 - Delete from Genre");
    menu.put("24", "  24 - Select all from Genre");
    menu.put("25", "  25 - Select by ID from Genre");

    menu.put("3", "  3 - Table: MusicVideo");
    menu.put("31", "  31 - Create MusicVideo");
    menu.put("32", "  32 - Update MusicVideo");
    menu.put("33", "  33 - Delete from MusicVideo");
    menu.put("34", "  34 - Select all from MusicVideo");
    menu.put("35", "  35 - Select by ID from MusicVideo");

    menu.put("4", "  4 - Table: Customer");
    menu.put("41", "  41 - Create Customer");
    menu.put("42", "  42 - Update Customer");
    menu.put("43", "  43 - Delete from Customer");
    menu.put("44", "  44 - Select all from Customer");
    menu.put("45", "  45 - Select by ID from Customer");

    menu.put("5", "  5 - Table: RecordLabel");
    menu.put("51", "  51 - Create RecordLabel");
    menu.put("52", "  52 - Update RecordLabel");
    menu.put("53", "  53 - Delete from RecordLabel");
    menu.put("54", "  54 - Select all from RecordLabel");
    menu.put("55", "  55 - Select by ID from RecordLabel");

    menu.put("6", "  6 - Table: Song");
    menu.put("61", "  61 - Create Song");
    menu.put("62", "  62 - Update Song");
    menu.put("63", "  63 - Delete from Song");
    menu.put("64", "  64 - Select all from Song");
    menu.put("65", "  65 - Select by ID from Song");

    menu.put("7", "  7 - Table: Album");
    menu.put("71", "  71 - Create Album");
    menu.put("72", "  72 - Update Album");
    menu.put("73", "  73 - Delete from Album");
    menu.put("74", "  74 - Select all from Album");
    menu.put("75", "  75 - Select by ID from Album");

    menu.put("Q", "  Q - exit");

    menuMethods.put("A", this::selectAllTables);

    menuMethods.put("11", artistController::createArtist);
    menuMethods.put("12", artistController::updateArtist);
    menuMethods.put("13", artistController::deleteArtist);
    menuMethods.put("14", artistController::selectAllFromArtist);
    menuMethods.put("15", artistController::selectFromArtistById);

    menuMethods.put("21", genreController::createGenre);
    menuMethods.put("22", genreController::updateGenre);
    menuMethods.put("23", genreController::deleteGenre);
    menuMethods.put("24", genreController::selectAllFromGenre);
    menuMethods.put("25", genreController::selectFromGenreById);

    menuMethods.put("31", musicVideoController::createMusicVideo);
    menuMethods.put("32", musicVideoController::updateMusicVideo);
    menuMethods.put("33", musicVideoController::deleteMusicVideo);
    menuMethods.put("34", musicVideoController::selectAllFromMusicVideo);
    menuMethods.put("35", musicVideoController::selectFromMusicVideoById);

    menuMethods.put("41", customerController::createCustomer);
    menuMethods.put("42", customerController::updateCustomer);
    menuMethods.put("43", customerController::deleteCustomer);
    menuMethods.put("44", customerController::selectAllFromCustomer);
    menuMethods.put("45", customerController::selectFromCustomerById);

    menuMethods.put("51", recordLabelController::createRecordLabel);
    menuMethods.put("52", recordLabelController::updateRecordLabel);
    menuMethods.put("53", recordLabelController::deleteRecordLabel);
    menuMethods.put("54", recordLabelController::selectAllFromRecordLabel);
    menuMethods.put("55", recordLabelController::selectFromRecordLabelById);

    menuMethods.put("61", songController::createSong);
    menuMethods.put("62", songController::updateSong);
    menuMethods.put("63", songController::deleteSong);
    menuMethods.put("64", songController::selectAllFromSong);
    menuMethods.put("65", songController::selectFromSongById);

    menuMethods.put("71", albumController::createAlbum);
    menuMethods.put("72", albumController::updateAlbum);
    menuMethods.put("73", albumController::deleteAlbum);
    menuMethods.put("74", albumController::selectAllFromAlbum);
    menuMethods.put("75", albumController::selectFromAlbumById);

    menuMethods.put("Q", this::exitOutput);
  }

  private void selectAllTables() {
    artistController.selectAllFromArtist();
    genreController.selectAllFromGenre();
    musicVideoController.selectAllFromMusicVideo();
    customerController.selectAllFromCustomer();
    recordLabelController.selectAllFromRecordLabel();
    songController.selectAllFromSong();
    albumController.selectAllFromAlbum();
  }

  private void exitOutput() {
    System.out.println("Exiting program....");
  }

  private void outputMenu() {
    System.out.println("\nMENU:");
    for (String key : menu.keySet()) {
      if (key.length() == 1) {
        System.out.println(menu.get(key));
      }
    }
  }

  private void outputSubMenu(String subMenuKey) {
    System.out.println("\nSUB_MENU:");
    for (String key : menu.keySet()) {
      if (key.length() != 1 && key.substring(0, 1).equals(subMenuKey)) {
        System.out.println(menu.get(key));
      }
    }
  }

  public void show() {
    String menuKey;
    do {
      outputMenu();
      System.out.println("Please, select menu point.");
      menuKey = input.nextLine().toUpperCase();

      if (menuKey.matches("^\\d")) {
        outputSubMenu(menuKey);
        System.out.println("Please, select menu point.");
        menuKey = input.nextLine().toUpperCase();

      }

      try {
        menuMethods.get(menuKey).print();
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    } while (!menuKey.equals("Q"));
  }
}
