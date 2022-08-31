# Install Guide

This is a guide to installing all the tools necessary to build and run the project.

A more detailed guide on setting up the project can be found [here](/docs/RunningTheProject.docx)

## Development Environment

### Install prerequisites

1. Install [xampp webserver package](https://www.apachefriends.org/download.html). Only Apache, PHP, MySQL (actually MariaDB in xampp), and PhpMyAdmin are required to run the software.
2. Install [Java OpenJDK 16](https://jdk.java.net/archive/).
3. (Optional) Install a Java IDE. The project will be build from IntelliJ, so installing IntelliJ is recommended.

### Get software

1. Clone the git repo. You can use git clone or download a zip.

### Building and running

1. Start Apache and MySQL (MariaDB) from the xampp control panel.
2. Navigate to localhost/phpmyadmin in your browser. Create a new database called "stonks". Then import the [stonks.sql](../src/sql/stonks.sql) file to create the database tables. NOTE that the default user is test and the default password is test. This is inherently insecure because the sql file is publicly available on the internet. 
3. All of the [php files](../src/php/) have to be copied to the xampp/htdocs folder. There are default files in this folder. These can be deleted or moved.
4. Build the java project.
5. Run the Main class in Java. A virtual board will run for testing purposes.

## Full Deployment to Raspberry Pi

### Install prerequisites

1. Install the apt packages for apache2, mariadb-server, mariadb-client, php php-common, php-mysql, php-gd, php-cli, phpmyadmin on the pi. [This tutorial](https://raspi-tutoriais.blogspot.com/2021/07/raspberry-pi-install-apache-mysql-php.html) may help.
2. Install [Java OpenJDK 16](https://jdk.java.net/archive/).

### Get the software

* To use the source code from the git repo, you can clone this repo and build from source in the terminal with the JDK.
* To make modifications yourself, set up a development environment and then copy either the source code and the build or copy a prebuilt binary.

### Building and running

1. If not already running, start Apache and MySQL(MariaDB) from the terminal. The tutorial above may help with this.
2. Use localhost/phpmyadmin to import the [stonks.sql](../src/sql/stonks.sql) file or manually enter the values in it into the MySQL interactive terminal. NOTE that in a production environemt, you may want to make a different username and/or password. Online tools can be found to generate the proper hash of the password.
3. Copy the [php files](../src/php/) to the Apache root folder (typically /var/www/html).
4. If not already compiled, build the Java project.
5. Run the Java binary.

### TODOs

1. We had hardware issues (meaning we probably broke the hardware LOL) so we have not successfully integrated the [LED panel](https://www.adafruit.com/product/2277) and [piHat](https://www.adafruit.com/product/2345) with the pi and software. The plan was to import [this library in binary form](https://github.com/hzeller/rpi-rgb-led-matrix) using Java Native Interface and integrate with the LED board. We then would make a new class implementing the LEDMatrix interface for the actual panel, and swap out the object in the main class.
2. We may still try to do this and will update if we have success. If someone finds this somehow on GitHub and makes it work, make a pull request or leave an issue and we will update the repository.
