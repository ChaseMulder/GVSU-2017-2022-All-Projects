Team name: Team Stonks

Team members:

* Alec DeVries
* Chase Mulder
* Simon Hillebrands
* Chase Kinard

# Introduction

Our project idea is to build an IoT device based off of a Raspberry Pi that watches stock tickers. An embedded web server will allow the user to choose different views, different tickers, and any other features we decide to implement. ~~Connected to the Raspberry Pi will be some sort of [LED or LCD Display](https://learn.adafruit.com/adafruit-rgb-matrix-plus-real-time-clock-hat-for-raspberry-pi/driving-matrices)~~ When you plug the pi into a monitor, an terminal app will start that outputs the current price of a selected stock (or stocks) and/or some other relative data.

# Anticipated Technologies

* Raspberry Pi with Raspberry Pi OS
* LAMP stack: Apache Web Server, MariaDB Database (preferred over MySQL for Oracle licensing concerns), PHP Hypertext Preprocessor
* Java OpenJDK version TBD
* Python version TBD

# Method/Approach

## Web Server

Use HTML and PHP to create a secure login portal backed by MariaDB. MariaDB also contains a table row with all of the settings for the application. User can configure the stock display to show various screens (described elsewhere).

## Stock Stat Watcher

Use Java to create a terminal process that runs on startup. Process has three background threads:
* First thread will monitor database for configuration changes and updates the app accordingly
* Second thread will monitor [Yahoo Finance using a 3rd party API](https://financequotes-api.com/) to get stock data for chosen tickers
* Third thread will ~~run Python scripts~~ use [nCurses](https://webfolderio.github.io/curses4j/) to manage screen

## Screen Display Controller

~~Use Python libraries to control LED screen. Most LED displays we have seen have python libraries that are distributed with them so this seems to be the easiest route to follow to manage the screen.~~ Use nCurses with the Java wrapper curses4j to output data to the terminal. nCurses allows to make an ASCII-based gui of a sort within the terminal, allowing us to still run a lightweight GUI-less OS on the pi.

# Estimated Timeline

## Phase 1 - Proposal - Finish by September 17

## Phase 2 - Project Setup - Finish by October 1

* Planning
    * Finalize project details
    * Determine code structure - UML, etc?
* Acquire Hardware
    * Raspberry Pi
    * ~~LED Display~~
    * Hardware and Connectors to connect the two
    * Power Supplies
    * __After__ hardware is obtained, install OS and all necessary packages and assemble any electronic components that need assembly.
* Set up development environment
    * IntelliJ (or similar) for those working on Java backend and OpenJDK
    * Python for those working on Python
    * Code editor of choice and PHP server for web app

## Phase 3 - Pre-release - Finish by October 15

* App functions:
    * Web UI can at bare minimum select a stock ticker to watch
    * Backend can read ticker from DB and find the price
    * Python function correctly writes ticker name and price to screen
* Begin testing

## Phase 4 - Testing and Additional Functions - Finish by November 5

* Build strong suite of tests
* Begin to add more functions 
    * Different display options on the display
    * Watch top stocks on reddit? Check out this api [Here](https://dashboard.nbshare.io/apps/reddit/api/)
    * Display stock volume traded

## Phase 5 - More Testing and Bug Fixes - Finish by End of November

* Testing and more Testing and Debugging and more Testing
* Full release is due soon!

## Phase 6 - Release and Presentation!

# Anticipated Problems

There is a bit of a learning curve as not everyone is 100% familiar with all the technologies we will be working on.
Only one person will have physical access to the pi and led screen so this could also cause issues if more then one person wants to work on the led screen.
