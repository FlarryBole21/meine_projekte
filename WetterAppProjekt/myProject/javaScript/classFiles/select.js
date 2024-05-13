const workerPath='javaScript/jsonFiles/';
const jsonData='staedte.json';
const selectId="selectStadt";

//Erstellung des Select-Objekts --> Alle Städte mit Ausnahme der Geolocation werden hier verwaltet
class SelectObject{
    static options=[];
    #id;
    #data;
    constructor(data){
        this.#id=SelectObject.options.length;
        this.#data=data;
        SelectObject.options.push(this);
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
 
    getData(){
        return this.#data;
      }
    setData(value){
        this.#data=value;
    }

    static async fuelleSelectOptions(selectId,workerPath,jsonData){
        let selectStadt = document.getElementById(selectId);
        let path=`${workerPath}${jsonData}`;
        let response=await fetch(`${path}`);
        let data=await response.json();
        
        data.forEach(element => {
            let optionElement=document.createElement("option");
            optionElement.innerHTML=element.city;
            optionElement.setAttribute('value',element.city.toLowerCase());
            optionElement.classList.add('option');
            selectStadt.appendChild(optionElement);
            new SelectObject(element); 
            if(typeof(LocationObject) !== "undefined"){
                new LocationObject(element.latitude,element.longitude,element.city);
            }  
        });

    }
}

SelectObject.fuelleSelectOptions(selectId,workerPath,jsonData);




