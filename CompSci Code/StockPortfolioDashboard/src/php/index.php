<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Stonk Login</title>
        <style>
            header, body, main, form, h1, input {
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

            .center-contents {
                width: 100%;
            }

            input {
                width: 75%;
                max-width: 500px;
                padding: 2%;
                border: 2px solid #000000;
                margin: 3% auto;
                display: block;
            }

            input[type=submit] {
                background-color: #000000;
                color: #ffffff
            }

            input[type=submit]:hover {
                background-color: #666666;
            }

            #message {
                text-align: center;
                font-weight: bold;
                font-style: italic;
                color: #bd3333;
            }
            
        </style>
    </head>
    <body>
        <header>
            <h1 id="title">Stonk Dashboard</h1>
        </header>
        <main>
            <p id="message">
            <?php
                if (isset($_GET['message'])) {
                    echo $_GET['message'];
                }
            ?>
            </p>
            <form action="authenticate.php" method="post">
                <div class="center-contents"><input type="text" name="username" placeholder="Username" id="username" required></div>
                <div class="center-contents"><input type="password" name="password" placeholder="Password" id="password" required></div>
                <div class="center-contents"><input type="submit" value="Login"></div>
            </form>
        </main>
    </body>
</html>