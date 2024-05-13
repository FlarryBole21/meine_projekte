<?php

require_once("dbprojekt.inc.php");

function dataSelect($dbh){

    if($_SESSION["myid"] == "01"){
        $stmt = $dbh->prepare('select * from datenformat_1');
                             
     }else{
         $stmt = $dbh->prepare('select datenformat_1.id,datenformat_1.interpret,datenformat_1.titel,datenformat_1.album,datenformat_1.erscheinungsdatum,
                         datenformat_1.laenge,datenformat_1.urheber_musik,datenformat_1.urheber_text,datenformat_1.genre from datenformat_1');
 
     }
     
 
     
          
 
     $stmt->execute();

     return $stmt;

    
}

function listSelect($dbh){

    if($_SESSION["myid"] == "01"){
        $stmt = $dbh->prepare('select datenformat_1.id,datenformat_1.interpret,datenformat_1.titel,datenformat_1.album,datenformat_1.erscheinungsdatum,
                         datenformat_1.laenge,datenformat_1.urheber_musik,datenformat_1.urheber_text,datenformat_1.genre, datenformat_1.dateiname
                         from datenformat_1,playlists,benutzer where playlists.pid=benutzer.pid and playlists.id_song=datenformat_1.id and playlists.pid="'.$_SESSION["myid"].'"');
                             
     }else{
         $stmt = $dbh->prepare('select datenformat_1.id,datenformat_1.interpret,datenformat_1.titel,datenformat_1.album,datenformat_1.erscheinungsdatum,
                         datenformat_1.laenge,datenformat_1.urheber_musik,datenformat_1.urheber_text,datenformat_1.genre
                         from datenformat_1,playlists,benutzer where playlists.pid=benutzer.pid and playlists.id_song=datenformat_1.id and playlists.pid="'.$_SESSION["myid"].'"');
 
     }
    
     
 
    $stmt->execute();
 
    return $stmt;
    

}




function randomSelect($dbh){

    if($_SESSION["myid"] == "01"){
        $stmt = $dbh->prepare('select datenformat_1.id,datenformat_1.interpret,datenformat_1.titel,datenformat_1.album,datenformat_1.erscheinungsdatum,
                         datenformat_1.laenge,datenformat_1.urheber_musik,datenformat_1.urheber_text,datenformat_1.genre, datenformat_1.dateiname
                         from datenformat_1,playlists,benutzer where playlists.pid=benutzer.pid and playlists.id_song=datenformat_1.id and playlists.pid="'.$_SESSION["myid"].'" ORDER BY RAND()');
                             
     }else{
         $stmt = $dbh->prepare('select datenformat_1.id,datenformat_1.interpret,datenformat_1.titel,datenformat_1.album,datenformat_1.erscheinungsdatum,
                         datenformat_1.laenge,datenformat_1.urheber_musik,datenformat_1.urheber_text,datenformat_1.genre
                         from datenformat_1,playlists,benutzer where playlists.pid=benutzer.pid and playlists.id_song=datenformat_1.id and playlists.pid="'.$_SESSION["myid"].'" ORDER BY RAND()');
 
     }
    
     
 
    $stmt->execute();
 
    return $stmt;
    

}


function userMitglied($dbh){

    $stmt = $dbh->prepare('select pid from benutzer where mitglied="1"');


    $stmt->execute();


    $pids = $stmt->fetchAll(PDO::FETCH_COLUMN);

    $myMitglied = [];

    $newZaehler = 0;

    foreach($pids as $pid){
         

            $myMitglied[$newZaehler] = $pid;

// .

            $newZaehler = $newZaehler + 1;
               

    }
    
    return  $myMitglied;
}

function userSelect($dbh){

    $stmt = $dbh->prepare('select * from benutzer');

    $stmt->execute();
 
    return $stmt;

}

function dataRep($dbh){

    $stmt = $dbh->prepare('select id from datenformat_1');


    $stmt->execute();


    $ids = $stmt->fetchAll(PDO::FETCH_COLUMN);

    $myData = [];

    $newZaehler = 0;

    foreach($ids as $id){
         

            $myData[$newZaehler] = $id;

// .

            $newZaehler = $newZaehler + 1;
               

    }
    
    return  $myData;
}

function userRepID($dbh){

    $stmt = $dbh->prepare('select pid from benutzer');


    $stmt->execute();


    $ids = $stmt->fetchAll(PDO::FETCH_COLUMN);

    $myData = [];

    $newZaehler = 0;

    foreach($ids as $id){
         

            $myData[$newZaehler] = $id;

// .

            $newZaehler = $newZaehler + 1;
               

    }
    
    return  $myData;
}

function userRepName($dbh){

    $stmt = $dbh->prepare('select pid from benutzer');


    $stmt->execute();


    $ids = $stmt->fetchAll(PDO::FETCH_COLUMN);

    $myData2 = [];

    $newZaehler = 0;

    foreach($ids as $id){
         

            $myData2[$newZaehler] = $id;

// .

            $newZaehler = $newZaehler + 1;
               

    }
    
    return  $myData2;
}



function dataCount($dbh){

    $stmt = $dbh->prepare('SELECT COUNT(*) as count FROM datenformat_1');


    $stmt->execute();

    $countPId = $stmt->fetch();
                    
    $newCount = $countPId['count'];

    return $newCount;

}



function listCount($dbh){

    $stmt = $dbh->prepare('SELECT COUNT(*) as count FROM playlists where playlists.pid="'.$_SESSION["myid"].'"');


    $stmt->execute();

    $countPId = $stmt->fetch();
                    
    $newCount = $countPId['count'];

    return $newCount;

}

function userCount($dbh){

    $stmt = $dbh->prepare('SELECT COUNT(*) as count FROM benutzer');


    $stmt->execute();

    $countPId = $stmt->fetch();
                    
    $newCount = $countPId['count'];

    return $newCount;

}




