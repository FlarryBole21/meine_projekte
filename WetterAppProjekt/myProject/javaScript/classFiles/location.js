const geoWorkerFile='geoapp.js';

//LocationObject --> Jede Auswahl in der Select auf der HTML ist auch gleichzeitig ein Location-Object 
class LocationObject{
  static locations=[];
  #id;
  #latitude;
  #longitude
  #city
  constructor(latitude,longitude,city){
    this.#id=LocationObject.locations.length;
    this.#latitude=latitude;
    this.#longitude=longitude;
    this.#city=city;
    LocationObject.locations.push(this);
  }
  getId(){
    return this.#id;
  }
  setId(value){
      if(Number.isInteger(value)){
          this.#id=value;
      }
      else{
          console.log('Ungültiger Wert für Set-Methode. Nur Ganzzahlen erlaubt!');
          return;
      }
  }
  getLatitude(){
    return this.#latitude;
  }
  setLatitude(value){
      this.#latitude=value;
  }
  getLongitude(){
    return this.#longitude;
  }
  setLongitude(value){
      this.#longitude=value;
  }

  getCity(){
    return this.#city;
  }
  setCity(value){
      this.#city=value;
  }

  static gebeAlleLocations(){
      return new Promise((resolve)=>{
        resolve(LocationObject.locations);
      })
  }

}

//Nur Geolocation haben Zugriff auf diese Methoden
class GeoLocationObject extends LocationObject{
  static geoLocations=[];
  constructor(latitude,longitude,location){
    super(latitude,longitude,location);
    GeoLocationObject.geoLocations.push(this);
  }

  getLocation(){
    return this.getCity();
  }
  setLocation(value){
      this.setCity(value);
  }

  static bekommeGeolocation(){
    return new Promise((resolve,reject)=>{
      navigator.geolocation.getCurrentPosition(
        async function(position) {
          let latitudeValue= position.coords.latitude;
          let longitudeValue = position.coords.longitude;
          let geoWorker=await WorkerObject.starteWorker('GeoWorker',geoWorkerFile);
          await geoWorker.workerSendeDaten({status: 'geoLocationCheck', latitude: latitudeValue, longitude: longitudeValue}) 
          let data=await geoWorker.workerErwartetDaten(geoWorker);
          let locationValue=data.data;
          if(data.status== 'geoLocationCheck'){
            data = { status: 'geoLocationCheck', latitude: latitudeValue, longitude: longitudeValue ,location:locationValue};
            resolve(data);
          }
          else{
            let err=data.data;
            data = { status: 'geoLocationNotCheck', data:err};
            reject(data);
          }   
        },
        function(error) {
          console.error("Fehler bei der Abfrage der Position:", error.message);
          reject;
        }
      );
    })
  }

  static pruefeGeolocation(){
    if ("geolocation" in navigator) {
      return true;
    } else {
      console.error("Geolocation wird nicht unterstützt");
      return false;
    }
  }

  static gebeAlleGeoLocations(){
    return new Promise((resolve)=>{
      resolve(GeoLocationObject.locations);
    })
}

}

async function erstellungVonLocationObjekt(){
  output=GeoLocationObject.pruefeGeolocation();
  if(output){
    try{
      data=await GeoLocationObject.bekommeGeolocation();
      if(data.status == 'geoLocationCheck'){
        new GeoLocationObject(data.latitude,data.longitude,data.location);
      }
      else{
        throw new Error(data.data);
      }
    }
    catch(err){
      console.log(err.data);
    }
  }
}

erstellungVonLocationObjekt();