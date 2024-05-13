<?php
require_once("model/htmlHead.inc.php");
require_once("model/stmtEdit.inc.php");
require_once("model/while.inc.php");
$_SESSION["aktuelleSeite"] = $_SERVER['PHP_SELF'];
$previous_page = isset($_SERVER['HTTP_REFERER']) ? $_SERVER['HTTP_REFERER'] : null;
echo '<style>
        body{
            background: linear-gradient(to right, #001110, #003D39, #299393, #003D39, #001110); 
        }
    </style>';


if (isset($_POST["button"])) {

        $del_user = $_POST["button"];

        $stmt = $dbh->prepare('SELECT name FROM benutzer WHERE pid=:del_user');
        $stmt->bindParam(':del_user', $del_user);
        $stmt->execute();
        $username = $stmt->fetchColumn();

        $stmt = $dbh->prepare("DROP USER :username@'%'");
        $stmt->bindParam(':username', $username);
        $stmt->execute();
                        
                        
                        
                    
                                    
        
        $stmt = $dbh->prepare('DELETE FROM benutzer WHERE pid="'.$del_user.'"');
        
        $stmt->execute();

        $stmt = $dbh->prepare('DELETE FROM playlists WHERE pid="'.$del_user.'"');
                  
        $stmt->execute();
                  
                                
        

        header("Location: http://localhost/php/Playlist/projekt_2023/user_Del.php");
}
        myHeader();
?> 


<body>

        <?php
            session_start();
            $_SESSION["aktuelleSeite"] = $_SERVER['PHP_SELF'];


          ?>


    
    <form action="Anmeldung_NEU.php" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center">
               <a href="projekt2023.php">
                <img src="img/img_01.png" action="projekt2023.php" width="50" height="50" style="margin-right: 1rem">
               </a>
               <h1 style="color:white;text-align:center;margin-top:1rem">User entfernen?</h1>
               <button type="sumbit" name="abmelden" value="Abmelden" class="button-image3"><img src="img/img_45.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

     </form>

    
    <table style= "margin-top:1rem">
            <tr>
                

                    <th class="ueberschriften3">PID</td><br><br/>
                    <th class="ueberschriften3">Name</td><br><br/>
                    <th class="ueberschriften3">Passwort</td><br><br/>
                    <th class="ueberschriften3">Mitglied</td><br><br/>
                    
                        
                    
                    <th class="ueberschriften3">Löschen?</td><br><br/>
                    
                    </th>
                    
              
            </tr>
   

    <?php

     
                        
            $stmt= userSelect($dbh);

           

            userListDel($stmt);    
            
        //     if(isset($_SESSION["DELUSER"])){
        //         echo '<h2 style="color:red">'; echo $_SESSION["DELUSER"];; echo'</h2>';
        //     }
                                        


    ?>

</table>



<br/><br/><br/>

<?php

    $newCount= userCount($dbh);

                    
        
        
            if($newCount != 0){
                    echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:25rem; margin-left:25rem; 
                    background: linear-gradient(to right, #14C3DF,#227FAE,#14C3DF,#227FAE,#14C3DF);border-radius: 0.75rem; overflow: auto;">           
                        <div style="background: linear-gradient(to right, #CCCCCC, #CCCCCC);padding-top:2rem;padding-bottom:2rem;
                        display:flex; justify-content: center; align-items: center">

                            
                                    
                        
                                <div>    
                                    <a href="projekt2023_user.php">
                                        <img class="clickMe" src="img/img_50.png" title="User" width="120" height="100">
                                    </a>
                                </div>

                                <div  style="margin-left:5rem">    
                                    <a href="user_addTo.php">
                                        <img class="clickMe" src="img/img_51.png" title="User hinzufügen?" width="120" height="100">
                                    </a>
                                </div>

                                

                                <div style="margin-left:5rem"> 

                                    <a href="projekt2023.php">
                                                <img class="clickMe2" src="img/img_01.png" title="Startseite" width="120" height="100">
                                    </a>
                                </div>

                                <div style="margin-left:5rem"> 

                                        <a href="';echo $previous_page;echo'">
                                                    <img class="clickMe2" src="img/img_60.png" title="Zurück" width="120" height="100">
                                        </a>
                                </div>

                                <div style="margin-left:5rem"> 

                                        <a href="';echo $_SESSION["aktuelleSeite"];echo'">
                                                    <img class="clickMe2" src="img/img_61.png" title="Neu laden" width="120" height="100">
                                        </a>
                                </div>
                    
                            
                        </div>
                    </div>';

            }elseif($newCount == 0){

                echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:35rem; margin-left:35rem; 
                background: linear-gradient(to right, #14C3DF,#227FAE,#14C3DF,#227FAE,#14C3DF);border-radius: 0.75rem; overflow: auto;">           
                    <div style="background: linear-gradient(to left,#CCCCCC, #CCCCCC);padding-top:2rem;padding-bottom:2rem;
                    display:flex; justify-content: center; align-items: center">
                    
                                

                            <div> 

                            <a href="projekt2023.php">
                                        <img class="clickMe" src="img/img_01.png" title="Startseite" width="120" height="100">
                            </a>
                        </div>

                        <div style="margin-left:5rem"> 

                                <a href="';echo $previous_page;echo'">
                                            <img class="clickMe" src="img/img_60.png" title="Zurück" width="120" height="100">
                                </a>
                        </div>

                        <div style="margin-left:5rem"> 

                                <a href="';echo $_SESSION["aktuelleSeite"];echo'">
                                            <img class="clickMe" src="img/img_61.png" title="Neu laden" width="120" height="100">
                                </a>
                        </div>
    
                        
                        
                    </div>
                </div>';

            }
?>




<?php

    $newCount= userCount($dbh);

                    
        
    myEnd3($newCount);

    audioLink();
?>

<!-- <div class="cover"></div> -->

    
<script src="model/functions.js">




</script>



    
</body>
</html>

