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


// if (isset($_SESSION["MAXIMUM".$_SESSION["username"]])) {
// $_SESSION["MAXIMUM".$_SESSION["username"]] = false;
// }

if (isset($_POST["button"])) {

    $del_song = $_POST["button"];
                        
                        
                        
                    
                                    
        
        $stmt = $dbh->prepare('DELETE FROM playlists WHERE pid="'.$_SESSION["myid"].'" and id_song="'.$del_song.'"');
        
        $stmt->execute();

        if(isset($_SESSION[$_SESSION["username"]])){
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


            }
                                
        

        header("Location: http://localhost/php/Playlist/projekt_2023/playlist_01_Del.php");
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
               <h1 style="color:white;text-align:center;margin-top:1rem">Aus der Playlist entfernen?</h1>
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
                    <th class="ueberschriften">Löschen?</td><br><br/>
                    
                    </th>
                    
              
            </tr>
   

    <?php

        #require_once("model/dbprojekt.inc.php");
        require_once("model/stmtEdit.inc.php");                    
        
                        
        
            
    

       $stmt= listSelect($dbh);

       $delVar = 0;

        function loescheB($newDel){

                    if($newDel > 0){

                        return $newDel;

                    }

        }
        

        while($row = $stmt->fetch()){

            
            

            echo "<tr>";
            echo "<th>".$row["id"]."</th>";
            echo "<th>".$row["interpret"]."</th>";
            echo "<th>";
        echo '<a href="#" onclick="playTrack(\'audio/audio_'.$row["id"].'.mp3\')">'.$row["titel"].'</a>';
        if (isset($_GET['befehl'])) {
          echo "<br>";
          echo "<br>";
              echo'<audio class="meinUmschalter" id="audio'.$row["id"].'" src="audio/audio_'.$row["id"].'.mp3" controls></audio>';
        echo "</th>";

        }else{
          echo "<br>";
          echo "<br>";
              echo'<audio class="meinUmschalter" id="audio'.$row["id"].'" src="audio/audio_'.$row["id"].'.mp3"></audio>';
        echo "</th>";
        }
            echo "<th>".$row["album"]."</th>";
            echo "<th>".$row["erscheinungsdatum"]."</th>";
            echo "<th>".$row["laenge"]."</th>";
            echo "<th>".$row["urheber_musik"]."</th>";
            echo "<th>".$row["urheber_text"]."</th>";
            echo "<th>".$row["genre"]."</th>";

                if($_SESSION["myid"] == "01"){
                    echo "<th>".$row["dateiname"]."</th>";
                }

                $delVar++;

                $toDel = $delVar;

                $buttonDel= loescheB($toDel);

                if($buttonDel > 0){
                    
                                echo "<th>".'
                        <form action="playlist_01_Del.php" method="POST" style="margin-left:3rem">
                            <button type="sumbit" name="button" value="'.
                        $row["id"].'" class="button-image"><img src="img/img_47.png" alt="Submit" 
                        style="width: 50px;height: 50px;"></button>

                        </form>'
                        ."</th>";


                    }

                


                echo "</tr>";


            
                }

                if (isset($_POST["button"])) {

                    $del_song = $_POST["button"];
                                        
                                        
                                        
                                    
                                                    
                        
                        $stmt = $dbh->prepare('DELETE FROM playlists WHERE pid="'.$_SESSION["myid"].'" and id_song="'.$del_song.'"');
                        
                        $stmt->execute();

                        if(isset($_SESSION[$_SESSION["username"]])){
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

                    
                        }
                                                
                        
    
                }

            
        
                           
                                        
            
            
            
     

    ?>

</table>

<?php

$newCount= dataCount($dbh);

                        
            
            
if($newCount == 0){

    if(isset($_SESSION[$_SESSION["username"]])){
            unset($_SESSION[$_SESSION["username"]]);
    
    }

    if(isset($_SESSION["MAXIMUM".$_SESSION["username"]])){
        unset($_SESSION["MAXIMUM".$_SESSION["username"]]);

    }

    

    

    
    

                    



    echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
            display: flex;
                justify-content: center;
                align-items: center">
            <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Die Library hat keine Einträge!</h2>

            </div>';
}else{

    $newCount= listCount($dbh);

                
    
    
    if($newCount == 0){

        if(isset($_SESSION[$_SESSION["username"]])){
            unset($_SESSION[$_SESSION["username"]]);
        }

        
    
    
    

                        
    
    

        echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
                display: flex;
                    justify-content: center;
                    align-items: center">
                <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Diese Playlist hat keine Einträge!</h2>
  
                </div>';
    }

    

        if(isset($_SESSION["MAXIMUM".$_SESSION["username"]])){
            unset($_SESSION["MAXIMUM".$_SESSION["username"]]);
    
        }

    

}

?>


<br/><br/><br/>

<?php

    $newCount= dataCount($dbh);

    if($newCount == 0){
        echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:35rem; margin-left:35rem; 
        background: linear-gradient(to right, #12CE54,#0B7765, #12CE54, #0B7765,#12CE54);border-radius: 0.75rem; overflow: auto;">           
            <div style="background: linear-gradient(to left,#CCCCCC,#CCCCCC);padding-top:2rem;padding-bottom:2rem;
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

}else{
    $newCount= listCount($dbh);


                    
        
        
            if($newCount != 0){
                    echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:15rem; margin-left:15rem; 
                    background: linear-gradient(to right, #12CE54,#0B7765, #12CE54, #0B7765,#12CE54);border-radius: 0.75rem; overflow: auto;">           
                        <div style="background: linear-gradient(to right, #CCCCCC,#CCCCCC);padding-top:2rem;padding-bottom:2rem;
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
                                    <a href="playlist_01_addTo.php">
                                        <img class="clickMe" src="img/img_55.png" width="120" height="100">
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

                echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:25rem; margin-left:25rem; 
                background: linear-gradient(to right, #12CE54,#0B7765, #12CE54, #0B7765,#12CE54);border-radius: 0.75rem; overflow: auto;">           
                    <div style="background: linear-gradient(to left,#CCCCCC,#CCCCCC);padding-top:2rem;padding-bottom:2rem;
                    display:flex; justify-content: center; align-items: center">
                    
                            
                            <div>    
                            <a href="projekt2023_library.php">
                                <img class="clickMe" src="img/img_29.png" width="120" height="100">
                            </a>
                        </div>

                                <div style="margin-left:5rem">    
                                <a href="playlist_01_addTo.php">
                                    <img class="clickMe" src="img/img_55.png" width="120" height="100">
                                </a>
                            </div>

                            <div style="margin-left:5rem"> 

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
        }
?>





<?php

    $newCount= dataCount($dbh);

    if($newCount !== 0){

        $newCount= listCount($dbh);



    

        myEnd($newCount);
    }

                    
    audioLink();
        
            


?>

<!-- <div class="cover"></div> -->

<script src="model/functions.js">




</script>





    
</body>
</html>

