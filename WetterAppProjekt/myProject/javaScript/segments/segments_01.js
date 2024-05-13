const weatherWorkerFile='wetterapp.js';

//Programm wird entweder geschlossen oder gestartet, Übergabe der Werte und Veränderung des Button-Werts
function programmZustandSegment(nachricht,knopfText,knopfWert){
    console.log(nachricht);
    programmLinkButton.innerHTML=knopfText;
    programmLinkButton.setAttribute('value',knopfWert);
    programmLinkButton.classList.add('button');
}

//Prüfung der Ausgabe des Nutzers  --> speziell ob es sich um eine Geolocation handelt oder nicht
async function selectedInputSegment(){
    var selectStadt = document.getElementById("selectStadt");
    var selectedValueObject;
    SelectObject.options.forEach((element)=>{
        if(selectStadt.value.toLowerCase() == element.getData().city.toLowerCase()){
            selectedValueObject={status:'cityCheck',city:element.getData().city,latitude:element.getData().latitude,longitude:element.getData().longitude};
        }
    })
    if(selectedValueObject == undefined){
        selectedValueObject={status:'geoCheck'};
    }
    return selectedValueObject;
}

//Verbindung mit der Wetter-APP & Abragen der Daten & Übergabe an nächstes Segment
async function wetterAppSegment(dataObject){
    let wetterWorker=await WorkerObject.starteWorker('WetterWorker',weatherWorkerFile);
    if(typeof(LocationObject) !== "undefined")
    {
        if(dataObject.status == 'geoCheck'){
            let latitudeValue=GeoLocationObject.geoLocations[0].getLatitude();
            let longitudeValue=GeoLocationObject.geoLocations[0].getLongitude();
            let locationValue=GeoLocationObject.geoLocations[0].getLocation();
            wetterWorker.workerSendeDaten({status: 'geoCheck', latitude: latitudeValue, longitude: longitudeValue ,location:locationValue})     
        }
        else{
            wetterWorker.workerSendeDaten({status: 'geoNotCheck', latitude: dataObject.latitude, longitude: dataObject.longitude , city:dataObject.city})  
        }
    }
    data=await wetterWorker.workerErwartetDaten(wetterWorker);
    if(data.status == 'weatherCheck'){
        return data.data;
    }
    else{
        errorMessage(data.data);
    }
}