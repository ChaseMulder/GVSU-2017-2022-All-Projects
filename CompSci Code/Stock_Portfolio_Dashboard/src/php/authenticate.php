<?php

session_start();

$DATABASE_HOST = 'localhost';
$DATABASE_USER = 'root';
$DATABASE_PASS = '';
$DATABASE_NAME = 'stonks';

$connection = mysqli_connect($DATABASE_HOST, $DATABASE_USER, $DATABASE_PASS, $DATABASE_NAME);

if (mysqli_connect_errno()) {
    exit('Failed to connect to database: Error ' . mysqli_connect_error());
}

if (!isset($_POST['username'], $_POST['password'])) {
    exit('Please enter a username and a password!');
}

if ($statement = $connection->prepare('SELECT id, password FROM accounts WHERE username = ?')) {
    $statement->bind_param('s', $_POST['username']);
    $statement->execute();
    $statement->store_result();

    if ($statement->num_rows > 0) {
        $statement->bind_result($id, $password);
        $statement->fetch();

        if (password_verify($_POST['password'], $password)) {
            session_regenerate_id();
            $_SESSION['loggedin'] = TRUE;
            $_SESSION['name'] = $_POST['username'];
            $_SESSION['id'] = $id;
            header('Location: home.php');
        } else {
            //echo 'Incorrect username and/or password!';
            header('Location: index.php?message=Invalid%20username%20or%20password');
        }
    } else {
        //echo 'Incorrect username and/or password!';
        header('Location: index.php?message=Invalid%20username%20or%20password');
    }

    $statement->close();
}

?>