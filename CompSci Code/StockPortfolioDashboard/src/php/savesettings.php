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

$statement = $connection->prepare("UPDATE settings SET layoutStyle = ?, singleTicker = ?, doubleTicker1 = ?, doubleTicker2 = ?, manyTicker1 = ?, manyTicker2 = ?, manyTicker3 = ?, manyTicker4 = ?, manyTicker5 = ? WHERE id = 1");
$statement->bind_param("sssssssss", $_POST['layoutChooser'], $_POST['singleStock'], $_POST['doubleStock1'], $_POST['doubleStock2'], $_POST['manyStock1'], $_POST['manyStock2'], $_POST['manyStock3'], $_POST['manyStock4'], $_POST['manyStock5']);
$statement->execute();
$statement->close();

$connection->close();

header('Location: home.php');
exit;

?>