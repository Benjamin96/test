/* 
 * CA For Webservices DB
 */
/*
 * Ben, Chris and Aleks
 * Author: Chris and Aleks
 */

DROP TABLE IF EXISTS bookLoaned;
DROP TABLE IF EXISTS bookStock;
DROP TABLE IF EXISTS users;
 
CREATE TABLE users(
    userID int(4) NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL,
    password varchar(20) NOT NULL,
    firstName varchar(20) NOT NULL,
    lastName varchar(20) NOT NULL,
    address varchar(60) NOT NULL,
    email varchar(30) NOT NULL,
    booksLoaned int(2) NOT NULL,
    admin smallint(1) NOT NULL,
    PRIMARY KEY (userID)
);

insert into users (userID, username, password, firstName, lastName, address, email, booksLoaned, admin)
values (0, 'user1', '123', 'Chris', 'McKenzie', '26 lane', 'test@gmail.com', 2,0);
insert into users (userID, username, password, firstName, lastName, address, email, booksLoaned, admin)
values (1, 'user2', '123', 'Ben', 'Rose', '27 lane', 'test2@gmail.com', 2,0);
insert into users (userID, username, password, firstName, lastName, address, email, booksLoaned, admin)
values (2, 'admin1', '123', 'Chris', 'Rose', '28 lane', 'admin1@gmail.com', 0,1);
insert into users (userID, username, password, firstName, lastName, address, email, booksLoaned, admin)
values (3, 'admin2', '123', 'Ben', 'McKenzie', '29 lane', 'admin2@gmail.com', 1,1);


CREATE TABLE bookStock
(
    bookID int(4) NOT NULL AUTO_INCREMENT,
    bookName varchar(20),
    author varchar(30),
    publisher varchar(20),
    copies int(2),
    PRIMARY KEY (bookID)
);

insert into bookStock (bookID, bookName, Author, publisher, copies) 
values (0, 'Harry Potter and The Chamber of Secrets', 'J.K Rowling', 'Book', 10);
insert into bookStock (bookID, bookName, Author, publisher, copies) 
values (1, 'Skullduggery Pleasant', 'Derak Landy', 'Two', 10);
insert into bookStock (bookID, bookName, Author, publisher, copies) 
values (2, 'The Hobbit', 'J. R. R. Tolkien', 'HobbitShop', 10);
insert into bookStock (bookID, bookName, Author, publisher, copies) 
values (3, 'A Game of Thrones', 'George R.R. Martin', 'GoT', 10);
/*Add max value for copies(MAX(10)) then a loaned coloum to check for lloaned copies

*/

CREATE TABLE bookLoaned
(
    loanID int(4) NOT NULL AUTO_INCREMENT,
    bookID int(4) NOT NULL,
    userID int(4) NOT NULL,
	PRIMARY KEY(loanID),
    FOREIGN KEY (bookID) REFERENCES bookStock(bookID),
    FOREIGN KEY (userID) REFERENCES users(userID));

insert into bookLoaned (loanID, bookID, userID) 
values (0, 2, 1);
insert into bookLoaned (loanID, bookID, userID) 
values (1, 3, 1);

