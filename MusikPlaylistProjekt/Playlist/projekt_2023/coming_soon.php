<?php

    session_start();

    require_once("model/htmlHead.inc.php");
    require_once("model/stmtEdit.inc.php");
    require_once("model/while.inc.php");

    $previous_page = isset($_SERVER['HTTP_REFERER']) ? $_SERVER['HTTP_REFERER'] : null;
    echo '<link rel="stylesheet" href="model/design.css">';




    myHeader();
            

?>

<style>

    h3{
            color:black;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-top:1.5rem;
            margin-left: 5rem;
            margin-right: 5rem;}

    p{
        color:black;
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-left: 5rem;
        margin-right: 5rem;
        line-height: 1.5;
        
}


</style>


<body>

    <form action="Anmeldung_NEU.php" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center">
               <a href="projekt2023.php">
                <img src="img/img_01.png" action="projekt2023.php" width="50" height="50" style="margin-right: 1rem">
               </a>
               <h1 style="color:white;text-align:center;margin-top:1rem">???</h1>
               <button type="sumbit" name="abmelden" value="Abmelden" class="button-image3"><img src="img/img_45.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

    </form>


<div  style="padding-top:2rem;padding-bottom:2rem;height:25rem;margin-top:3rem;margin-right:3rem; margin-left:3rem; 
background: linear-gradient(to right, black,black);border-radius: 0.75rem;">
        <div style="background-color:#A6B5AF !important;display: flex;
        justify-content: center;
        align-items: center">
                <br>
                <h3>NICHT VERFÜGBAR!</h2>
                

                
                

    
        </div>
        <div style="display: flex;
        justify-content: center; margin-top:1rem;">
        <?php
        $random_number = rand(1, 3);
      /*   if($random_number == 3){
           echo'<img src="gif/gif_15.gif" width=10% >
            
            ';
            

        }elseif($random_number == 2){
            echo'<img src="gif/gif_14.gif" width=10%>
            ';

        }else{
            echo'<img src="gif/gif_16.gif" width=10%">
            ';

        } */
    ?>
        <!-- <img src="gif/gif_19.gif" width="250" height="250" style=""> -->
        <?php
      /*   $random_number = rand(1, 3);
        if($random_number == 3){
           echo'<img src="gif/gif_15.gif" width=10%>
            
            ';
            

        }elseif($random_number == 2){
            echo'<img src="gif/gif_14.gif" width=10%>
            ';

        }else{
            echo'<img src="gif/gif_16.gif" width=10%">
            ';

        } */
    ?>
        
        
</div>
</div>


      


    
    

           

<br/><br/><br/>





<form action="<?php echo $previous_page; ?>" method="POST" style=" display: flex;
        justify-content: center;
        align-items: center;">
               <button type="sumbit" name="fulltext" value="0" style="font-size:2rem;border-radius: 0.75rem;background-color:#FAFF7F">Zurück</button>

     </form>





<div class="cover3"></div>
            
            
            
   
    

<!-- <div class="cover2"></div> -->

    




    
</body>
</html>

