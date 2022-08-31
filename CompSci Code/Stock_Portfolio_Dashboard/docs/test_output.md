# Test Outputs

## Unit Tests

### Unit Test 1

#### Output

~~~
2021-12-11T15:16:22.864963800-05:00
The system has detected the market is closed.
Is this true? If so, the test passes. Otherwise, the test fails.
~~~

#### Results

Since the test was run on Saturday, the market is closed.
Test __Passes__

### Unit Test 2

#### Output

~~~
A Virtual LED Matrix should have opened and displayed 4 rows of scrolling text.
The scrolling test should display the values coded into the class.
If this is the case, the test has passed and you can close the window. Otherwise, the test has failed.
~~~

#### Results

At the run of the test, a Virtual LED Matrix window popped up and displayed scrolling text.
Test __Passes__

### Unit Test 3

#### Output

~~~
Testing chars qwerty

 000    0   0   00000   0000    00000   0   0   
0   0   0   0   0       0   0     0     0   0   
0   0   0   0   0       0   0     0     0   0   
0   0   0 0 0   0000    0000      0      0 0    
0 0 0   0 0 0   0       0  0      0       0     
0  00   00000   0       0   0     0       0     
 0000    0 0    00000   0   0     0       0     

Testing chars uiopas

0   0   00000    000    0000      0      000    
0   0     0     0   0   0   0    0 0    0   0   
0   0     0     0   0   0   0   0   0   0       
0   0     0     0   0   0000    00000    000    
0   0     0     0   0   0       0   0       0   
0   0     0     0   0   0       0   0   0   0   
 000    00000    000    0       0   0    000    

Testing chars dfghjk

0000    00000    000    0   0   00000   0   0   
0   0   0       0   0   0   0      0    0   0   
0   0   0       0       0   0      0    0  0    
0   0   0000    0  00   00000      0    000     
0   0   0       0   0   0   0      0    0  0    
0   0   0       0   0   0   0   0  0    0   0   
0000    0        0000   0   0    00     0   0   

Testing chars lzxcvb

0       00000   0   0    000    0   0   0000    
0           0   0   0   0   0   0   0   0   0   
0          0     0 0    0       0   0   0   0   
0         0       0     0       0   0   0000    
0        0       0 0    0       0   0   0   0   
0       0       0   0   0   0    0 0    0   0   
00000   00000   0   0    000      0     0000    

Testing chars nmQWER

0   0   0   0    000    0   0   00000   0000    
00  0   00 00   0   0   0   0   0       0   0   
0 0 0   0 0 0   0   0   0   0   0       0   0   
0 0 0   0 0 0   0   0   0 0 0   0000    0000    
0 0 0   0   0   0 0 0   0 0 0   0       0  0    
0  00   0   0   0  00   00000   0       0   0   
0   0   0   0    0000    0 0    00000   0   0   

Testing chars TYUIOP

00000   0   0   0   0   00000    000    0000    
  0     0   0   0   0     0     0   0   0   0   
  0     0   0   0   0     0     0   0   0   0   
  0      0 0    0   0     0     0   0   0000    
  0       0     0   0     0     0   0   0       
  0       0     0   0     0     0   0   0       
  0       0      000    00000    000    0       

Testing chars ASDFGH

  0      000    0000    00000    000    0   0   
 0 0    0   0   0   0   0       0   0   0   0   
0   0   0       0   0   0       0       0   0   
00000    000    0   0   0000    0  00   00000   
0   0       0   0   0   0       0   0   0   0   
0   0   0   0   0   0   0       0   0   0   0   
0   0    000    0000    0        0000   0   0   

Testing chars JKLZXC

00000   0   0   0       00000   0   0    000    
   0    0   0   0           0   0   0   0   0   
   0    0  0    0          0     0 0    0       
   0    000     0         0       0     0       
   0    0  0    0        0       0 0    0       
0  0    0   0   0       0       0   0   0   0   
 00     0   0   00000   00000   0   0    000    

Testing chars VBNM12

0   0   0000    0   0   0   0     0      000    
0   0   0   0   00  0   00 00    00     0   0   
0   0   0   0   0 0 0   0 0 0   0 0        0    
0   0   0000    0 0 0   0 0 0     0       0     
0   0   0   0   0 0 0   0   0     0      0      
 0 0    0   0   0  00   0   0     0     0       
  0     0000    0   0   0   0   00000   00000   

Testing chars 345678

 000       0    00000    000    00000    000    
