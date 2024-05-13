
<!DOCTYPE html>
 <style>
     * {
        margin-top: 0;
        padding: 0;
        box-sizing: border-box;
    }
    
    body {
        background: linear-gradient(to bottom, #100404, #100404, #100404);
        /* background-image: url("gif/gif_01.gif"); */



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

              <form method="post" style="color:black; margin-left:1.5rem">
              <br>
                <h3>Benutzername:</h3> <input type="text" name="username" value=><br><br>
                <h3>Passwort:</h3> <input type="password" name="passwort" value=><br><br><br>
                <input type="submit" name="button" value="Anmelden" style="margin-left:3rem; font-size:1rem">
              </form>

          </div>

</div>

<?php


    #require_once("model/dbprojekt.inc.php");
    require_once("model/stmtEdit.inc.php");
    session_start();

    function notLogin(){
        echo "<br>";  
      

        echo '<div style=" margin-top:3rem; height:4rem; width: 100%; background-color:#FFC6D0;
                display: flex;
                justify-content: center;
            align-items: center">
            <p style=" text-align:center; margin-top:1rem; font-size:1rem; color:red">
            Benutzer oder Passwort sind falsch. Bitte versuche es erneut oder wende dich an den Kundensupport.</p>

            </div>';


    };


           if (isset($_POST["button"])) {

            if($_POST["button"]=="Anmelden"){

                

                if (isset($_POST["username"]) && isset($_POST["passwort"])) {

                    $stmt = $dbh->prepare('SELECT name FROM benutzer');

                    $stmt->execute();

                    $usernames = $stmt->fetchAll(PDO::FETCH_COLUMN);

                    $myArray = [];

                    $zaehler = 0;

                    foreach($usernames as $username){
                         

                            $myArray[$zaehler] = $username;
   
// .

                            $zaehler = $zaehler + 1;
                               

                        }

                    //var_dump($myArray);
                    //echo "<br>";

                    $stmt = $dbh->prepare('SELECT passwort FROM benutzer');

                    $stmt->execute();

                    $passwords = $stmt->fetchAll(PDO::FETCH_COLUMN);

                    $myPass = [];

                    $newZaehler = 0;

                    foreach($passwords as $password){
                         

                            $myPass[$newZaehler] = $password;
   
// .

                            $newZaehler = $newZaehler + 1;
                               

                        }
                    
                    
                    //var_dump($myPass);
                    //echo "<br>";


                    if(in_array ($_POST["username"], $myArray) && in_array($_POST["passwort"], $myPass)){
                        

                            
                            
                            $nameIndex = array_search($_POST["username"], $myArray);
                            #var_dump($nameIndex);
                            $plusIndex = $nameIndex;
                            #echo $plusIndex;
                            $plusIndex++;
                            #var_dump($plusIndex);
                            $finalIndex = $plusIndex;
                            #var_dump($finalIndex);
                            $myID = sprintf("%02d", $finalIndex);
                            #var_dump($myID);
                            
                            #echo $myID;
                            #echo "<br>";
                            
                            $myIndex = $myPass[$nameIndex];
                            #echo $myIndex;
                            #echo "<br>";

                            #$passIndex = array_search($_POST["passwort"], $myPass);
                            #echo "FAKE ----->  ".$passIndex;
                            #echo "<br>";
                            #echo $myPass[$nameIndex];
                            #$savePlus = $myPass[$nameIndex];
                            #echo $savePlus;
                            #echo "<br>";
                            #$plusIndex = array_search($savePlus, $myPass);
                            #echo $plusIndex;
                            #echo "<br>";
                            

                            if($_POST["passwort"]=== $myIndex){
                                $_SESSION["username"] = $_POST["username"];
                                $_SESSION["myid"] = $myID;
                                $_SESSION["myrawid"] = $finalIndex;
                                $_SESSION["mypasswort"] = $myIndex;
                                

                                $mitgliedPlus= userMitglied($dbh);

                                $_SESSION["mitgliedPlus"] = $mitgliedPlus;
                                
                                
                                header("Location: http://localhost/php/Playlist/projekt_2023/projekt2023.php");
                            }else{
                                notLogin();
                            }

                                        

                        

                    }else{
                        notLogin();

                    }
                  

                }

            }
  
           }


?>




</body>
</html>
