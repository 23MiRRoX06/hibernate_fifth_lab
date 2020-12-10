package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Entity
public class Album {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;

  @Basic
  @Column(name = "title")
  private String title;

  @Basic
  @Column(name = "release_date")
  private Date releaseDate;

  @Basic
  @Column(name = "price")
  private BigDecimal price;

  @Basic
  @Column(name = "total_items")
  private Integer totalItems;

  @ManyToOne
  @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false)
  private Artist artistByArtistId;

  @ManyToOne
  @JoinColumn(name = "genre_id", referencedColumnName = "id")
  private Genre genreByGenreId;

  @ToString.Exclude
  @OneToMany(mappedBy = "albumByAlbumId")
  private List<Song> songsById;

  @ToString.Exclude
  @ManyToMany(mappedBy = "albums")
  private Set<RecordLabel> record_labels;

  @ToString.Exclude
  @ManyToMany(mappedBy = "downloaded_albums")
  private Set<Customer> customers_downloaded_by;

  @ToString.Exclude
  @ManyToMany(mappedBy = "reviewed_albums")
  private Set<Customer> customers_reviewed_by;
}
