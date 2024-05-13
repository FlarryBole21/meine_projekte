<?php

    session_start();
    require_once("model/dbprojekt.inc.php");
    $_SESSION["aktuelleSeite"] = $_SERVER['PHP_SELF'];
    unset($_SESSION["zufall"]);
    $previous_page = isset($_SERVER['HTTP_REFERER']) ? $_SERVER['HTTP_REFERER'] : null;
    if(isset($_SESSION["MAXIMUM".$_SESSION["username"]])){
        unset($_SESSION["MAXIMUM".$_SESSION["username"]]);

    }
    
    
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <style>
        body{
            background: linear-gradient(to right, #001110, #003D39, #299393, #003D39, #001110); 
        }
    </style>
    
<!-- <link rel="stylesheet" href="model/design_main.css"> -->
</head>
<body>
            



    <div>
            <!-- <div style="background-image: url('gif/gif_01.gif'); height:100%; width: 100%"> -->
                <div class="cover"></div>
                
                <!-- <h1 class="titel">Waving To New Emotions, The Path To Great Sound Is Yours!</h1> -->
                
                
                
                
                
            </div>
            
    </div>

    

    

    <?php

    if(!in_array ($_SESSION["myrawid"],  $_SESSION["mitgliedPlus"])){
        

        echo '<div style="margin-top:1rem">
        <a href="ad_error.php" target="_blank">
            <img class="clickable" src="gif/gif_27.gif" width="25%" style="float: left;">
            <img class="clickable" src="gif/gif_22.gif" width="18%" style="float: right;">
            <img class="clickable" src="gif/gif_17.gif" width="32%" style="float: left;">
            <img class="clickable" src="gif/gif_23.gif" width="25%" style="float: right;">
        </a>
        </div>';

        echo "<br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br><br><br<<br> ";

    }

    
    ?>


    <!-- <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br>  -->

    <!-- <div>
    <a id="mitte">
</div> -->
    

<div>

    <div style="display:flex; justify-content: space-between; align-items: center;">
    <div>
            <?php
                if(in_array ($_SESSION["myrawid"],  $_SESSION["mitgliedPlus"])){
                    echo '<h2 style="color:lime; font-size: 4rem;font-family: sans-serif; margin-left:2rem;display: inline">Mitglied</h2>'; 
                }else{
                    echo '<h2 style="color:red; font-size: 4rem;font-family: sans-serif; margin-left:2rem;display: inline">Kein</h2><br>';
                    echo '<h2 style="color:red; font-size: 4rem;font-family: sans-serif; margin-left:2rem;display: inline">Mitglied</h2>'; 
                }
            ?>
           
    
    </div>
    <div style="margin-top:2rem">
    <h2 style="color:white; font-size: 4rem;font-family: sans-serif;display: inline">Willkommen!</h2>
    
    </div>
    <a href="Anmeldung_NEU.php" title="Abmelden">
        <img class="clickable" src="img/img_45.png"  width="150" height="150" style="margin-right:2rem">
    </a>
    </div>
    <?php

    if(!in_array ($_SESSION["myrawid"],  $_SESSION["mitgliedPlus"])){
        echo "<br><br><br>";
    }
    ?>
    <div style="display: flex; justify-content: flex-start; align-items: flex-end;padding-left:2rem; padding-bottom:2rem;">
                    <!-- <a style="color: inherit;text-decoration: none" href="coming_soon.php"><div style="background-color:#EAA714; border-radius: 0.75rem; 
                display: flex; justify-content: center; align-items: center;padding-top:1rem;padding-left:1rem;padding-right:1rem;
                box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);">
                    <h2 style="color:white;font-family: sans-serif">Mitgliedsstaus ändern</h2> -->
            </div></a>

    
    <br><br><br>
    
  

</div>

<div style="display:flex; justify-content: center; align-items: center; margin-top:-5rem">

    <a href="<?php echo $_SESSION["aktuelleSeite"];?>">
                    <img class="mainClick" src="img/img_01.png" title="Seite neuladen"  width="300" height="300" style="margin-left: 5rem">
    </a>


</div>

<br><br><br>

<div style="margin-bottom:2rem">
        <div style="display:flex; justify-content: center; align-items: center;">
        
    
        <h2 style="color:white; font-size: 4rem;margin-left:5rem;margin-bottom:15rem;font-family: sans-serif"><?php echo $_SESSION["username"];?></h2>
    
        </div>

                  
</div>





