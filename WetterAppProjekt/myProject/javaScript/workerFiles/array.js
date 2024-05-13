//Konvertierung in ein Objekt, sodass damit weitergeabeitet werden kann
onmessage = (event) => {
    if (event.data.status === 'showData'){
        dataObject=event.data.data;
        let dataTransferObject={};
        if(dataObject.dataCollection){
            Object.values(dataObject.dataCollection).forEach(element=>{
                transfromObject(element,dataTransferObject);
            })
        }
        postMessage({status:'showData',data:dataTransferObject})
    }
};

//Map-Methode wo bestimmte Elemente gefiltert werden, Rückgabe ist das gefilterte Array
function arrayMapping(objectKey){
    array=new Array();
    Object.entries(objectKey).forEach(element => {
        gemappt=element.map((element2,index) => {
            if (index % 2 == 0){
                return element2.charAt(0).toUpperCase() + element2.slice(1);
            }else{
                return element2;
            }
        })
        array.push(gemappt);
    });
    return array;
}


function transfromObject(element,dataTransferObject){
    let data=arrayMapping(element);
    index=Object.values(dataTransferObject).length;
    dataTransferObject[`data_${index}`]=data;

}