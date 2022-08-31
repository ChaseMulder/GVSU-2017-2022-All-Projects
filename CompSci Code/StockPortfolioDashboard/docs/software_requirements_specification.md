# Overview

This document outlines the software requirements for the project. Listed below are both functional and non-functional requirements for each part of our project. Our project is a stock ticker implemented on a 32x64 LED board and virtually.

# Software Requirements

Contents below in this markdown file include three categories of functional and non-functional requirements; further, each category holds a minimum of 5 requirements

## Functional Requirements

### WebUI Feature
| 01 | Requirement | Test Cases |
| :---: | :---: | :---: |
| FR1 | The web console shall allow the user to choose stock tickers. | IT3 | 
| FR2 | The web console shall save stock settings so that the watcher app can read them. | IT3 |
| FR3 | The web console shall require the user to login. | UT4 |
| FR4 | The web console shall give feedback to the user when settings are saved. | |
| FR5 | The web console shall display a preview of each possible layout. | |

### Stock Ticker Display Feature

| 02 | Requirement | Test Cases |
| :---: | :---: | :---: |
| FR6 | The stock ticker shall display the current price of a stock. | UT3 | 
| FR7 | The stock ticker shall be able to display the price of multiple stocks simultaneously. | IT2 |
| FR8 | The stock ticker shall be able to display other data such as the volume, change since previous close, etc. |  | 
| FR9 | The stock ticker shall display data on an LED panel (or virtual panel). |  | 
| FR10 | The stock ticker shall allow for different display layouts. | | 

### Other Functional Requirements

| 03 | Requirement | Test Cases |
| :---: | :---: | :---: |
| FR11 | The stock ticker shall signal whether or not the market is open. | UT1 |
| FR12 | The stock ticker shall have an internet connection | ST1 |
| FR13 | The stock ticker controller shall run on Java and the WebUI on Apache | |
| FR14 | The stock ticker shall have a DBMS | IT1 |
| FR15 | The stock ticker shall offer a scroll bar that displays data on many stocks in the background. | UT2 |

## Non-Functional Requirements

### WebUI Feature

| 04 | Requirement | Test Cases |
| :---: | :---: | :---: |
| FR16 | The ticker shall have a web console. | ST2 |
| FR17 | The web console shall load in a reasonable amount of time (1-2 seconds max). |  |
| FR18 | The web console shall have an intuitive UI. |  |
| FR19 | The web console shall be visually appealing. |  |
| FR20 | The web console password shall not be stored in plaintext. |  |

### Stock Ticker Display Feature

| 05 | Requirement | Test Cases |
| :---: | :---: | :---: |
| FR21 | The stock ticker shall update within 6 seconds. | ST3 | 
| FR22 | The stock ticker shall be color-coded based on the change in price of the stock. | |
| FR23 | The LED panel shall be large enough to display multiple stocks at the same time. | |
| FR24 | The stock ticker controller shall run on startup | |
| FR25 | The stock ticker controller application for running the code should be OpenJDK. | ST2 |

### Other Non-Functional Requirements

| 06 | Requirement | Test Cases |
| :---: | :---: | :---: |
| FR26 | The software shall run on Raspberry Pi OS (or other OS for development). | |
| FR27 | The stock ticker shall run on a raspberry pi (or other device for development). | |
| FR28 | The hardware shall have soldered wires. | |
| FR29 | The stock ticker shall be portable. | |
| FR30 | The stock ticker shall be plugged into a regular wall outlet. | |

# Tests

## Unit Tests

| Test | How to run | 
| :---: | :--- |
| UT1 | Unit test 1 tests the market open algorithm. Run [com.stonks.test.MarketOpenTest.java](../src/java/src/com/stonks/test/MarketOpenTest.java) and view the output to determine the result of the test |
| UT2 | Unit test 2 tests the scrolling text function. Run [com.stonks.test.ScrollingTextTest.java](../src/java/src/com/stonks/test/ScrollingTextTest.java) and view the outputs to determine the result of the test |
| UT3 | Unit test 3 tests the 5x7 font used on the matrix. Run [com.stonks.test.FontTest.java](../src/java/src/com/stonks/test/FontTest.java) and view the output to determine the results of the test |
| UT4 | Unit test 4 tests to make sure the web user interface is secured by a password. Open a new private browsing window to ensure there are no active sessions. Navigate to [localhost/home.php](http://localhost/home.php)   (or the IP address if on a different device). If the website redirects you to the signin page, the test passes |

## Integration Tests

| Test | How to run | 
| :---: | :--- |
| IT1 | Integration test 1 tests the database integration. Run [com.stonks.test.SQLTest.java](../src/java/src/com/stonks/test/SQLTest.java) and view the output to determine the results of the test |
| IT2 | Integration test 2 tests the adds integration with Yahoo Finance to the database. Run [com.stonks.test.DBYahooFinanceIntegrationTest.java](../src/java/src/com/stonks/test/SQLTest.java) and view the output to determine the results of the test |
| IT3 | Integration test 3 tests the integration with the WebUI. Run [com.stonks.test.WebUIIntegrationTest.java](../src/java/src/com/stonks/test/WebUIIntegrationTest.java) and view the output to determine the results of the test. |

## System Tests
| Test | How to run | 
| :---: | :--- |
| ST1 | System test 1 tests if the network connection is working. Run [com.stonks.test.NetworkConnectionTest.java](../src/java/src/com/stonks/test/NetworkConnectionTest.java) and view the outputs to determine the result of the test |
| ST2 | System test 2 tests the JDK/JRE, the web server, and the DBMS service. Run [com.stonks.test.SystemServicesTest.java](../src/java/src/com/stonks/test/SystemServicesTest.java) and view the outputs to determine the results of the test |
| ST3 | System test 3 tests the update speed of the YahooFinanceMonitor thread. Run [com.stonks.test.StockUpdateTest.java](../src/java/src/com/stonks/test/StockUpdateTest.java) and view the outputs to determine the results of the test |

## Test Outputs

Successful outputs from all the tests as we have run it on the project can be found [here](test_output.md)

# Artifacts

## Use Cases

The use cases give a high level overview of the project and how the user and various systems interact

### Use Case Diagrams

* [Use case 1](../artifacts/use_case_diagrams/use_case_1.png)
* [Use case 2](../artifacts/use_case_diagrams/use_case_2.png)
* [Use case 3](../artifacts/use_case_diagrams/use_case_3.png)

### Extended Use Case Descriptions

* [Use case 1 Description](../artifacts/use_case_diagrams/use_case_description_1.md)