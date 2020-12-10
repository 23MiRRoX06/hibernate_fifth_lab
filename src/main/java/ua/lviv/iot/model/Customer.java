package ua.lviv.iot.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Accessors(chain = true)
@Entity
public class Customer {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;

  @Basic
  @Column(name = "email")
  private String email;

  @Basic
  @Column(name = "password")
  private String password;

  @Basic
  @Column(name = "first_name")
  private String firstName;

  @Basic
  @Column(name = "surname")
  private String surname;

  @Basic
  @Column(name = "birth_date")
  private Date birthDate;

  @Basic
  @Column(name = "country")
  private String country;

  @Basic
  @Column(name = "phone_number")
  private String phoneNumber;

  @Basic
  @Column(name = "nickname")
  private String nickname;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(name = "customer_downloads_album", schema = "ostap_koziaryk_itunes_full",
      joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id",
          nullable = false),
      inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id",
          nullable = false))
  private Set<Album> downloaded_albums;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(name = "customer_reviews_album", schema = "ostap_koziaryk_itunes_full",
      joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id",
          nullable = false),
      inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id",
          nullable = false))
  private Set<Album> reviewed_albums;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(name = "customer_downloads_song", schema = "ostap_koziaryk_itunes_full",
      joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id",
          nullable = false),
      inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id",
          nullable = false))
  private Set<Song> downloaded_songs;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(name = "customer_reviews_song", schema = "ostap_koziaryk_itunes_full",
      joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id",
          nullable = false),
      inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id",
          nullable = false))
  private Set<Song> reviewed_songs;
}
