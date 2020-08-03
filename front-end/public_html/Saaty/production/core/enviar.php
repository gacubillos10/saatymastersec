<?php

var_dump($_POST);


$dir_subida = '/home/tupm4k9nwuip/public_html/Saaty/production/core/tempDoc/';
$fichero_subido = $dir_subida . basename($_FILES['fichero_usuario']['name']);

echo '<pre>';
if (move_uploaded_file($_FILES['fichero_usuario']['tmp_name'], $fichero_subido)) {
    echo "El fichero es válido y se subió con éxito.\n";
} else {
    echo "¡Posible ataque de subida de ficheros!\n";
}

echo 'Más información de depuración:';
print_r($_FILES);

print "</pre>";


$img = file_get_contents($dir_subida.$_FILES['fichero_usuario']['name']); 
  
// Encode the image string data into base64 
$data = base64_encode($img); 
  
// Display the output 
echo $data; 


$fecha = date("Y-m-d-h-m-s");
$fileName = $_FILES['fichero_usuario']['name']."-".$fecha.".txt";

$usr="1";
$acceso= $_POST["Acceso"];
$integridad=$_POST["Integridad"];
$disponibilidad=$_POST["Disponibilidad"];
//$fileName="saaty-2020-07-01.txt";




$md5 = md5_file($dir_subida.$_FILES['fichero_usuario']['name']);

$info = $usr.",Gustavo Cubillos,".$acceso.",".$integridad.",".$disponibilidad.",".$fileName.",".$_FILES['fichero_usuario']['name'].",".$fecha.",Crear,".$md5;

$Algoritmo="";
							if($acceso=="0"){
                                $Algoritmo= "Aes128";
                            }elseif($acceso=="1"){
                                $Algoritmo= "Aes258";
                            }elseif($acceso=="2"){
                                $Algoritmo= "Ntru";
                            }elseif($acceso=="3"){
                                $Algoritmo = "Ntru";
                            }


//API Url
$url = 'https://saaty.azurewebsites.net/api/SubirDocumento';
 
//Initiate cURL.
$ch = curl_init($url);
 
//The JSON data.
$jsonData = array(
    'base64' => $data,
    'usr' => $usr,
    'acceso' => $acceso,
    'integridad' => $integridad,
    'disponibilidad' => $disponibilidad,
    'fileName' => $Algoritmo."-".$fileName
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


$fichero = '../log.txt';
// Abre el fichero para obtener el contenido existente
$actual = file_get_contents($fichero);
// Añade una nueva persona al fichero
$actual .= $info."\n";
// Escribe el contenido al fichero
file_put_contents($fichero, $actual);



	echo '
<script>
  window.location.assign("../")
</script>
';






?>