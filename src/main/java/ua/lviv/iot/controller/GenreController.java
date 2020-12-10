package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.GenreServiceImpl;
import ua.lviv.iot.model.Genre;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreController {
  @Getter
  private static final GenreController INSTANCE = new GenreController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private GenreServiceImpl genreService = GenreServiceImpl.getINSTANCE();

  public void createGenre() {
    input.nextLine();
    Genre genre = createGenreInstanceFromUserInputs();
    genreService.create(genre);
    System.out.println("There are 1 row created");
  }

  public void updateGenre() {
    input.nextLine();
    Genre genre = createGenreInstanceFromUserInputs();
    System.out.println("Input id(id) for Genre:  ");
    genre.setId(input.nextInt());
    genreService.update(genre);
    System.out.println("There are 1 row updated");
  }

  public void deleteGenre() {
    System.out.println("Input id(id) for Genre");
    Integer deleteId = input.nextInt();
    int count = genreService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromGenre() {
    System.out.println("\nTable: Genre");
    List<Genre> genres = genreService.findAll();
    for (Genre genre : genres) {
      System.out.println(genre);
    }
  }

  public void selectFromGenreById() {
    System.out.println("Input id(id) for Genre");
    Integer selectId = input.nextInt();
    Genre genre = genreService.findById(selectId);
    System.out.println(genre);
  }

  private Genre createGenreInstanceFromUserInputs() {
    System.out.println("Input name(name) for Genre:  ");
    String name = input.nextLine();
    System.out.println("Input description(description) for Genre:  ");
    String description = input.nextLine();
    return new Genre()
        .setName(name)
        .setDescription(description);
  }
}
