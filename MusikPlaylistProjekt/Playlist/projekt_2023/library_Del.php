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
echo '<script src="model/functions.js">




</script>';


if (isset($_POST["button"])) {

    $del_song = $_POST["button"];
                        
                        
                        
                    
                                    
        
        $stmt = $dbh->prepare('DELETE FROM datenformat_1 WHERE id="'.$del_song.'"');
        
        $stmt->execute();
                                
        

        header("Location: http://localhost/php/Playlist/projekt_2023/library_Del.php");
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
               <h1 style="color:white;text-align:center;margin-top:1rem">Aus der Library entfernen?</h1>
               <button type="sumbit" name="abmelden" value="Abmelden" class="button-image3"><img src="img/img_45.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

     </form>

    
    <table style= "margin-top:1rem">
            <tr>
                
                    <th class="ueberschriften2">ID</td><br><br/>
                    <th class="ueberschriften2">Interpret</td><br><br/>
                    <th class="ueberschriften2">Titel</td><br><br/>
                    <th class="ueberschriften2">Album</td><br><br/>
                    <th class="ueberschriften2">Erscheinungsdatum</td><br><br/>
                    <th class="ueberschriften2">Länge</td><br><br/>
                    <th class="ueberschriften2">Urheber der Musik</td><br><br/>
                    <th class="ueberschriften2">Urheber des Textes</td><br><br/>
                    <th class="ueberschriften2">Genre</td><br><br/>
                    <th class="ueberschriften2">Dateiname</td><br><br/>
                    
                        
                    
                    <th class="ueberschriften2">Löschen?</td><br><br/>
                    
                    </th>
                    
              
            </tr>
   

    <?php

     
                        
            $stmt= dataSelect($dbh);

           

            dataListDel($stmt);              
                                        


    ?>

</table>

<?php

    $newCount= dataCount($dbh);

                    
        
        
    if($newCount == 0){

                        
    
    

        echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
                display: flex;
                    justify-content: center;
                    align-items: center">
                <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Diese Library hat keine Einträge, die gelöscht werden könnten!</h2>
    
                </div>';
    }

?>


<br/><br/><br/>

<?php

    $newCount= dataCount($dbh);

                    
        
        
            if($newCount != 0){
                    echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:15rem; margin-left:15rem; 
                    background: linear-gradient(to right, #E117DA,#7E14E0,#E117DA,#7E14E0,#E117DA);border-radius: 0.75rem; overflow: auto;">           
                        <div style="background: linear-gradient(to right, #CCCCCC, #CCCCCC);padding-top:2rem;padding-bottom:2rem;
                        display:flex; justify-content: center; align-items: center">

                            
                                    <div>    
                                            
                                    <img class="clickMe" src="img/img_64.png" width="120" height="100" onclick="openMusicLibrary()">
                                
                                </div>
                        
                                <div style="margin-left:5rem">    
                                    <a href="projekt2023_library.php">
                                        <img class="clickMe" src="img/img_29.png" width="120" height="100">
                                    </a>
                                </div>

                                <div  style="margin-left:5rem">    
                                    <a href="library_addTo.php">
                                        <img class="clickMe" src="img/img_48.png" width="120" height="100">
                                    </a>
                                </div>

                                

                                <div style="margin-left:5rem"> 

                                    <a href="projekt2023.php">
                                                <img class="clickMe2" src="img/img_01.png" width="120" height="100">
                                    </a>
                                </div>

                                <div style="margin-left:5rem"> 

                                        <a href="';echo $previous_page;echo'">
                                                    <img class="clickMe2" src="img/img_60.png" width="120" height="100">
                                        </a>
                                </div>

                                <div style="margin-left:5rem"> 

                                        <a href="';echo $_SESSION["aktuelleSeite"];echo'">
                                                    <img class="clickMe2" src="img/img_61.png" width="120" height="100">
                                        </a>
                                </div>
                    
                            
                        </div>
                    </div>';

            }elseif($newCount == 0){

                echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:35rem; margin-left:35rem; 
                background: linear-gradient(to right, #E117DA,#7E14E0,#E117DA,#7E14E0,#E117DA);border-radius: 0.75rem; overflow: auto;">           
                    <div style="background: linear-gradient(to left,#CCCCCC, #CCCCCC);padding-top:2rem;padding-bottom:2rem;
                    display:flex; justify-content: center; align-items: center">
                    
                                

                            <div> 

                            <a href="projekt2023.php">
                                        <img class="clickMe" src="img/img_01.png" width="120" height="100">
                            </a>
                        </div>

                        <div style="margin-left:5rem"> 

                                <a href="';echo $previous_page;echo'">
                                            <img class="clickMe" src="img/img_60.png" width="120" height="100">
                                </a>
                        </div>

                        <div style="margin-left:5rem"> 

                                <a href="';echo $_SESSION["aktuelleSeite"];echo'">
                                            <img class="clickMe" src="img/img_61.png" width="120" height="100">
                                </a>
                        </div>
    
                       
                        
                    </div>
                </div>';

            }
?>




<?php

    $newCount= dataCount($dbh);

                    
        
    myEnd2($newCount);

    audioLink();
?>

<!-- <div class="cover"></div> -->

    




    
</body>
</html>

