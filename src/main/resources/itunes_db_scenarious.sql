CREATE DATABASE IF NOT EXISTS ostap_koziaryk_itunes_full;
USE ostap_koziaryk_itunes_full;

DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.customer_reviews_album;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.customer_reviews_song;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.customer_downloads_album;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.customer_downloads_song;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.record_label_album;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.song;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.album;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.artist;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.customer;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.genre;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.record_label;
DROP TABLE IF EXISTS ostap_koziaryk_itunes_full.music_video;

CREATE TABLE ostap_koziaryk_itunes_full.customer
(
    id           INT          NOT NULL AUTO_INCREMENT,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    first_name   VARCHAR(20)  NOT NULL,
    surname      VARCHAR(40)  NOT NULL,
    birth_date   DATE         NOT NULL,
    country      VARCHAR(56)  NOT NULL,
    phone_number VARCHAR(20)  NOT NULL UNIQUE,
    nickname     VARCHAR(30)  NOT NULL UNIQUE,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.artist
(
    id           INT          NOT NULL AUTO_INCREMENT,
    title        VARCHAR(100) NOT NULL,
    total_songs  INT UNSIGNED NOT NULL,
    total_albums INT UNSIGNED NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.album
(
    id           INT           NOT NULL AUTO_INCREMENT,
    title        VARCHAR(500)  NOT NULL,
    artist_id    INT           NOT NULL,
    release_date DATE          NOT NULL,
    price        DECIMAL(8, 2) NOT NULL,
    genre_id     INT           NULL,
    total_items  INT UNSIGNED  NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.song
(
    id                          INT               NOT NULL AUTO_INCREMENT,
    title                       VARCHAR(45)       NOT NULL,
    artist_id                   INT               NOT NULL,
    album_id                    INT               NOT NULL,
    price                       DECIMAL(8, 2)     NOT NULL,
    duration_in_minutes         FLOAT             NOT NULL,
    release_date                DATE              NOT NULL,
    genre_id                    INT               NULL,
    popularity                  INT(100) UNSIGNED NULL,
    with_parental_advisory_logo TINYINT           NULL,
    music_video_id              INT               NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.genre
(
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(30)  NOT NULL UNIQUE,
    description VARCHAR(250) NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.record_label
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.music_video
(
    id                INT    NOT NULL AUTO_INCREMENT,
    size_in_megabytes DOUBLE NOT NULL,
    release_date      DATE   NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.customer_reviews_album
(
    id          INT          NOT NULL AUTO_INCREMENT,
    customer_id INT          NOT NULL,
    album_id    INT          NOT NULL,
    title       VARCHAR(30)  NULL,
    message     VARCHAR(200) NULL,
    date        DATE         NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.customer_reviews_song
(
    id          INT          NOT NULL AUTO_INCREMENT,
    customer_id INT          NOT NULL,
    song_id     INT          NOT NULL,
    title       VARCHAR(30)  NULL,
    message     VARCHAR(200) NULL,
    date        DATE         NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.customer_downloads_album
(
    customer_id INT NOT NULL,
    album_id    INT NOT NULL,
    PRIMARY KEY (customer_id, album_id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.customer_downloads_song
(
    customer_id INT NOT NULL,
    song_id     INT NOT NULL,
    PRIMARY KEY (customer_id, song_id)
) ENGINE = InnoDB;

CREATE TABLE ostap_koziaryk_itunes_full.record_label_album
(
    record_label_id INT NOT NULL,
    album_id        INT NOT NULL,
    PRIMARY KEY (record_label_id, album_id)
) ENGINE = InnoDB;

ALTER TABLE ostap_koziaryk_itunes_full.album
    ADD CONSTRAINT FK_album_artist
        FOREIGN KEY (artist_id)
            REFERENCES ostap_koziaryk_itunes_full.artist (id)
                ON DELETE CASCADE
                ON UPDATE CASCADE,

    ADD CONSTRAINT FK_album_genre
        FOREIGN KEY (genre_id)
            REFERENCES ostap_koziaryk_itunes_full.genre (id)
            ON DELETE SET NULL
            ON UPDATE CASCADE;

ALTER TABLE ostap_koziaryk_itunes_full.song
    ADD CONSTRAINT FK_song_artist
        FOREIGN KEY (artist_id)
            REFERENCES ostap_koziaryk_itunes_full.artist (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    ADD CONSTRAINT FK_song_album
        FOREIGN KEY (album_id)
            REFERENCES ostap_koziaryk_itunes_full.album (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    ADD CONSTRAINT FK_song_genre
        FOREIGN KEY (genre_id)
            REFERENCES ostap_koziaryk_itunes_full.genre (id)
            ON DELETE SET NULL
            ON UPDATE CASCADE,

    ADD CONSTRAINT FK_song_music_video
        FOREIGN KEY (music_video_id)
            REFERENCES ostap_koziaryk_itunes_full.music_video (id)
            ON DELETE SET NULL
            ON UPDATE CASCADE;

ALTER TABLE ostap_koziaryk_itunes_full.customer_reviews_album
    ADD CONSTRAINT FK_review_customer
        FOREIGN KEY (customer_id)
            REFERENCES ostap_koziaryk_itunes_full.customer (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    ADD CONSTRAINT FK_review_album
        FOREIGN KEY (album_id)
            REFERENCES ostap_koziaryk_itunes_full.album (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE ostap_koziaryk_itunes_full.customer_reviews_song
    ADD CONSTRAINT FK_review_customer1
        FOREIGN KEY (customer_id)
            REFERENCES ostap_koziaryk_itunes_full.customer (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    ADD CONSTRAINT FK_review_song
        FOREIGN KEY (song_id)
            REFERENCES ostap_koziaryk_itunes_full.song (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE ostap_koziaryk_itunes_full.customer_downloads_album
    ADD CONSTRAINT FK_download_customer
        FOREIGN KEY (customer_id)
            REFERENCES ostap_koziaryk_itunes_full.customer (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,


    ADD CONSTRAINT FK_download_album
        FOREIGN KEY (album_id)
            REFERENCES ostap_koziaryk_itunes_full.album (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;


ALTER TABLE ostap_koziaryk_itunes_full.customer_downloads_song
    ADD CONSTRAINT FK_download_customer1
        FOREIGN KEY (customer_id)
            REFERENCES ostap_koziaryk_itunes_full.customer (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,


    ADD CONSTRAINT FK_download_song
        FOREIGN KEY (song_id)
            REFERENCES ostap_koziaryk_itunes_full.song (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE ostap_koziaryk_itunes_full.record_label_album
    ADD CONSTRAINT FK_record_label_album_record_label
        FOREIGN KEY (record_label_id)
            REFERENCES ostap_koziaryk_itunes_full.record_label (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    ADD CONSTRAINT FK_record_label_album_album
        FOREIGN KEY (album_id)
            REFERENCES ostap_koziaryk_itunes_full.album (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

INSERT INTO ostap_koziaryk_itunes_full.customer (email, password, first_name, surname, birth_date, country, phone_number,
                                            nickname)
VALUES ('ostapb@gmail.com', 'ostapko468', 'Ostap', 'Bodnarkevich', '2002-07-26', 'Ukraine', '096-683-03-07',
        'ostapbodnar228'),
       ('kirilko@yahoo.com', 'kirilrd24484', 'Kiril', 'Rashkiv', '1997-03-23', 'Russia', '3836857860', 'kirilkord'),
       ('olenkasm@yahoo.com', 'olenkasmile28237', 'Olena', 'Smikh', '1998-08-01', 'Ukraine', '063-571-18-19',
        'olenka_smile'),
       ('roman.ponizhai@gmail.com', 'romanpon8203', 'Roman', 'Ponizhai', '2003-09-17', 'Belarus', '83496-65-798',
        'romkopon'),
       ('steve.jobs@gmail.com', 'stevie63', 'Steve', 'Jobs', '1968-01-12', 'USA', '264887969258', 'iphonejobs'),
       ('oksanaal@yahoo.com', 'oksiksu34469', 'Oksana', 'Alendar', '1999-10-11', 'Russia', '575-346-765',
        'oksialen'),
       ('mashkasol@gmail.com', 'MaSHa3258', 'Maria', 'Solomon', '1984-05-04', 'Ukraine', '639897118', 'mashasol32'),
       ('billgates@yahoo.com', 'BiLLioGutse', 'Bill', 'Gates', '1973-03-17', 'USA', '468579869786', 'billsecrets'),
       ('kushnir.diana@gmail.com', 'dianosa4425', 'Diana', 'Kushnir', '2001-11-12', 'Ukraine', '987163834',
        'kushnirka2001'),
       ('samanta.k@gmail.com', 'sulimanta34', 'Samanta', 'Kenedi', '1995-06-23', 'USA', '465479868967',
        'also_kenedi');

INSERT INTO ostap_koziaryk_itunes_full.artist (title, total_songs, total_albums)
VALUES ('Queen', '150', '15'),
       ('Twenty One Pilots', '87', '6'),
       ('Imagine Dragons', '102', '8'),
       ('The Score', '88', '5'),
       ('PALC', '32', '3'),
       ('One Republic', '31', '4'),
       ('Республика Полина', '11', '1'),
       ('The Weekend', '43', '5'),
       ('Maroon 5', '122', '9'),
       ('The Man Who', '89', '7');

INSERT INTO ostap_koziaryk_itunes_full.genre (name, description)
VALUES ('rock', NULL),
       ('hip_hop', NULL),
       ('jazz', NULL),
       ('pop', NULL),
       ('blues', NULL),
       ('country', NULL),
       ('classical', NULL),
       ('electronic', NULL),
       ('disco', NULL),
       ('punk_rock', NULL);

INSERT INTO ostap_koziaryk_itunes_full.album (title, artist_id, release_date, price, genre_id, total_items)
VALUES ('Bohemian Rhapsody', '1', '2018-10-19', '4.99', '1', '22'),
       ('Trench', '2', '2018-10-05', '5.99', NULL, '14'),
       ('Evolve', '3', '2017-06-23', '5.88', NULL, '12'),
       ('Night Visions', '3', '2012-09-04', '9.99', NULL, '15'),
       ('ATLAS', '4', '2017-10-13', '4.99', NULL, '12'),
       ('Пагубное Влияние', '5', '2019-12-20', '1.99', NULL, '4'),
       ('Human', '6', '2020-12-01', '5.99', NULL, '16'),
       ('Бяскоци Красавік', '7', '2015-05-15', '10.89', NULL, '11'),
       ('After Hours', '8', '2020-05-20', '4.99', NULL, '14'),
       ('Starboy', '8', '2016-11-05', '6.99', NULL, '18');

INSERT INTO ostap_koziaryk_itunes_full.music_video(size_in_megabytes, release_date)
VALUES ('39.6', '1985-07-13'),
       ('50.4', '1987-10-17');

INSERT INTO ostap_koziaryk_itunes_full.song (title, artist_id, album_id, price, duration_in_minutes, release_date, genre_id,
                                        popularity, with_parental_advisory_logo, music_video_id)
VALUES ('Bohemian Rhapsody', '1', '1', '0.69', '5.54', '2018-10-19', '1', '100', '0', '1'),
       ('Somebody To Love', '1', '1', '0.69', '4.55', '2018-10-19', '1', '70', '1', '2'),
       ('Jumsuit', '2', '2', '0.69', '3.58', '2018-09-28', '4', '100', '1', NULL),
       ('My Blood', '2', '2', '0.69', '3.49', '2018-09-28', '4', '80', '1', NULL),
       ('Nico And The Niners', '2', '2', '0.69', '3.45', '2018-09-28', '4', '90', '1', NULL),
       ('Thunder', '3', '3', '0.49', '03.07', '2017-06-23', '4', '100', '0', NULL),
       ('Yesterday', '3', '3', '0.49', '3.25', '2017-06-23', '4', '70', '1', NULL),
       ('Radioactive', '3', '4', '0.69', '03.06', '2012-09-04', '4', '95', '0', NULL),
       ('Demons', '3', '4', '0.69', '2.57', '2012-09-04', '4', '70', '1', NULL),
       ('Legend', '4', '5', '0.49', '03.09', '2017-10-13', '4', '85', '0', NULL);

INSERT INTO ostap_koziaryk_itunes_full.record_label(name)
VALUES ('KIDinaKorner'),
       ('Interscope Records'),
       ('Republic records'),
       ('A division of UMG Recordings'),
       ('Sony Music'),
       ('A Warner Music Group Company'),
       ('Yoola'),
       ('Mosley Music'),
       ('The Weekend XO'),
       ('Virgin Records');

INSERT INTO ostap_koziaryk_itunes_full.customer_reviews_album (customer_id, album_id, title, message, date)
VALUES ('3', '1', 'Historical album', NULL, '2020-06-23'),
       ('4', '1', 'Agree with above message', NULL, '2020-06-28'),
       ('1', '2', 'LOVE TYLER', 'Love the idea of creating universe in ur songs', '2019-09-11'),
       ('1', '5', 'Обожаю их песни', NULL, '2018-05-17'),
       ('2', '10', 'Let`s go Scoressssssss', NULL, '2020-08-10'),
       ('10', '3', 'Liked their concert in Kiev', NULL, '2019-05-16'),
       ('7', '5', NULL, 'Started listening their song when this was not a hype', '2018-03-13'),
       ('6', '4', NULL, 'Listening this song only because of Radioactive', '2020-07-24');

INSERT INTO ostap_koziaryk_itunes_full.record_label_album (record_label_id, album_id)
VALUES ('1', '3'),
       ('1', '1'),
       ('9', '8'),
       ('6', '5'),
       ('2', '7'),
       ('5', '6'),
       ('5', '3');

INSERT INTO ostap_koziaryk_itunes_full.customer_downloads_song (customer_id, song_id)
VALUES ('1', '2'),
       ('2', '3'),
       ('1', '3'),
       ('2', '5'),
       ('8', '10'),
       ('6', '10'),
       ('8', '9'),
       ('8', '2'),
       ('4', '3'),
       ('10', '2');

INSERT INTO ostap_koziaryk_itunes_full.customer_downloads_album (customer_id, album_id)
VALUES ('1', '3'),
       ('2', '4'),
       ('1', '2'),
       ('2', '6'),
       ('8', '9'),
       ('6', '7'),
       ('8', '8'),
       ('8', '1'),
       ('4', '2'),
       ('10', '5');

CREATE INDEX dateIndex
    ON song (release_date);

CREATE INDEX nameIndex
    ON record_label (name);