<?php

    session_start();

    require_once("model/htmlHead.inc.php");
    require_once("model/stmtEdit.inc.php");
    require_once("model/while.inc.php");

    $previous_page = isset($_SERVER['HTTP_REFERER']) ? $_SERVER['HTTP_REFERER'] : null;
    if(isset($_SESSION["MAXIMUM".$_SESSION["username"]])){
        unset($_SESSION["MAXIMUM".$_SESSION["username"]]);

    }



    myHeader();
            

?>

<style>

body{
            background: linear-gradient(to right, #001110, #003D39, #299393, #003D39, #001110); 
        }

    h3{
            color:black;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-top:2rem;
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
               <h1 style="color:white;text-align:center;margin-top:1rem">Impressum</h1>
               <button type="sumbit" name="abmelden" value="Abmelden" class="button-image3"><img src="img/img_45.png" alt="Submit" 
       style="width: 50px;height: 50px;"></button>

    </form>


<div  style="padding-top:2rem;padding-bottom:2rem;height:25rem;margin-top:3rem;margin-right:3rem; margin-left:3rem; 
background: linear-gradient(to right, grey, grey);border-radius: 0.75rem; overflow: auto;">
        <div style="background: linear-gradient(to right, #CCCCCC, #CCCCCC)">
                <br>
                <h3>Name und Anschrift:</h2>

                <p>cbm Powergroup GmbH & co. KG<br>
                Wegesende 3-4<br>
                28195 Bremen</p>

                

                <h3>Kontakt:</h3>
                
                <p>E-Mail: Powergroup@maximum-brains.com<br>
                Telefon: 0421/362023</p>
        
   
    

        

        
        
            <h3>Verantwortlich für den Inhalt nach § 55 Abs. 2 RStV:</h3>

            <p>Power Group<br>
            Hells Door 666<br>
            28007 Errorstadt</p>
            <br>
            <h3>Haftungsausschluss:</h3>

            <p>Trotz sorgfältiger inhaltlicher Kontrolle übernehmen 
                wir keine Haftung für die Inhalte externer Links. Für den Inhalt der verlinkten Seiten sind ausschließlich deren Betreiber verantwortlich.</p>
                <br>
            <h3>Urheberrecht:</h3>

            <p>Die Inhalte und Werke auf dieser Website unterliegen dem deutschen Urheberrecht. 
                Die Vervielfältigung, Bearbeitung, Verbreitung und jede Art der Verwertung außerhalb 
                der Grenzen des Urheberrechts bedürfen der schriftlichen Zustimmung des
                 jeweiligen Autors bzw. 
                Erstellers. Downloads und Kopien dieser Seite sind nur für den privaten, nicht kommerziellen Gebrauch gestattet.</p>
                <br>
            <h3>Datenschutz:</h3>

            <p>Die Nutzung unserer Webseite ist in der Regel ohne Angabe personenbezogener 
                Daten möglich. Soweit auf unseren Seiten personenbezogene Daten (z.B. Name, Anschrift oder E-Mail-Adresse) 
                erhoben werden, erfolgt dies, soweit möglich, stets auf freiwilliger Basis. Weitere Informationen zum 
                Datenschutz finden Sie in unserer Datenschutzerklärung.</p>
            </p>
            <br><br>
        </div>
</div>

      


    
    

           

<br/><br/><br/>




<div style="justify-content: center">
     <a style="color: inherit;text-decoration: none" href="<?php echo $previous_page; ?>"><div style="background-color:#EAA714; border-radius: 0.75rem; 
display: flex; justify-content: center; align-items: center;padding-top:1rem;padding-left:1rem;padding-right:1rem; margin-right:55rem; margin-left:55rem;
box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);">
    <h2 style="color:white;font-family: sans-serif">Zurück</h2>
    </div></a>
</div>





<div class="cover6"></div>

  
</body>
</html>

