<?php

    session_start();

    require_once("model/dbprojekt.inc.php");

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

   
    // if(isset($_SESSION[$_SESSION["username"]])){
    // echo '<h2 style="color:red">'.json_encode($_SESSION[$_SESSION["username"]]).'</h2>';  
    // echo '<h2 style="color:red">'.json_encode($_SESSION["username"]).'</h2>';
    // }

    if(isset($_SESSION["MAXIMUM".$_SESSION["username"]])){

        if($_SESSION["MAXIMUM".$_SESSION["username"]] == true){

        

        $maximumLimit = true;

        }else{

            $maximumLimit = false;

        }

        

    }

                                    

    if (isset($_POST["button"])) {


        

        header("Location: http://localhost/php/Playlist/projekt_2023/playlist_01_addTo.php");
    }

    myHeader();

    
            

?>

<body>
         


    
    <form action="Anmeldung_NEU.php" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center">
               <a href="projekt2023.php">
                <img src="img/img_01.png" action="projekt2023.php" width="50" height="50" style="margin-right: 1rem">
                </a>
               <h1 style="color:white; text-align:center; margin-top:1rem">Zur Playlist hinzufügen?</h1>
               <button type="sumbit" name="abmelden" value="Abmelden" class="button-image3"><img src="img/img_45.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

     </form>

     
     
    
    <table style= "margin-top:1rem">
            <tr>
                
                    <th class="ueberschriften">ID</td><br><br/>
                    <th class="ueberschriften">Interpret</td><br><br/>
                    <th class="ueberschriften">Titel</td><br><br/>
                    <th class="ueberschriften">Album</td><br><br/>
                    <th class="ueberschriften">Erscheinungsdatum</td><br><br/>
                    <th class="ueberschriften">Länge</td><br><br/>
                    <th class="ueberschriften">Urheber der Musik</td><br><br/>
                    <th class="ueberschriften">Urheber des Textes</td><br><br/>
                    <th class="ueberschriften">Genre</td><br><br/>
                    
                    <?php
                        if($_SESSION["myid"] == "01"){
                            echo "<th class='ueberschriften'>Dateiname</td><br><br/>";
                        }
                        
                    ?>
                    <th class="ueberschriften">Hinzufügen?</td><br><br/>
                    </th>
              
            </tr>

    
   

    <?php
    
 

    

    // $sql = "SELECT * FROM `datenformat_1`;";

    
    // if(isset($_SESSION[$_SESSION["username"]])){
    //     unset($_SESSION[$_SESSION["username"]]);

    // }


   

    
                 
    if(!isset($_SESSION[$_SESSION["username"]])){
        $stmt= dataSelect($dbh);

        playListAdd($stmt);

    }else{

        $stmt= dataSelect($dbh);

        playListAddRep($stmt);

    }
    





    
    if (isset($_POST["button"])) {

        




        $id_song = $_POST["button"];

                $stmt = $dbh->prepare('select datenformat_1.id from datenformat_1,playlists,benutzer 
            where playlists.pid=benutzer.pid and playlists.id_song=datenformat_1.id and playlists.pid="'.$_SESSION["myid"].'"');

            $stmt->execute();

            $songsSchonDa = $stmt->fetchAll(PDO::FETCH_COLUMN);

            $ArraySchonDa = [];

            $zaehlerSchonDa = 0;

            foreach($songsSchonDa as $songSchonDa){
                

                    $ArraySchonDa[$zaehlerSchonDa] = $songSchonDa;

                // .

                    $zaehlerSchonDa = $zaehlerSchonDa + 1;
                        

                }    
          
            
        
        

            if(!in_array ($id_song, $ArraySchonDa)){ 

                if (in_array ($_SESSION["myrawid"],  $_SESSION["mitgliedPlus"])){
                    
                    
                    $stmt = $dbh->prepare('SELECT MAX(playlist_id) AS max_id FROM playlists');
                    $stmt->execute();
                    $maxPId = $stmt->fetch();
                    $newPId = $maxPId['max_id'] + 1;
                    
                
                    $stmt = $dbh->prepare('INSERT INTO playlists (playlist_id, pid, id_song) VALUES (:playlist_id, :pid, :id_song)');
                    $stmt->bindValue(':playlist_id', $newPId);
                    $stmt->bindValue(':pid', $_SESSION["myid"]); 
                    $stmt->bindValue(':id_song', $id_song); 
                    $stmt->execute();

                    $stmt = $dbh->prepare('select datenformat_1.id from datenformat_1,playlists,benutzer 
                    where playlists.pid=benutzer.pid and playlists.id_song=datenformat_1.id and playlists.pid="'.$_SESSION["myid"].'"');
        
                    $stmt->execute();
        
                    $songsSchonDa = $stmt->fetchAll(PDO::FETCH_COLUMN);
        
                    $ArraySchonDa = [];
        
                    $zaehlerSchonDa = 0;
        
                    foreach($songsSchonDa as $songSchonDa){
                        
        
                            $ArraySchonDa[$zaehlerSchonDa] = $songSchonDa;
        
                        // .
        
                            $zaehlerSchonDa = $zaehlerSchonDa + 1;
                                
        
                    }

                    $_SESSION[$_SESSION["username"]] = $ArraySchonDa;

                    
                    

                    

                }else{

                            $newCount= listCount($dbh);
                            // echo '<h2 style="color:red">'; echo $newCount; echo'</h2>';

                            if ($newCount < 3){

                                $stmt = $dbh->prepare('SELECT MAX(playlist_id) AS max_id FROM playlists');
                                $stmt->execute();
                                $maxPId = $stmt->fetch();
                                $newPId = $maxPId['max_id'] + 1;
                                
                            
                                $stmt = $dbh->prepare('INSERT INTO playlists (playlist_id, pid, id_song) VALUES (:playlist_id, :pid, :id_song)');
                                $stmt->bindValue(':playlist_id', $newPId);
                                $stmt->bindValue(':pid', $_SESSION["myid"]); 
                                $stmt->bindValue(':id_song', $id_song); 
                                $stmt->execute();
                                $stmt = $dbh->prepare('select datenformat_1.id from datenformat_1,playlists,benutzer 
                                where playlists.pid=benutzer.pid and playlists.id_song=datenformat_1.id and playlists.pid="'.$_SESSION["myid"].'"');
                    
                                                $stmt->execute();
                                    
                                                $songsSchonDa = $stmt->fetchAll(PDO::FETCH_COLUMN);
                                    
                                                $ArraySchonDa = [];
                                    
                                                $zaehlerSchonDa = 0;
                                    
                                                foreach($songsSchonDa as $songSchonDa){
                                                    
                                    
                                                        $ArraySchonDa[$zaehlerSchonDa] = $songSchonDa;
                                    
                                                    // .
                                    
                                                        $zaehlerSchonDa = $zaehlerSchonDa + 1;
                                                            
                                    
                                                }
                            
                                                $_SESSION[$_SESSION["username"]] = $ArraySchonDa;
                                

                                }elseif(!$newCount < 3){

                                    $_SESSION["MAXIMUM".$_SESSION["username"]]= true;

                                    // echo '<h2 style="color:red">'; echo $newCount; echo'</h2>';

                                }





            }   
            



        }}
    
     
    ?>

</table>

<?php

    $newCount= dataCount($dbh);

       
        
    if($newCount == 0){

                        
    
    

        echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
                display: flex;
                    justify-content: center;
                    align-items: center">
                <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Diese Library hat keine Einträge, die zu deiner Playlist hinzugefügt werden könnten!</h2>
    
                </div>';
    }

    if (isset($maximumLimit)){
        if ($maximumLimit == true){

                        echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
                    display: flex;
                        justify-content: center;
                        align-items: center">
                    <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Maximumlimit der Playlist erreicht. Werde Mitglied, um mehr Songs hinzuzufügen!</h2>

                    </div>';
        }

    }
    
?>



<br/><br/><br/>

<?php

    $newCount= dataCount($dbh);

                    
        
        
            if($newCount != 0){
                    echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:15rem; margin-left:15rem; 
                    background: linear-gradient(to right, #12CE54,#0B7765, #12CE54, #0B7765,#12CE54);border-radius: 0.75rem; overflow: auto;">           
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
                                    <a href="playlist_01_Normal.php">
                                        <img class="clickMe" src="img/img_54.png" width="120" height="100">
                                    </a>
                                </div>

                                <div style="margin-left:5rem">    
                                    <a href="playlist_01_Del.php">
                                        <img class="clickMe" src="img/img_56.png" width="120" height="100">
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
                background: linear-gradient(to right, #12CE54,#0B7765, #12CE54, #0B7765,#12CE54);border-radius: 0.75rem; overflow: auto;">           
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

    myEnd($newCount);  

    audioLink();

  
    ?>

<!-- <script src="model/functions.js">




</script> -->


    
</body>
</html>

