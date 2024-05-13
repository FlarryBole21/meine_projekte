const apiLink = 'https://api.opencagedata.com/geocode/v1/json';

//verbindung mit der Location-APP, Wenn eine Verbindung aufgebaut werden konnte, dann werden die Daten übertragen, ansonten Fehlermeldung
onmessage = (event) => {

    if (event.data.status === 'geoLocationCheck'){
        let apiKey = '4384963ad16049bc84d004a9d1cf5d55';
        var latitude = event.data.latitude;
        var longitude = event.data.longitude;
        var query = latitude + ',' + longitude;

        var request_url = apiLink
            + '?'
            + 'key=' + apiKey
            + '&q=' + encodeURIComponent(query)
            + '&pretty=1'
            + '&no_annotations=1';

        fetch(request_url).
        then(response => {return response.json()}).
        then(data => {
                postMessage({status:'geoLocationCheck',data:data.results[0].formatted});
            }
        ).
        catch(err => {
            postMessage({status:'geoLocationNotCheck',data:err});
        }
        );
    };
};


