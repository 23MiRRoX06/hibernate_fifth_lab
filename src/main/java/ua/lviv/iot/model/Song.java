package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Entity
public class Song {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;

  @Basic
  @Column(name = "title")
  private String title;

  @Basic
  @Column(name = "price")
  private BigDecimal price;

  @Basic
  @Column(name = "duration_in_minutes")
  private Double durationInMinutes;

  @Basic
  @Column(name = "release_date")
  private Date releaseDate;

  @Basic
  @Column(name = "popularity")
  private Integer popularity;

  @Basic
  @Column(name = "with_parental_advisory_logo")
  private Boolean withParentalAdvisoryLogo;

  @ManyToOne
  @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false)
  private Artist artistByArtistId;

  @ManyToOne
  @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)
  private Album albumByAlbumId;

  @ManyToOne
  @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
  private Genre genreByGenreId;

  @ManyToOne
  @JoinColumn(name = "music_video_id", referencedColumnName = "id")
  private MusicVideo musicVideoByMusicVideoId;

  @ToString.Exclude
  @ManyToMany(mappedBy = "downloaded_songs")
  private Set<Customer> customers_downloaded_by;

  @ToString.Exclude
  @ManyToMany(mappedBy = "reviewed_songs")
  private Set<Customer> customers_reviewed_by;
}
