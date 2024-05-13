var apiLink = "https://api.open-meteo.com/v1/forecast?"

//Worker zur Verbindung mit der Wetter-API
onmessage = (event) => {
    if (event.data.status === 'geoCheck' || event.data.status === 'geoNotCheck'){
        
        var parameter = {
            "latitude": event.data.latitude,
            "longitude": event.data.longitude,
            //Daten innerhalb dieses Objekts, mit Ausnahme von Breitengrade & Längengerade sind manipulierbar // austauschbar
            "current":'temperature_2m,relative_humidity_2m,is_day,precipitation,weather_code,cloud_cover,pressure_msl,surface_pressure',
            "daily":"weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max"
        }
    }
    
    //Wenn eine Verbindung mit der Wetter-APP erstellt werden konnte, SONST ERROR
    try{
        Object.entries(parameter).forEach((element,index)=>{
            apiLink+=`${element[0]}=${element[1]}`;
            if(index != Object.entries(parameter).length-1){
                apiLink+='&';
            }
        })

        fetch(apiLink).
            then(result => {
                return result.json()
            }).
            then(data => {
                if(event.data.status === 'geoCheck'){
                    data.location=event.data.location;
                }
                else{
                    data.city=event.data.city;
                }
                postMessage({status:'weatherCheck',data:data})
            }).
            catch(error => {
                postMessage({status:'weatherNotCheck',data:error})
            }); 
    }
    catch(err){
        console.log(err);
    }
};
