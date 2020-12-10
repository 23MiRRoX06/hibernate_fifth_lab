package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.ArtistServiceImpl;
import ua.lviv.iot.model.Artist;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtistController {
  @Getter
  private static final ArtistController INSTANCE = new ArtistController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private ArtistServiceImpl artistService = ArtistServiceImpl.getINSTANCE();

  public void createArtist() {
    input.nextLine();
    Artist artist = createArtistInstanceFromUserInputs();
    artistService.create(artist);
    System.out.println("There are 1 row created");
  }

  public void updateArtist() {
    input.nextLine();
    Artist artist = createArtistInstanceFromUserInputs();
    System.out.println("Input id(id) for Artist:  ");
    artist.setId(input.nextInt());
    artistService.update(artist);
    System.out.println("There are 1 row updated");
  }

  public void deleteArtist() {
    System.out.println("Input id(id) for Artist");
    Integer deleteId = input.nextInt();
    int count = artistService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromArtist() {
    System.out.println("\nTable: Artist");
    List<Artist> artists = artistService.findAll();
    for (Artist artist : artists) {
      System.out.println(artist);
    }
  }

  public void selectFromArtistById() {
    System.out.println("Input id(id) for Artist");
    Integer selectId = input.nextInt();
    Artist artist = artistService.findById(selectId);
    System.out.println(artist);
  }

  private Artist createArtistInstanceFromUserInputs() {
    System.out.println("Input title(title) for Artist:  ");
    String title = input.nextLine();
    System.out.println("Input totalSongs(total_songs) for Artist:  ");
    Integer totalSongs = input.nextInt();
    input.nextLine();
    System.out.println("Input totalAlbums(total_albums) for Artist:  ");
    Integer totalAlbums = input.nextInt();
    return new Artist()
        .setTotalAlbums(totalAlbums)
        .setTotalSongs(totalSongs)
        .setTitle(title);
  }
}
