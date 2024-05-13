<?php

    session_start();

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

    $newCount= dataCount($dbh);
    $newCount++;
    $newPlus =  $newCount;

    // echo '<h2 style="color:red">'; echo $newCount; echo'</h2>';
    

    function notEingabe(){

      
      
      return true;
  
    };


    if (isset($_POST["button"])) {

      if($_POST["button"]=="0"){

        
        

          if (isset($_POST["id"]) && isset($_POST["interpret"]) && isset($_POST["titel"]) 
          && isset($_POST["album"]) && isset($_POST["erscheinungsdatum"]) && isset($_POST["laenge"])
          && isset($_POST["urheber_musik"]) && isset($_POST["urheber_text"]) && isset($_POST["genre"])
          && isset($_POST["dateiname"])){

                        $id_string = $_POST["id"];
                        $datum_string = $_POST["erscheinungsdatum"];
                        $laenge_string = $_POST["laenge"];
                        $id_song = intval($id_string);
                        $datum_song = intval($datum_string);
                        $laenge_song = intval($laenge_string);

                        // echo '<h2 style="color:red">'; echo $datum_song; echo'</h2>';

                       

                        $myData=dataRep($dbh);
                        if(!in_array ($id_song,$myData) && $id_song !== 0 && $_POST["interpret"] != NULL
                        && $_POST["titel"] != NULL && $_POST["album"] != NULL  && $datum_song !== 0 && $laenge_song !== 0
                        && $_POST["urheber_musik"] != NULL && $_POST["urheber_text"] != NULL && $_POST["genre"] != NULL && $_POST["dateiname"] != NULL){
                          if (file_exists('audio/audio_'.$id_song.'.mp3')){

                                            $stmt = $dbh->prepare('INSERT INTO datenformat_1 (id, interpret, titel, album, erscheinungsdatum, laenge, urheber_musik, urheber_text,genre,dateiname) 
                                            VALUES (:id, :interpret, :titel, :album, :erscheinungsdatum, :laenge, :urheber_musik, :urheber_text,:genre,:dateiname)');
                                            $stmt->bindValue(':id', $_POST["id"]);
                                            $stmt->bindValue(':interpret', $_POST["interpret"]); 
                                            $stmt->bindValue(':titel', $_POST["titel"]); 
                                            $stmt->bindValue(':album', $_POST["album"]); 
                                            $stmt->bindValue(':erscheinungsdatum', $_POST["erscheinungsdatum"]); 
                                            $stmt->bindValue(':laenge', $_POST["laenge"]); 
                                            $stmt->bindValue(':urheber_musik', $_POST["urheber_musik"]); 
                                            $stmt->bindValue(':urheber_text', $_POST["urheber_text"]); 
                                            $stmt->bindValue(':genre', $_POST["genre"]); 
                                            $stmt->bindValue(':dateiname', $_POST["dateiname"]); 
                                            $stmt->execute();
                                            $keinInhalt = false;
                                            $newCount= dataCount($dbh);
                                            $newCount++;
                                            $newPlus =  $newCount;


                                          } else{
                                            $keinInhalt=notEingabe();
                                              } 

              

                                            }else{
                                              $keinInhalt=notEingabe();
                                                } 

                
                
            }
              



          
            
          }
      }


    myHeader();
            

?>

<style>

    .myInputs{
        text-align: left;
        padding: 8px;
        border: 1px solid black;
        color:black;
        background-color: #14E199 !important;
        font-weight: bold;
    }

</style>


<body>


    

    <form action="Anmeldung_NEU.php" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center;">
            <a href="projekt2023.php">
                <img src="img/img_01.png" action="projekt2023.php" width="50" height="50" style="margin-right: 1rem">
            </a>
               <h1 style="color:white; text-align:center; margin-top:1rem ">Zur Library hinzufügen?</h1>
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
                    
                    
                    </th>
            </tr>
   

    <?php
    

    $stmt= dataSelect($dbh);

    playList($stmt);
   

    ?>

</table>

<?php

    $newCount= dataCount($dbh);

                    
        
        
    if($newCount == 0){

                        
    
    

        echo '<div style=" margin-top:3rem; height:10rem; width: 100%; background-color:#FFC6D0;
                display: flex;
                    justify-content: center;
                    align-items: center">
                <h2 style=" text-align:center; margin-top:1.25rem; font-size:2.5rem; color:red">Diese Library hat keine Einträge!</h2>
    
                </div>';
    }

    
    if (isset($keinInhalt)) {
      if($keinInhalt == true){

      echo "<br>";  
      

        echo '<div style=" margin-top:3rem; height:4rem; width: 100%; background-color:#FFC6D0;
                display: flex;
                justify-content: center;
            align-items: center">
            <p style=" text-align:center; margin-top:1rem; font-size:1rem; color:red">
            Deine Eingaben sind unvollständig oder ungültig. Bitte versuche es nochmal.</p>

            </div>';

  
    }};

?>

<br/><br/><br/>


  <div style="padding-top:0.5rem;height:6rem;margin-right:1rem; margin-left:1rem; background-color:#B720EB;border-radius: 0.75rem">
    <form action="library_addTo.php" method="post" style="color:black; display: flex; justify-content: space-evenly; align-items: center;">
      <div style="width: 4rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">ID:</p>
        <input type="number" name="id" value=<?php echo $newPlus;?>>
      </div>
      <div style="width: 10rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Interpret:</p>
        <input type="text" name="interpret" value=>
      </div>
      <div style="width: 10rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Titel:</p>
        <input type="text" name="titel" value=>
      </div>
      <div style="width: 10rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Album:</p>
        <input type="text" name="album" value=>
      </div>
      <div style="width: 12rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Erscheinungsdatum:</p>
        <input type="date" name="erscheinungsdatum" value=>
      </div>
      <div style="width: 6rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Länge:</p>
        <input type="time" name="laenge" value=>
      </div>
      <div style="width: 12rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Urheber der Musik:</p>
        <input type="text" name="urheber_musik" value=>
      </div>
      <div style="width: 12rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Urheber des Textes:</p>
        <input type="text" name="urheber_text" value=>
      </div>
      <div style="width: 6rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Genre:</p>
        <input type="text" name="genre" value="">
      </div>
      <div style="width: 12rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Dateiname:</p>
        <input type="text" name="dateiname" value=<?php echo 'audio_'.$newPlus.'.mp3'?>>
      </div>
      <form action="library_addTo.php" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center;">
               <button type="sumbit" name="button" value="0" class="button-image"><img src="img/img_46.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

        </form>

        <br>
              
            </form>
  </div>




<br><br><br>


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
                                    <a href="library_Del.php">
                                        <img class="clickMe" src="img/img_53.png" width="120" height="100">
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

<br>


     

    <?php



   



    $newCount= dataCount($dbh);

            
    myEnd2($newCount);
                    
    audioLink();

    ?>



 




    
</body>
</html>

