const programmLinkButton = document.getElementById('programmLinkButton');

//Überprüfung des button-Werts 1 oder 0 --> Programm wird gestartet oder beendet
programmLinkButton.addEventListener('click',function(){
    let value=programmLinkButton.getAttribute('value');
    switch (value){
        case '0':
            starteProgramm();
            break;
        case '1':
            beendeProgramm();
            break;
    }
})

//Hauptprogramm wird gestartet & die einzelnen Segemnte werden abgearbeitet
async function starteProgramm(){
    programmZustandSegment('Programm wird gestartet...','Stop Program','1');
    let output=await selectedInputSegment();
    let output2=await wetterAppSegment(output);
    let output3=await datenAnzeigenSegment(output2);
    if(output3){
        chartDatenAnzeigenSegment(output3);
    }
}

//Hauptprogramm wird beendet, Chart wird ausgeblendet, Alle Worker werden geschlossen
function beendeProgramm(){
    chartDatenEntfernen('subBlock2');
    programmZustandSegment('Programm wird beendet...','Run Program','0');
    if (typeof(WorkerObject) !== "undefined") {
        WorkerObject.beendeAlleWorker();
    }
    datenAnzeigenSegment();
}

//Programm wird auch bei Änderung der Auswahl im Select-Menü durchlaufen --> Änderung der Daten --> Altes Diagramm wird entfernt, um Neues hinzufügen zu können
function selectAenderung(){
    chartDatenEntfernen('subBlock2');
    let value=programmLinkButton.getAttribute('value');
    if (value == '1'){
        starteProgramm();
    }
}















