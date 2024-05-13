<?php

function dataList($stmt){

    while($row = $stmt->fetch()){

        

       


        echo "<tr>";
        echo "<th>".$row["id"]."</th>";
        echo "<th>".$row["interpret"]."</th>";
        echo "<th>".$row["titel"]."</th>";
        echo "<th>".$row["album"]."</th>";
        echo "<th>".$row["erscheinungsdatum"]."</th>";
        echo "<th>".$row["laenge"]."</th>";
        echo "<th>".$row["urheber_musik"]."</th>";
        echo "<th>".$row["urheber_text"]."</th>";
        echo "<th>".$row["genre"]."</th>";
        if($_SESSION["myid"] == "01"){
            echo "<th>".$row["dateiname"]."</th>";
     
                          
          }
        

        echo "</tr>";

        

   }

    
}

function playList($stmt){

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
      

      echo "</tr>";

      

 }

  
}

function playListAdd($stmt){

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

        echo "<th>".'
        <form action="playlist_01_addTo.php" method="POST" style="margin-left:3rem">
           <button type="sumbit" name="button" value="'.
       $row["id"].'" class="button-image"><img src="img/img_46.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

        </form>'
        ."</th>";  
  
      

      echo "</tr>";

      

 }

  
}

function playListAddRep($stmt){

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
        if(!in_array ($row["id"], $_SESSION[$_SESSION["username"]])){ 

        echo "<th>".'
        <form action="playlist_01_addTo.php" method="POST" style="margin-left:3rem">
           <button type="sumbit" name="button" value="'.
       $row["id"].'" class="button-image"><img src="img/img_46.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

        </form>'
        ."</th>";  
  
        }else{
          echo "<th>".'
        <div style="margin-left:4rem">
           <button type="sumbit" name="button" value="'.
       $row["id"].'" class="button-image2"><img src="img/img_46.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

        </div>'
        ."</th>";  

        }

      echo "</tr>";
        

     

 }

  
}

function dataListDel($stmt){

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
      echo "<th>".$row["dateiname"]."</th>";

         

          $delVar++;

          $toDel = $delVar;

          $buttonDel= loescheB($toDel);

          if($buttonDel > 0){
              
                          echo "<th>".'
                  <form action="library_Del.php" method="POST" style="margin-left:3rem">
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
                                  
                                  
                                  
                              
                                              
                  
                  $stmt = $dbh->prepare('DELETE FROM datenformat_1 WHERE id="'.$del_song.'"');
                  
                  $stmt->execute();
                                          
                  

          }

  
}

function playListDel($stmt){

  
                     

}




function userList($stmt){

  #print_r( $_SESSION["mitgliedPlus"]);

  while($row = $stmt->fetch()){

 

      echo "<tr>";
      echo "<th>".$row["pid"]."</th>";
      echo "<th>".$row["name"]."</th>";
      echo "<th>".$row["passwort"]."</th>";
      // echo "<th>".$row["mitglied"]."</th>";
      $vergleich = $row["pid"];
     
      if(in_array ($vergleich,  $_SESSION["mitgliedPlus"])){
        echo "<th>True</th>";

      }else{
        echo "<th>False</th>";
      }
      

      echo "</tr>";


 }

  
}

function userListDel($stmt){

  $delVar = 0;

  function loescheB($newDel){

              if($newDel > 0){

                  return $newDel;

              }

  }
  

  while($row = $stmt->fetch()){

      
      

      echo "<tr>";
      echo "<th>".$row["pid"]."</th>";
      echo "<th>".$row["name"]."</th>";
      
      echo "<th>".$row["passwort"]."</th>";
      $vergleich = $row["pid"];
     
      if(in_array ($vergleich,  $_SESSION["mitgliedPlus"])){
        echo "<th>True</th>";

      }else{
        echo "<th>False</th>";
      }
     

         

          $delVar++;

          $toDel = $delVar;

          $buttonDel= loescheB($toDel);

          if($buttonDel > 0){
              
                          echo '<th >'.'
                  <form action="user_Del.php" method="POST" style="margin-left:3rem">
                      <button type="sumbit" name="button" value="'.
                  $row["pid"].'" class="button-image"><img src="img/img_47.png" alt="Submit" 
                  style="width: 50px;height: 50px;"></button>

                  </form>'
                  ."</th>";


              }

          


          echo "</tr>";


      
          }

          if (isset($_POST["button"])) {

              $del_user = $_POST["button"];
                                  
                  
                  // $stmt = $dbh->prepare('SELECT name FROM benutzer WHERE pid="'.$del_user.'"'); 

                  // $stmt->execute();

                  // $username = $stmt->fetch();
                  
                  // echo $username;
                  
                  // $stmt = $dbh->prepare("DROP USER '$username'@'%'");

                  // $stmt->execute();

                  $stmt = $dbh->prepare('SELECT name FROM benutzer WHERE pid=:del_user');
                  $stmt->bindParam(':del_user', $del_user);
                  $stmt->execute();
                  $username = $stmt->fetchColumn();

                  $stmt = $dbh->prepare("DROP USER :username@'%'");
                  $stmt->bindParam(':username', $username);
                  $stmt->execute();

                  $_SESSION["DELUSER"] = $del_user;
                  

                  $stmt = $dbh->prepare('DELETE FROM benutzer WHERE pid="'.$del_user.'"');
                  
                  $stmt->execute();


                  $stmt = $dbh->prepare('DELETE FROM playlists WHERE pid="'.$del_user.'"');
                  
                  $stmt->execute();
                  
                  


                  
                  
                                          
                  

          }

  
}



  



?>