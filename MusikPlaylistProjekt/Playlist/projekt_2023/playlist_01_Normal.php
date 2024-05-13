<?php

    session_start();

    require_once("model/htmlHead.inc.php");
    require_once("model/stmtEdit.inc.php");
    require_once("model/while.inc.php");
    $_SESSION["aktuelleSeite"] = $_SERVER['PHP_SELF'];
    $previous_page = isset($_SERVER['HTTP_REFERER']) ? $_SERVER['HTTP_REFERER'] : null;

    myHeader();

    if(isset($_SESSION["MAXIMUM".$_SESSION["username"]])){
        unset($_SESSION["MAXIMUM".$_SESSION["username"]]);

    }
        

?>


<body>

    <form action="Anmeldung_NEU.php" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center">
               <a href="projekt2023.php">
                <img src="img/img_01.png" action="projekt2023.php" width="50" height="50" style="margin-right: 1rem">
               </a>
               <h1 style="color:white;text-align:center;margin-top:1rem">Meine Playlist</h1>
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
                    
                    
                    </th>
                    
              
            </tr>

           

  <!-- <audio controls>
  <source src="audio.mp3" type="audio/mpeg">
  <source src="audio.ogg" type="audio/ogg">
  Your browser does not support the audio element.
</audio> -->
   

    <?php
        
        
        

        if (isset($_GET['befehl'])) {

            $stmt= randomSelect($dbh);
            
            playList($stmt);
    



        }else{

            $stmt= listSelect($dbh);

            
            playList($stmt);
            
            
        }


           

       
        
        
    
    ?>

</table>

<?php

    $newCount= dataCount($dbh);

    // echo '<h2 style="color:red">'; echo $newCount; echo'</h2>';             
            
            
    if($newCount == 0){

                        



        echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
                display: flex;
                    justify-content: center;
                    align-items: center">
                <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Die Library hat keine Einträge!</h2>

                </div>';
    }else{

        $newCount= listCount($dbh);

        
        // echo '<h2 style="color:red">'; echo $newCount; echo'</h2>';
        
        if($newCount == 0){

            

                            
        
        

            echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
                    display: flex;
                        justify-content: center;
                        align-items: center">
                    <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Diese Playlist hat keine Einträge!</h2>
      
                    </div>';
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
                                    <img class="clickMe" title="Startseite" src="img/img_01.png" width="120" height="100">
                        </a>
                    </div>

                    <div style="margin-left:5rem"> 

                            <a href="';echo $previous_page;echo'">
                                        <img class="clickMe" title="Zurück" src="img/img_60.png" width="120" height="100">
                            </a>
                    </div>

                    <div style="margin-left:5rem"> 

                            <a href="';echo $_SESSION["aktuelleSeite"];echo'">
                                        <img class="clickMe" title="Neu laden" src="img/img_61.png" width="120" height="100">
                            </a>
                    </div>

               
                
            </div>
        </div>';

}else{
    $newCount= listCount($dbh);


                    
        
        
            if($newCount != 0){
                    echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:5rem; margin-left:5rem; 
                    background: linear-gradient(to right, #12CE54,#0B7765, #12CE54, #0B7765,#12CE54);border-radius: 0.75rem; overflow: auto;">           
                        <div style="background: linear-gradient(to right, #CCCCCC,#CCCCCC);padding-top:2rem;padding-bottom:2rem;
                        display:flex; justify-content: center; align-items: center">

                                

                                <div>    
                                
                                    <img class="clickMe" title="Audio öffnen" src="img/img_64.png" width="120" height="100" onclick="openMusicLibrary()">
                                
                                </div>

                                <div style="margin-left:5rem">    
                                    <a href="projekt2023_library.php">
                                        <img class="clickMe" title="Library" src="img/img_29.png" width="120" height="100">
                                    </a>
                                </div>
                        
                        
                                <div style="margin-left:5rem">    
                                    <a href="playlist_01_addTo.php">
                                        <img class="clickMe" title="Zur Playlist hinzufügen?" src="img/img_55.png" width="120" height="100">
                                    </a>
                                </div>

                                <div style="margin-left:5rem">    
                                    <a href="playlist_01_Del.php">
                                        <img class="clickMe" title="Aus der Playlist entfernen?" src="img/img_56.png" width="120" height="100">
                                    </a>
                                </div>


                                <div style="margin-left:5rem"> 
                                    
                                    <img class="clickMe" title="Zufällige Reihenfolge" src="img/img_59.png" width="120" height="100" onclick="mechanismusAusloesen()">
                                
                                </div>

                                <div style="margin-left:5rem"> 

                                    <a href="projekt2023.php">
                                                <img class="clickMe2" title="Startseite" src="img/img_01.png" width="120" height="100">
                                    </a>
                                </div>

                                <div style="margin-left:5rem"> 

                                        <a href="';echo $previous_page;echo'">
                                                    <img class="clickMe2" title="Zurück" src="img/img_60.png" width="120" height="100">
                                        </a>
                                </div>

                                <div style="margin-left:5rem"> 

                                        <a href="';echo $_SESSION["aktuelleSeite"];echo'">
                                                    <img class="clickMe2" title="Neu laden" src="img/img_61.png" width="120" height="100">
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
                                <img class="clickMe" title="Library" src="img/img_29.png" width="120" height="100">
                            </a>
                        </div>

                                <div style="margin-left:5rem">    
                                <a href="playlist_01_addTo.php">
                                    <img class="clickMe" title="Zur Playlist hinzufügen?" src="img/img_55.png" width="120" height="100">
                                </a>
                            </div>

                            <div style="margin-left:5rem"> 

                                <a href="projekt2023.php">
                                            <img class="clickMe" title="Startseite" src="img/img_01.png" width="120" height="100">
                                </a>
                            </div>

                            <div style="margin-left:5rem"> 

                                    <a href="';echo $previous_page;echo'">
                                                <img class="clickMe" title="Zurück" src="img/img_60.png" width="120" height="100">
                                    </a>
                            </div>

                            <div style="margin-left:5rem"> 

                                    <a href="';echo $_SESSION["aktuelleSeite"];echo'">
                                                <img class="clickMe" title="Neu laden" src="img/img_61.png" width="120" height="100">
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






<script src="model/functions.js">




</script>



    
</body>
</html>

