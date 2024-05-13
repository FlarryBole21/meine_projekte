<?php


#$dsn =  'mysql:dbname=projekt2023; host=127.0.0.1';

$servername = "localhost";
$username = array("SuperUser" => 1, "User_01" => 2);

$password = "224466";
$dbname = "projekt2023";

$Fehlermeldung= false;



#$dbh = new pdo($dsn, $user, $password);

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  // Benutzername und Passwort aus dem Formular abrufen

  if(isset($_POST['username']) && isset($_POST['password'])){
    $input_username = $_POST["username"];
    $input_password = $_POST["password"];
  
      // Überprüfen, ob Benutzername und Passwort korrekt sind
      if (array_key_exists($input_username, $username) && $input_password === $password) {
        // Verbindung zur Datenbank herstellen
        $username = $_POST["username"];
        $conn = mysqli_connect($servername, $username, $password, $dbname);

        // Verbindung prüfen
        if (!$conn) {
            die("Verbindung fehlgeschlagen: " . mysqli_connect_error());
        }
        echo "Verbindung zur Datenbank erfolgreich hergestellt.";

        // Verbindung schließen
        mysqli_close($conn);

        

        // Umleitung auf die Zielseite
        #header("Location: http://localhost/php/Aufgaben/Meine_Aufgaben2/projekt_2023/projekt2023.php");
        header("Location: http://localhost/php/Playlist/projekt_2023/projekt2023.php");
        exit();
      } else {
        $Fehlermeldung= true;
        #echo "Benutzername oder Passwort falsch";
      }
  }
}


?>


<!DOCTYPE html>
 <style>
     * {
        margin-top: 0;
        padding: 0;
        box-sizing: border-box;
    }
    
    body {
        background: linear-gradient(to bottom, #100404, #100404, #100404);
        background-image: url("img/img_06.jpg");

          background-size: 100% 1080px;
    
    }
    
		
	</style>
<html>
<body>

<div style="margin-top:3rem; margin-left:38rem; height:25rem; width: 44rem; background-color:brown;border-radius: 0.75rem">

        <form action="Anmeldung+Weiterleitung.php" method="POST" style=" display: flex;
                justify-content: center;
                align-items: center;background-color:#22333B">
                      <img src="img/img_01.png" width="50" height="50" style="margin-right: 1rem">
                      <h1 style="color:white; text-align:center; margin-top:1rem ">Anmeldung</h1>


            </form>
                      

          <div style="margin-top:3rem; margin-left:15rem; height:15rem; width: 14rem; background-color:#FAFF7F;border-radius: 0.75rem">

              <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" style="color:black; margin-left:1.5rem">
              <br>
                <h3>Benutzername:</h3> <input type="text" name="username"><br><br>
                <h3>Passwort:</h3> <input type="password" name="password"><br><br><br>
                <input type="submit" value="Anmelden" style="margin-left:3rem; font-size:1rem">
              </form>

          </div>

</div>

<?php



 if($Fehlermeldung == true){
      echo "<br>";  
      #echo '<p style="margin-left:50rem">Benutzername oder Passwort sind falsch</p>';  

      echo '<div style=" margin-top:3rem; height:4rem; width: 100%; background-color:#FFC6D0;
                         display: flex;
                            justify-content: center;
                            align-items: center">
                            <p style=" text-align:center; margin-top:1rem; font-size:1rem; color:red">
                            Benutzer oder Passwort sind falsch. Bitte versuche es erneut oder wende dich an den Kundensupport.</p>
      
        </div>';
  }

?>

</body>
</html>
