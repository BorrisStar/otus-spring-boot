INSERT INTO author(firstname, lastname)
VALUES ('Andrey', 'Lukyanenko');
INSERT INTO author(firstname, lastname)
VALUES ('Aleksey', 'Isaev');
INSERT INTO author(firstname, lastname)
VALUES ('University', 'of Oxford');


INSERT INTO genre(genre)
VALUES ('Military');
INSERT INTO genre(genre)
VALUES ('Fantasy');
INSERT INTO genre(genre)
VALUES ('Dictionary');


INSERT INTO book(title, author, genre, year)
VALUES ('the night Watch', 1, 2, '1995');
INSERT INTO book(title, author, genre, year)
VALUES ('Battle in the den of the Beast.', 2, 1, '2007');
INSERT INTO book(title, author, genre, year)
VALUES ('The miracle near Moscow', 2, 1, '2017');
INSERT INTO book(title, author, genre, year)
VALUES ('Oxford Dictionary', 3, 3, '2010');

INSERT INTO comment(text, book_id)
VALUES ('Unbelievable!', 1);
INSERT INTO comment(text, book_id)
VALUES ('Fantastic!', 1);
INSERT INTO comment(text, book_id)
VALUES ('So-so...', 2);
INSERT INTO comment(text, book_id)
VALUES ('So boring...', 3);

INSERT INTO users (id, username, password)
VALUES (1, 'admin', 'password');
INSERT INTO users (id, username, password)
VALUES (2, 'user1', 'user1');
INSERT INTO users (id, username, password)
VALUES (3, 'user2', 'user2');

INSERT INTO user_role (user_id, roles)
VALUES (1, 'ADMIN');
INSERT INTO user_role (user_id, roles)
VALUES (2, 'USER');
INSERT INTO user_role (user_id, roles)
VALUES (3, 'BANNED')