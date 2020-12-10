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
public class Genre {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;

  @Basic
  @Column(name = "name")
  private String name;

  @Basic
  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "genreByGenreId")
  @ToString.Exclude
  private List<Album> albumsById;

  @OneToMany(mappedBy = "genreByGenreId")
  @ToString.Exclude
  private List<Song> songsById;
}