0   0     00    0       0   0       0   0   0   
    0    0 0    0       0          0    0   0   
  00    0  0    0000    0000      0      000    
    0   00000       0   0   0    0      0   0   
0   0      0        0   0   0   0       0   0   
 000       0    0000     000    0        000    

Testing chars 90-+.|

 000     000                              0     
0   0   0   0                             0     
0   0   00  0             0               0     
 0000   0 0 0   00000    000              0     
    0   0  00             0               0     
   0    0   0                    00       0     
  0      000                     00       0     

The output should contain all the chars. Check the output to confirm all the characters are outputting properly.
All the chars should be output uppercase. If this is not the case the test fails.
If all the characters are output properly, the test passes.
~~~

#### Results

All the 5x7 characters outputted properly map to the character they are supposed to be.
Test __Passes__

### Unit Test 4

#### Results

Upon launching a new browser session and navigating to localhost/home.php, the browser redirected me to localhost and the signin page.
Test __Passes__

## Integration Tests

### Integration Test 1

#### Output

~~~
Database Settings:
Mode: single
Single Stock: TSLA
Two Stocks - 1: GME
Two Stocks - 2: AMC
Many Stocks - Main: F
Many Stocks - Secondary 1: BTC-USD
Many Stocks - Secondary 2: TSLA
Many Stocks - Secondary 3: MSFT
Many Stocks - Secondary 4: AMC

Navigate to PhpMyAdmin or use the SQL terminal to determine the current values in the database.
If the values match what was output, the test passes. If not the test fails.
~~~

#### Results

The outputted results matched the values in my local database.
Test __Passes__

### Integration Test 2

#### Output

~~~
Single Ticker: TSLA:
1017.03
Double Ticker 1: GME:
159.01
Many Ticker 1: F:
21.45

The outputted prices should correspond to tickers in the database.
The prices outputted should correspond to the ticker and be current.
Prices can be verified at https://finance.yahoo.com
If the tickers are the correct tickers and the prices are right, the test passes.
~~~

#### Results

The outputted tickers correspond to the database. The outputted prices correspond to Yahoo Finance.
Test __Passes__

### Integration Test 3

#### Output

~~~
Navigate to the web user interface at http://localhost
Press enter once you have the Web UI open.

Layout: single
Single Ticker: TSLA
Two Tickers 1: GME
Two Tickers 2: AMC
Many Tickers Main: F
Many Tickers Secondary 1: BTC-USD
Many Tickers Secondary 2: TSLA
Many Tickers Secondary 3: MSFT
Many Tickers Secondary 4: AMC

The outputted tickers should match the tickers in the Web UI.
Now change some of the tickers and/or the layout and press save.
Press the enter key in the terminal when you have done this.


Layout: many
Single Ticker: TSLA
Two Tickers 1: GME
Two Tickers 2: AMC
Many Tickers Main: F
Many Tickers Secondary 1: BTC-USD
Many Tickers Secondary 2: TSLA
Many Tickers Secondary 3: MSFT
Many Tickers Secondary 4: AMC

The newly outputted tickers should match what you changed in the Web UI.
If either of the sets of tickers failed to match the Web UI, the test fails.
~~~

#### Results

I changed the layout from single to many. The output reflects this.
Test __Passes__

## System Tests

### System Test 1

#### Output

~~~
Successful connection to https://google.com
Successful connection to https://1.1.1.1
Internet Connection Test Results: 2 Successes, 0 Failures
~~~

#### Results

The JVM successfully connected to google.com and 1.1.1.1, therefore, the network connection is working.
Test __Passes__

### System Test 2

#### Output

~~~
Outputting Java Version: Should be OpenJDK for licensing issues. If not, test fails.
OpenJDK 64-Bit Server VM
Successful connection to http://localhost. This phase of test passes.
Successful connection to database. This phase of test passes.
~~~

#### Results

Java Version is OpenJDK, and JVM successfully connected to both the web server and the database server.
Test __Passes__

### System Test 3

#### Output

~~~
Stock prices will output for 20 seconds. This time can be adjusted in the program is necessary
Stock prices in the system should update within 6 seconds of the price changing.
Note that the price output updating can't be tested when the market is closed.
Find the stock price of TSLA at https://finance.yahoo.com/quote/TSLA and compare to the outputted price.
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
TSLA Price: 1017.03
If the price updated within 6 or so seconds, the test passes.
~~~

#### Results

The price is outputted but doesn't update because the market is closed.
Test Result __Inconclusive__