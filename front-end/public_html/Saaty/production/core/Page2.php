<?php
// pagina2.php

session_start();

echo 'Bienvenido a la página #2<br />';

echo $_SESSION['color'];  // verde
echo $_SESSION['animal']; // gato
echo $_SESSION['login']; // gato
echo date('Y m d H:i:s', $_SESSION['instante']);

// Puede ser conveniente usar el SID aquí, como hicimos en pagina1.php
echo '<br /><a href="pagina1.php">página 1</a>';
?>