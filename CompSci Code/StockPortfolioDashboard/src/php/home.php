<?php
    session_start();

    if (!isset($_SESSION['loggedin'])) {
        header('Location: index.php');
        exit;
    }

    $DATABASE_HOST = 'localhost';
    $DATABASE_USER = 'root';
    $DATABASE_PASS = '';
    $DATABASE_NAME = 'stonks';

    $connection = mysqli_connect($DATABASE_HOST, $DATABASE_USER, $DATABASE_PASS, $DATABASE_NAME);

    $result = $connection->query('SELECT * FROM settings WHERE id=1');
    $row = mysqli_fetch_assoc($result);

    //$statement = $connection->prepare('SELECT * FROM settings WHERE id=1');
    //$statement->execute();
    //$statement->store_result();

?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Stonk Settings</title>
        <style>
            header, body, main, form, nav, input, h1, h3, h4 {
                margin: 0;
                padding: 0;
                border: 0;
            }

            header {
                background-color: #bd3333;
            }

            body {
                background-color: #aaaaaa;
            }

            #title {
                font-size: 3em;
                font-family: 'Franklin Gothic Medium', Arial, sans-serif;
                font-weight: bold;
                text-align: center;
                color: #ffffff;
                padding: 2%;
            }

            nav {
                background-color: #000000;
                padding: 5px;
            }

            #logout {
                padding: 5px;
                background-color: #000000;
                font-weight: bold;
                text-decoration: none;
                color: #ffffff;
            }

            #logout:hover {
                background-color: #ffffff;
                color: #000000;
            }

            .center-contents {
                width: 100%;
            }

            input {
                padding: 1%;
            }
            
            input[type=text] {
                width: 75%;
                max-width: 500px;
                margin: 1% auto;
                display: block;
            }

            input[type=submit] {
                width: 75%;
                max-width: 400px;
                margin: 1% auto;
                display: block;
                background-color: #bd3333;
                color: #ffffff;
                font-weight: bold;
            }

            input[type=submit]:hover {
                background-color: #ffffff;
                color: #bd3333;
            }

            h3 {
                width: 75%;
                max-width: 500px;
                margin: 1% auto;
                display: block;
            }

            h4 {
                width: 75%;
                max-width: 500px;
                margin: 1% auto;
                display: block;
                font-style: italic;
            }

            #radioContainer {
                width: 75%;
                max-width: 500px;
                margin: 1% auto;
            }

            .radioLabelSurround {
                width: 27%;
                display: inline-block;
                border: 2px solid #000000;
                margin: 1%;
                padding: 1%;
            }
        </style>
    </head>
    <body>
        <header>
            <h1 id="title">Stonk Settings</h1>
        </header>
        <nav>
            <a id="logout" href="logout.php">Logout</a>
        </nav>
        <main>
            <form action="savesettings.php" method="post" onsubmit="return enableOnSubmit();">
                <div class="center-contents">
                    <div id="radioContainer">
                        <div class="radioLabelSurround">
                            <input type="radio" id="oneStock" name="layoutChooser" value="single" onchange="radioChange()">
                            <label for="oneStock">One Stock</label>
                        </div>
                        <div class="radioLabelSurround">
                            <input type="radio" id="twoStock" name="layoutChooser" value="double" onchange="radioChange()">
                            <label for="twoStock">Two Stocks</label>
                        </div>
                        <div class="radioLabelSurround">
                            <input type="radio" id="manyStock" name="layoutChooser" value="many" onchange="radioChange()">
                            <label for="manyStock">Multiple Stocks</label>
                        </div>
                    </div>
                </div>
                <div class="center-contents"><h3>Single Stock</h3></div>
                <div class="center-contents"><input type="text" name="singleStock" placeholder="Stock Ticker" id="singleStock" value=<?php echo "\"" . $row["singleTicker"] . "\"" ?> required></div>
                <div class="center-contents"><h3>Two Stocks</h3></div>
                <div class="center-contents"><input type="text" name="doubleStock1" placeholder="Stock Ticker #1" id="doubleStock1" value=<?php echo "\"" . $row["doubleTicker1"] . "\"" ?> required></div>
                <div class="center-contents"><input type="text" name="doubleStock2" placeholder="Stock Ticker #2" id="doubleStock2" value=<?php echo "\"" . $row["doubleTicker2"] . "\"" ?> required></div>
                <div class="center-contents"><h3>Multiple Stocks</h3></div>
                <div class="center-contents"><h4>Main Stock</h4></div>
                <div class="center-contents"><input type="text" name="manyStock1" placeholder="Main Stock Ticker" id="manyStock1" value=<?php echo "\"" . $row["manyTicker1"] . "\"" ?> required></div>
                <div class="center-contents"><h4>Secondary Stocks</h4></div>
                <div class="center-contents"><input type="text" name="manyStock2" placeholder="Secondary Ticker #1" id="manyStock2" value=<?php echo "\"" . $row["manyTicker2"] . "\"" ?> required></div>
                <div class="center-contents"><input type="text" name="manyStock3" placeholder="Secondary Ticker #2" id="manyStock3" value=<?php echo "\"" . $row["manyTicker3"] . "\"" ?> required></div>
                <div class="center-contents"><input type="text" name="manyStock4" placeholder="Secondary Ticker #3" id="manyStock4" value=<?php echo "\"" . $row["manyTicker4"] . "\"" ?> required></div>
                <div class="center-contents"><input type="text" name="manyStock5" placeholder="Secondary Ticker #4" id="manyStock5" value=<?php echo "\"" . $row["manyTicker5"] . "\"" ?> required></div>
                <div class="center-contents"><input type="submit" value="Save"></div>
                
            </form>
            <script>
                    var oneStockRadio = document.getElementById("oneStock");
                    var twoStockRadio = document.getElementById("twoStock");
                    var manyStockRadio = document.getElementById("manyStock");
                    var singleStock = document.getElementById("singleStock");
                    var doubleStock1 = document.getElementById("doubleStock1");
                    var doubleStock2 = document.getElementById("doubleStock2");
                    var manyStock1 = document.getElementById("manyStock1");
                    var manyStock2 = document.getElementById("manyStock2");
                    var manyStock3 = document.getElementById("manyStock3");
                    var manyStock4 = document.getElementById("manyStock4");
                    var manyStock5 = document.getElementById("manyStock5");

                    <?php
                        if ($row["layoutStyle"] == "single") {
                            echo "oneStockRadio.checked = true;";
                        } else if ($row["layoutStyle"] == "double") {
                            echo "twoStockRadio.checked = true;";
                        } else if ($row["layoutStyle"] == "many") {
                            echo "manyStockRadio.checked = true;";
                        }
                        echo "radioChange();";
                    ?>

                    function radioChange() {
                        singleStock.disabled = !oneStockRadio.checked;
                        doubleStock1.disabled = !twoStockRadio.checked;
                        doubleStock2.disabled = !twoStockRadio.checked;
                        manyStock1.disabled = !manyStockRadio.checked;
                        manyStock2.disabled = !manyStockRadio.checked;
                        manyStock3.disabled = !manyStockRadio.checked;
                        manyStock4.disabled = !manyStockRadio.checked;
                        manyStock5.disabled = !manyStockRadio.checked;
                    };

                    function enableOnSubmit() {
                        singleStock.disabled = false;
                        doubleStock1.disabled = false;
                        doubleStock2.disabled = false;
                        manyStock1.disabled = false;
                        manyStock2.disabled = false;
                        manyStock3.disabled = false;
                        manyStock4.disabled = false;
                        manyStock5.disabled = false;
                        return true;
                    };
                </script>
        </main>
    </body>
</html>

<?php
    $connection->close();
?>