<?php

session_start();
session_destroy();

header('Location: index.php?message=You%20have%20been%20signed%20out');
?>