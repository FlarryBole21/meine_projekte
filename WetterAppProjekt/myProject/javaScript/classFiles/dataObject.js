//Dataobjekte für das Datenanzeigensegemnt müssen konvertiert werden. Jedes einzelne davon wird hier verwaltet
class DataObject{
    static dataObjects=[];
    #id;
    #name;
    constructor(name){
        this.#id=DataObject.dataObjects.length;
        this.#name=name;
        DataObject.dataObjects.push(this);

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

    getName(){
    return this.#name;
    }
    setName(value){
        this.#name=value;
    }

    objektUmKeysErgaenzen(key,value,object=null,rekursionValue=0,rekursionKeyValue=null,rekursionIndex=[-2,-1]){
        let info={};
        info[key]=value;
        if(object !=null){
            Object.entries(object).forEach(([key,value])=>{
                info[key]=value;
            })
        }
        if(rekursionValue != 0 && Number.isInteger(rekursionValue) && rekursionKeyValue != null 
        && rekursionKeyValue instanceof Array){
            if(rekursionKeyValue.length >= 2 && rekursionKeyValue.length % 2 == 0){
                rekursionValue--;
                rekursionIndex[0]=rekursionIndex[0]+2;
                rekursionIndex[1]=rekursionIndex[1]+2;
                info= this.objektUmKeysErgaenzen(rekursionKeyValue[rekursionIndex[0]],
                    rekursionKeyValue[rekursionIndex[1]],info,rekursionValue,
                    rekursionKeyValue,rekursionIndex);
            }
        }
        return info;
    }
}