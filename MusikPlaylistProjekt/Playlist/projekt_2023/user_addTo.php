
<!-- FUNKTIONIERT NICHT! -->



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

    $newCount= userCount($dbh);
    $newCount++;
    $newPlus =  $newCount;

    // echo '<h2 style="color:red">'; echo $newCount; echo'</h2>';
    

    function notEingabe(){

      
      
      return true;
  
    };


    if (isset($_POST["button"])) {

      if($_POST["button"]=="0"){

        
       

          if (isset($_POST["pid"]) && isset($_POST["name"]) && isset($_POST["passwort"])){
            
                        $id_string = $_POST["pid"];
                        $id_user = intval($id_string);
                        

                        

                        
                        

                        $myData=userRepID($dbh);
                        $myData2=userRepName($dbh);
                        if(!in_array ($id_user,$myData) && !in_array ($_POST["name"],$myData2) && $id_user !== 0 && $_POST["name"] != NULL
                        && $_POST["passwort"] != NULL){
                          
                                            $stmt = $dbh->prepare('INSERT INTO benutzer (pid, name, passwort, mitglied) 
                                            VALUES (:pid, :name, :passwort, :mitglied)');
                                            $stmt->bindValue(':pid', $_POST["pid"]);
                                            $stmt->bindValue(':name', $_POST["name"]); 
                                            $stmt->bindValue(':passwort', $_POST["passwort"]);
                                            if (isset($_POST["mitglied"])){
                                            $stmt->bindValue(':mitglied', intval("1") ); 
                                            }else{
                                              $stmt->bindValue(':mitglied', intval("0") ); 
                                            }
                                        
                                          
                                            $stmt->execute();
                                            $newUsername= $_POST["name"];
                                            $newPassword = $_POST["passwort"];
                                            $stmt = $dbh->prepare("CREATE USER '{$newUsername}'@'%' IDENTIFIED BY '{$newPassword}'");
                                            $stmt->execute();
                                            $privileges = "SELECT, INSERT, UPDATE, DELETE";
                                            $dbname = "projekt2023";
                                            $stmt = $dbh->prepare("GRANT $privileges ON $dbname.* TO '$newUsername'@'%'");
                                            $stmt->execute();
                                            $keinInhalt = false;
                                            
                                            $newCount= userCount($dbh);
                                            $newCount++;
                                            $newPlus =  $newCount;

                                            

                                          } else{
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
               <h1 style="color:white; text-align:center; margin-top:1rem ">User hinzufügen?</h1>
               <button type="sumbit" name="abmelden" value="Abmelden" class="button-image3"><img src="img/img_45.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

     </form>
    
    <table style= "margin-top:1rem">
            <tr>
                
            <th class="ueberschriften3">PID</td><br><br/>
            <th class="ueberschriften3">Name</td><br><br/>
            <th class="ueberschriften3">Passwort</td><br><br/>
            <th class="ueberschriften3">Mitglied</td><br><br/>
            
                    
                    
                    </th>
            </tr>
   

    <?php
    

    $stmt= userSelect($dbh);

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

    ?>

</table>

<?php

    $newCount= userCount($dbh);

                    
        
        
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


  <div style="padding-top:0.5rem;height:6rem;margin-right:1rem; margin-left:1rem; background-color:#227FAE;border-radius: 0.75rem">
    <form action="user_addTo.php" method="post" style="color:black; display: flex; justify-content: space-evenly; align-items: center;">
      <div style="width: 4rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">ID:</p>
        <input type="number" name="pid" value=<?php echo $newPlus;?>>
      </div>
      <div style="width: 10rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Name:</p>
        <input type="text" name="name" value=>
      </div>
      <div style="width: 10rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Passwort:</p>
        <input type="text" name="passwort" value=>
      </div>
      <div style="width: 10rem; display: flex; justify-content: center; flex-direction: column;">
        <p class="myInputs">Mitglied:</p>
        <input type="checkbox" name="mitglied" value=>
      </div>
      
      
      <form action="user_addTo.php" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center; ">
               <button type="sumbit" name="button" value="0" class="button-image"><img src="img/img_46.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

        </form>

        <br>
              
            </form>
  </div>




<br><br><br>


<?php

    $newCount= userCount($dbh);

                    
        
        
            if($newCount != 0){
                    echo '<div style="padding-top:2rem;padding-bottom:1rem;height:15rem;margin-top:1rem;margin-right:20rem; margin-left:20rem; 
                    background: linear-gradient(to right, #14C3DF,#227FAE,#14C3DF,#227FAE,#14C3DF);border-radius: 0.75rem; overflow: auto;">           
                        <div style="background: linear-gradient(to right, #CCCCCC, #CCCCCC);padding-top:2rem;padding-bottom:2rem;
                        display:flex; justify-content: center; align-items: center">

                            
                                  
                        
                                <div>    
                                    <a href="projekt2023_user.php">
                                        <img class="clickMe" src="img/img_50.png" width="120" height="100">
                                    </a>
                                </div>

                                <div  style="margin-left:5rem">    
                                    <a href="user_Del.php">
                                        <img class="clickMe" src="img/img_52.png" width="120" height="100">
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
                background: linear-gradient(to right,#14C3DF,#227FAE,#14C3DF,#227FAE,#14C3DF);border-radius: 0.75rem; overflow: auto;">           
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



    $newCount= userCount($dbh);

            
    myEnd3($newCount);
                    
  
    ?>


    
</body>
</html>

