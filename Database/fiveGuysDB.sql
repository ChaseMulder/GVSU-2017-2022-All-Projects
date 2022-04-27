/*
Author: JRA
CREDITS: The SailorsDB is a significant extension of the one that appears
in Ramakrishnan and Gehrke's book. I have added several extensions in order
to demonstrate important SQL features.
*/
-- CREATING THE TABLES
-- --------------------------------------------------------------------
CREATE TABLE  Customers
(
creditCardNum       INTEGER,
Fname      CHAR(15)    NOT NULL, 
Lname      CHAR(15)    NOT NULL,
customerType  INTEGER     NOT NULL,
--
-- sIC1: Customer's creditCardNums are unique
CONSTRAINT sIC1 PRIMARY KEY (creditCardNum),
-- sIC2: Every secretShopper must be a Customer too.
CONSTRAINT sIC2 FOREIGN KEY (customerType) REFERENCES Customers(creditCardNum)
           ON DELETE SET NULL
           DEFERRABLE INITIALLY DEFERRED,
);
-- -------------------------------------------------------------------
CREATE TABLE  foodOrder
(
orderNum        INTEGER,
timePlaced      INTEGER,
cost            INTEGER,
customerCreditCardNum  INTEGER,
managerID       INTEGER,
items           CHAR(15)  NOT NULL,
-- bIC1: orderNums are unique.
CONSTRAINT bIC1 PRIMARY KEY (orderNum),
);
-- --------------------------------------------------------------
CREATE TABLE  crewMembers
(
crewID     INTEGER,
Fname      CHAR(15)    NOT NULL, 
Lname      CHAR(15)    NOT NULL,
wage       INTEGER,
--
-- rIC1: A boat can't be reserved more than once for the same date.
CONSTRAINT rIC1 PRIMARY KEY (crewID),
);
-- --------------------------------------------------------------
CREATE TABLE  Managers
(
managerID     INTEGER,
Fname      CHAR(15)    NOT NULL, 
Lname      CHAR(15)    NOT NULL,
--
-- rIC1: A boat can't be reserved more than once for the same date.
CONSTRAINT rIC1 PRIMARY KEY (managerID),
);
-- --------------------------------------------------------------
CREATE TABLE  crewReport
(
crewID     INTEGER,
managerID  INTEGER,
rating     INTEGER,
reportDate             CHAR(15)    NOT NULL,
reportDescription      CHAR(15)    NOT NULL,
--
-- rIC1: A boat can't be reserved more than once for the same date.
CONSTRAINT rIC1 PRIMARY KEY (crewID,managerID,reportDate),
);
-- --------------------------------------------------------------
CREATE TABLE  secretShopperReport
(
secretShopperID     INTEGER,
secretReportDate    CHAR(15)    NOT NULL, 
waitTime            CHAR(15)    NOT NULL,
serciveRating       INTEGER,
orderNum            INTEGER,
--
-- rIC1: A boat can't be reserved more than once for the same date.
CONSTRAINT rIC1 PRIMARY KEY (secretShopperID,secretReportDate,orderNum),
);
-- --------------------------------------------------------------
CREATE TABLE  Items
(
orderNum     INTEGER,
items      CHAR(15)    NOT NULL, 
--
-- rIC1: A boat can't be reserved more than once for the same date.
CONSTRAINT rIC1 PRIMARY KEY (orderNum,items),
);
-- --------------------------------------------------------------
CREATE TABLE  Makes
(
crewID     INTEGER,
orderNum     INTEGER,
makeTime      CHAR(15)    NOT NULL, 
--
-- rIC1: A boat can't be reserved more than once for the same date.
CONSTRAINT rIC1 PRIMARY KEY (crewID,orderNum),
);
-- --------------------------------------------------------------
-- A view containing sailors who don't have reservations
CREATE VIEW  LazySailors AS
    SELECT  sid, sname, rating
    FROM    Sailors
    WHERE   sid NOT IN (SELECT sid FROM Reservations);
-- ---------------------------------------------------------------
-- POPULATING THE DATABASE INSTANCE
-- ---------------------------------------------------------------
INSERT INTO Reservations VALUES (101, TO_DATE('10/10/19', 'MM/DD/YY'),
                                  22, TO_DATE('10/07/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (102, TO_DATE('10/14/19', 'MM/DD/YY'),
                                  22, TO_DATE('10/10/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (103, TO_DATE('11/17/19', 'MM/DD/YY'),
                                  22, TO_DATE('10/10/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (105, TO_DATE('10/14/19', 'MM/DD/YY'),
                                  58, TO_DATE('10/13/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (102, TO_DATE('10/20/19', 'MM/DD/YY'),
                                  31, TO_DATE('10/10/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (103, TO_DATE('11/22/19', 'MM/DD/YY'),
                                  31, TO_DATE('10/20/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (104, TO_DATE('11/23/19', 'MM/DD/YY'),
                                  31, TO_DATE('10/20/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (101, TO_DATE('09/05/19', 'MM/DD/YY'),
                                  64, TO_DATE('08/27/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (102, TO_DATE('11/20/19', 'MM/DD/YY'),
                                  64, TO_DATE('11/03/19', 'MM/DD/YY'));
INSERT INTO Reservations VALUES (103, TO_DATE('10/18/19', 'MM/DD/YY'),
                                  74, TO_DATE('08/04/19', 'MM/DD/YY'));
-- --------------------------------------------------------------------
INSERT INTO Sailors VALUES (22, 'Dave', 7, 45.0, 85);
INSERT INTO Sailors VALUES (29, 'Mike', 1, 33.0,NULL);
INSERT INTO Sailors VALUES (31, 'Mary', 8, 55.0, 85);
INSERT INTO Sailors VALUES (32, 'Albert', 8, 25.0, 31);
INSERT INTO Sailors VALUES (58, 'Jim', 10, 35.0, 32);
INSERT INTO Sailors VALUES (64, 'Jane', 7, 35.0, 22);
INSERT INTO Sailors VALUES (71, 'Dave', 10, 16.0, 32);
INSERT INTO Sailors VALUES (74, 'Jane', 9, 40.0, 95);
INSERT INTO Sailors VALUES (85, 'Art', 3, 25.0, 29);
INSERT INTO Sailors VALUES (95, 'Jane', 3, 63.0, NULL);
-- --------------------------------------------------------------------
INSERT INTO Boats VALUES (101,'Interlake', 'blue', 350, 30, 95);
INSERT INTO Boats VALUES (102, 'Interlake', 'red', 275, 23, 22);
INSERT INTO Boats VALUES (103, 'Clipper', 'green', 160, 15, 85);
INSERT INTO Boats VALUES (104, 'Marine', 'red', 195, 24, 22);
INSERT INTO Boats VALUES (105, 'Weekend Rx', 'white', 500, 43, 31);
INSERT INTO Boats VALUES (106, 'C#', 'red', 300, 27, 32);
INSERT INTO Boats VALUES (107, 'Bayside', 'white', 350, 32, 85);
INSERT INTO Boats VALUES (108, 'C++', 'blue', 100, 12, 95);
-- --------------------------------------------------------------------
-- Now, if no violations were detected, COMMIT all the commands in this file
COMMIT;