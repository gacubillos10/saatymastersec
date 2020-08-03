<?php



$base64_string= "

";

$content = base64_decode($base64_string);
$output_file="/home/tupm4k9nwuip/public_html/Saaty/production/core/tempDoc/base64.pdf";
$file = fopen($output_file, "wb");

fwrite($file, $content);
fclose($file);




?>