<div style="padding-top:2rem;height:15rem;margin-top:1rem;margin-right:1rem; margin-left:1rem; 
background: linear-gradient(to right, #DF9816,#EC6321, #DF9816, #EC6321,#DF9816);border-radius: 0.75rem; overflow: auto;">           
    <div style="background: linear-gradient(to right, #551A8B, #9143DB, #551A8B, #9143DB, #551A8B);padding-top:3rem;padding-bottom:3rem;
    display:flex; justify-content: center; align-items: center">
    
        
        <a href="playlist_01_Normal.php">
                    <img class="clickMe" src="img/img_54.png" title="Meine Playlist" width="120" height="100" >
        </a>
        
        <a href="playlist_01_addTo.php">
                    <img class="clickMe" src="img/img_55.png" title="Zur Playlist hinzufügen?" width="120" height="100" style="margin-left: 5rem">
        </a>
        <a href="playlist_01_Del.php">
                    <img class="clickMe" src="img/img_56.png" title="Aus der Playlist entfernen?" width="120" height="100" style="margin-left: 5rem">
        </a>
        <a href="projekt2023_library.php">
                    <img class="clickMe" src="img/img_29.png" title="Library" width="120" height="100" style="margin-left: 5rem">
        </a>
        <a <?php if($_SESSION["myid"] == "01"){
            echo 'href="library_addTo.php" title="Zur Library hinzufügen?"';
        }?>>
                    <img <?php if($_SESSION["myid"] == "01"){
                        echo'class="Available"';

                    }else{
                        echo'class="notAvailable"';
                    }?>  src="img/img_48.png"  width="120" height="100" style="margin-left: 5rem">
        </a>
        <a  <?php if($_SESSION["myid"] == "01"){
            echo 'href="library_Del.php" title="Aus der Library enfernen?"';
        }?>>
                    <img <?php if($_SESSION["myid"] == "01"){
                        echo'class="Available"';

                    }else{
                        echo'class="notAvailable"';
                    }?> src="img/img_53.png"  width="120" height="100" style="margin-left: 5rem">
        </a>
        <a  <?php if($_SESSION["myid"] == "01"){
            echo 'href="projekt2023_user.php" title="User"';
        }?>>
                    <img <?php if($_SESSION["myid"] == "01"){
                        echo'class="Available"';

                    }else{
                        echo'class="notAvailable"';
                    }?>  src="img/img_50.png"  width="120" height="100" style="margin-left: 5rem">
        </a>
        <a <?php if($_SESSION["myid"] == "01"){
            echo 'href="user_addTo.php" title="User hinzufügen?"';
        }?>>
                    <img <?php if($_SESSION["myid"] == "01"){
                        echo'class="Available"';

                    }else{
                        echo'class="notAvailable"';
                    }?>  src="img/img_51.png" width="120" height="100" style="margin-left: 5rem">
        </a>
        <a <?php if($_SESSION["myid"] == "01"){
            echo 'href="user_Del.php" title="User entfernen?"';
        }?>>
                    <img <?php if($_SESSION["myid"] == "01"){
                        echo'class="Available"';

                    }else{
                        echo'class="notAvailable"';
                    }?>  src="img/img_52.png"  width="120" height="100" style="margin-left: 5rem">
        </a>
    </div>
</div> 

                
                

     

    

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>






<?php


    if(!in_array ($_SESSION["myrawid"],  $_SESSION["mitgliedPlus"])){
        

        echo '<br><br><br><br><br><br>
        <div style="margin-top:-5rem">
                <a href="ad_error.php" target="_blank">
                <img class="clickable" src="gif/gif_24.gif" width="15%" style="float: left;">
                <img class="clickable" src="gif/gif_25.gif" width="35%" style="float: right;">
                <img class="clickable" src="gif/gif_11.gif" width="50%" style="float: left;">
                </a>
                </div>
                
                <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br> <br><br><br><br><br> ';

        

    }


?>



<div class="cover2" style="display: flex; justify-content: flex-start; align-items: flex-end;padding-left:2rem; padding-bottom:2rem">
    <a style="color: inherit;text-decoration: none" href="impressum.php"><div style="background-color:#EAA714; border-radius: 0.75rem; 
    display: flex; justify-content: center; align-items: center;padding-left:1rem;padding-right:1rem;
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);">
        <h2 style="color:white;font-family: sans-serif">Impressum</h2>
        </div></a>
        <a style="color: inherit;text-decoration: none" href="../Datenschutzerklärung.pdf" target="_blank" ><div style="background-color:#EAA714; border-radius: 0.75rem; 
    display: flex; justify-content: center; align-items: center;padding-left:1rem;padding-right:1rem;
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);margin-left:2rem">
        <h2 style="color:white;font-family: sans-serif">Datenschutzerklärung</h2>
        </div></a>
</div>

</body>
</html>


