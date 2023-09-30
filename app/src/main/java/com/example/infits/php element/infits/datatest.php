<?php

$logfile=fopen("./rawdata.txt","a+");
$postdata=$_POST;
fwrite($logfile,date("Y-m-d H:i:s")."\r\n");
foreach($postdata as $textkey=>$textdata){
fwrite($logfile,$textkey.":".$textdata."\r\n");
}





?>