//Zur Konvertierung eines Objekts in ein anderes Objekt
onmessage = (event) => {
    if (event.data.status === 'convertObjectToObject'){
        myData=event.data.data;
        let index=Object.values(myData.object_01).length;
        myData.object_01[`data_${index}`]=myData.object_02;

        postMessage({status:'convertObjectToObject',data:myData.object_01});

    }
};