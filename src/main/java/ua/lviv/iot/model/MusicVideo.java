package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "music_video", schema = "ostap_koziaryk_itunes_full")
public class MusicVideo {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;

  @Basic
  @Column(name = "size_in_megabytes")
  private Double sizeInMegabytes;

  @Basic
  @Column(name = "release_date")
  private Date releaseDate;

  @OneToMany(mappedBy = "musicVideoByMusicVideoId")
  @ToString.Exclude
  private List<Song> songsById;
}
