DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS genre;

CREATE TABLE author
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL
);

CREATE TABLE genre
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    genre VARCHAR(50) NOT NULL
);

CREATE TABLE book
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    title   VARCHAR(255),
    author  BIGINT,
    genre   BIGINT,
    year VARCHAR(4),
    FOREIGN KEY (author) REFERENCES author (id) ON DELETE CASCADE,
    FOREIGN KEY (genre) REFERENCES genre (id) ON DELETE CASCADE
);

CREATE TABLE comment
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    text TEXT,
    book_id bigint not null references book(id) on delete cascade
);