<?php
// pagina1.php

session_start();

echo 'Bienvenido a la página #1 AABBBB';

$_SESSION['color']  = 'verde';
$_SESSION['animal'] = 'gato';
$_SESSION['instante']   = time();

/*
// Funciona si la cookie de sesión fue aceptada
echo '<br /><a href="Page2.php">página 2</a>';

// O quizás pasar el id de sesión, si fuera necesario
echo '<br /><a href="Page2.php?' . SID . '">página 2</a>';
*/



	 $Username = $_POST['Username'];
	 $Password = $_POST['Password']; 
	//echo "string------";
	//echo "<br><br>USR".$Username." - ".$Password."<br><br>";

 
//API Url
$url = 'https://saaty.azurewebsites.net/ValidarLogin/Login';
 
//Initiate cURL.
$ch = curl_init($url);
 
//The JSON data.
$jsonData = array(
    'user' => $Username,
    'pass' => $Password
);
 
//Encode the array into JSON.
$jsonDataEncoded = json_encode($jsonData);
 
//Tell cURL that we want to send a POST request.
//curl_setopt($ch, CURLOPT_POST, 1);


curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);

 
//Attach our encoded JSON string to the POST fields.
curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDataEncoded);
 
//Set the content type to application/json
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json')); 
 
//Execute the request
$result = curl_exec($ch);
//echo "<br>111111<br>";


//curl_close($ch);

print_r($result);

//echo "<br>****111111*****<br>";
$results = json_decode($result, true);
//echo "<br>22222<br>";

//print_r($results);


if($results[auth]=="True"){
//echo "Autenticación Valida ";

$_SESSION["login"]="True";

echo '
<script>
  window.location.assign("https://saatyweb.azurewebsites.net/saaty/production/index.php")

</script>
';

echo "stringSesion; ".$_SESSION["login"];
header('Location: Page2.php');

}else{

	echo "Autenticación NO Valida ";

	echo '
<script>
  window.location.assign("https://saatyweb.azurewebsites.net/saaty/production/login.html")
</script>
';

}











?>