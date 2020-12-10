package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "artist", schema = "ostap_koziaryk_itunes_full")
public class Artist {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;

  @Basic
  @Column(name = "title")
  private String title;

  @Basic
  @Column(name = "total_songs")
  private Integer totalSongs;

  @Basic
  @Column(name = "total_albums")
  private Integer totalAlbums;

  @ToString.Exclude
  @OneToMany(mappedBy = "artistByArtistId")
  private List<Album> albumsById;

  @ToString.Exclude
  @OneToMany(mappedBy = "artistByArtistId")
  private List<Song> songsById;
}
