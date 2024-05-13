const arrayWorkerFile='array.js';
const objectWorkerFile='object.js';
const chartWorkerFile='chartData.js';

//Konvertierung der Daten + Anzeigen der Daten in Textform --> Prüfung auf Attribute des Objekts
async function datenAnzeigenSegment(dataObject=null){
    if(dataObject == null){
        let neueListe;
        BlockObject.blocks.forEach(element=>{
            if(element.getId()==='subBlock1'){
                element.childRemoval(1);
                neueListe = BlockObject.blocks.filter(item=> {
                    return item.getId() !== element.getId();
                });
            }
        })
        BlockObject.ueberschreibeListe(neueListe);
    }
    else{
        dataObject.dataCollection={};
        let objectWorker=await WorkerObject.starteWorker('ObjectWorker',objectWorkerFile);
        let showLoaction=true;

        if(showLoaction){
            newLocation=new DataObject('Location');
            if(dataObject.location != undefined){
                objectInfo=newLocation.objektUmKeysErgaenzen('elevation',dataObject.elevation,null,4,
                ['longitude',dataObject.longitude,'latitude',dataObject.latitude,'location',dataObject.location,'title',newLocation.getName()]);
            }
            if(dataObject.city != undefined){
                objectInfo=newLocation.objektUmKeysErgaenzen('elevation',dataObject.elevation,null,4,
                ['longitude',dataObject.longitude,'latitude',dataObject.latitude,'city',dataObject.city,'title',newLocation.getName()]);
            }
            objectWorker.workerSendeDaten({status: 'convertObjectToObject', data:{object_01:dataObject.dataCollection,object_02:objectInfo} });
            let output=await objectWorker.workerErwartetDaten(objectWorker); 
            dataObject.dataCollection=output.data;
        }
        
        if(dataObject.current){
            newCurrent=new DataObject('Current');
            objectInfo=newCurrent.objektUmKeysErgaenzen('title',newCurrent.getName(),dataObject.current);
            objectWorker.workerSendeDaten({status: 'convertObjectToObject', data:{object_01:dataObject.dataCollection,object_02:objectInfo} });
            let output=await objectWorker.workerErwartetDaten(objectWorker); 
            dataObject.dataCollection=output.data;
        }
        if(dataObject.daily){
            newDaily=new DataObject('Daily');
            objectInfo=newDaily.objektUmKeysErgaenzen('title',newDaily.getName(),dataObject.daily);
            objectWorker.workerSendeDaten({status: 'convertObjectToObject', data:{object_01:dataObject.dataCollection,object_02:objectInfo} });
            let output=await objectWorker.workerErwartetDaten(objectWorker); 
            dataObject.dataCollection=output.data;
        }

        let arrayWorker=await WorkerObject.starteWorker('ArrayWorker',arrayWorkerFile);
        arrayWorker.workerSendeDaten({status: 'showData', data:dataObject });
        let data=await arrayWorker.workerErwartetDaten(arrayWorker); 
        if(data.status=='showData'){
            const subBlock=document.getElementById('subBlock1');
            subBlockObject=new BlockObject('subBlock1',subBlock);
            subBlockObject.childRemoval(1);
           
            Object.values(data.data).forEach((element)=>{

                subBlockObject.createAppendDiv(element,'result',true);
            })
        }
        if(dataObject.daily){
            console.log(dataObject.daily);
            return dataObject.daily;
        }
    }
}

//Erstellung des Charts & Anzeigen des Charts --> Nur bei Daily-Key
async function chartDatenAnzeigenSegment(chartObjectData){
    let chartWorker=await WorkerObject.starteWorker('ChartWorker',chartWorkerFile);
    chartWorker.workerSendeDaten({status: 'convertDataToChartData', data:{chartData:chartObjectData,colors:randomColors()}});   
    let output=await chartWorker.workerErwartetDaten(chartWorker); 
    chartDatenAnzeigen(output.data,'subBlock2','rgba(0, 0, 0, 0.8)','2px solid rgb(224, 228, 24)','1rem','50rem','50rem','22rem','line');

}


