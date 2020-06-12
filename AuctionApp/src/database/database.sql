/**
 * AuctionDatabase
 * This describes the database of the auction server
 *
 * @author Cyril Acquah Kofi
 * @author 10686868
 * @version 1.0.0
 * @since 01-05-2020
 *
 * ################################################
 * ############### !!! WARNING !!! ################
 * ################################################
 *
 * This file alters the structure of your server.
 * The author will not be held responsible. Use
 * wisely :)
 */

DROP DATABASE IF EXISTS Auction;

CREATE DATABASE Auction;

USE Auction;

CREATE TABLE Users (
    ID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID)
);


CREATE TABLE Items (
    ID INT NOT NULL AUTO_INCREMENT,
    url VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    highest FLOAT NOT NULL,
    PRIMARY KEY(ID)
);


CREATE TABLE Bids (
    ID INT NOT NULL AUTO_INCREMENT,
    user INT NOT NULL,
    item INT NOT NULL,
    price FLOAT NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY(user) REFERENCES Users(ID),
    FOREIGN KEY(item) REFERENCES Items(ID)
);


ALTER TABLE Users AUTO_INCREMENT=10234;
ALTER TABLE Items AUTO_INCREMENT=10111;
ALTER TABLE Bids AUTO_INCREMENT=103234;