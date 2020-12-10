package ua.lviv.iot.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.lviv.iot.business.implementation.MusicVideoServiceImpl;
import ua.lviv.iot.model.MusicVideo;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MusicVideoController {
  @Getter
  private static final MusicVideoController INSTANCE = new MusicVideoController();
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private MusicVideoServiceImpl musicVideoService = MusicVideoServiceImpl.getINSTANCE();

  public void createMusicVideo() {
    MusicVideo musicVideo = createMusicVideoInstanceFromUserInputs();
    musicVideoService.create(musicVideo);
    System.out.println("There are 1 row created");
  }

  public void updateMusicVideo() {
    MusicVideo musicVideo = createMusicVideoInstanceFromUserInputs();
    System.out.println("Input id(id) for MusicVideo:  ");
    musicVideo.setId(input.nextInt());
    musicVideoService.update(musicVideo);
    System.out.println("There are 1 row updated");
  }

  public void deleteMusicVideo() {
    System.out.println("Input id(id) for MusicVideo");
    Integer deleteId = input.nextInt();
    int count = musicVideoService.delete(deleteId);
    System.out.println("There are " + count + " rows deleted");
  }

  public void selectAllFromMusicVideo() {
    System.out.println("\nTable: MusicVideo");
    List<MusicVideo> musicVideos = musicVideoService.findAll();
    for (MusicVideo musicVideo : musicVideos) {
      System.out.println(musicVideo);
    }
  }

  public void selectFromMusicVideoById() {
    System.out.println("Input id(id) for MusicVideo");
    Integer selectId = input.nextInt();
    MusicVideo musicVideo = musicVideoService.findById(selectId);
    System.out.println(musicVideo);
  }

  private MusicVideo createMusicVideoInstanceFromUserInputs() {
    System.out.println("Input sizeInMegabytes(size_in_megabytes) for MusicVideo:  ");
    Double sizeInMegabytes = input.nextDouble();
    input.nextLine();
    System.out.println("Input releaseDate(release_date) for MusicVideo:  ");
    Date releaseDate = Date.valueOf(input.nextLine());
    return new MusicVideo()
        .setSizeInMegabytes(sizeInMegabytes)
        .setReleaseDate(releaseDate);
  }
}
