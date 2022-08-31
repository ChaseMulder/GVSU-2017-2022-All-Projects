# Expanded Use Case Description

[Description of this use case diagram.](use_case_1.png)

## Use Case: Login

### Actors:

* User
* Database Management System

### Description:

The user navigates to the stock configuration web page and attempts to login. The credentials are validated against the database. If the DBMS returns credentials which match the users, the user is logged in.

### Cross References:

* See functional requirement 1.1.
* See non-functional requirement 1.4.

### Prerequisite Use Cases:

None.


## Use Case: Change Settings

### Actors:

* User
* Database Management System

### Description:

The user changes various settings to customize the display output of the stock ticker. Upon clicking __save__ the new settings will be pushed to the database.

### Cross References

* See functional requirements 1.2 and 1.3.
* See non-functional requirements 1.1, 1.2, and 1.3.

### Prerequisite Use Cases:

Login


## Use Case: Logout

### Actors:

* User

### Description:

The user can logout of their web console session to prevent other people from changing stock settings.

### Cross References

* See non-functional requirement 1.4.

### Prerequisite User Cases:

Login