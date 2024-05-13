<?php


$dsn =  'mysql:dbname=playlist2023; host=127.0.0.1';
$user = 'SuperUser';
$password = '224466';

$dbh = new pdo($dsn, $user, $password);

/* if (isset($_SESSION["myid"])){
    if($_SESSION["myid"] !== "01"){


        $dbh = new pdo($dsn, $user, $password);
                            
        // $dbh = new pdo($dsn, $_SESSION["username"], $_SESSION["mypasswort"]);
        
    
    }else{
    
        $dbh = new pdo($dsn, $user, $password);
       
    
    }

}else{
    $dbh = new pdo($dsn, $user, $password);
}
 */



?>