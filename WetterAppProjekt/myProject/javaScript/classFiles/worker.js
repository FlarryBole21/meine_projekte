//Worker-Object --> Jeder Worker, der erstellt wird, wird heir reingeschrieben
class WorkerObject{
    static worker=[];
    #id;
    #name;
    #workerPath='javaScript/workerFiles/';
    #worker;
    constructor(name,workerPath){
        this.#id=WorkerObject.worker.length;
        this.#name=name;
        this.#workerPath+=workerPath;
        this.#worker=this.workerSetzen(workerPath);
        WorkerObject.worker.push(this);
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

    getWorker(){
        return this.#worker;

    }

    setWorker(value){
        this.#worker=value;
    }

    workerSetzen(){
        return new Worker(this.#workerPath);
    }

    workerBeenden(){
        this.#worker.terminate();
    }

    workerSendeDaten(message){
        this.getWorker().postMessage(message);
    }

    workerErwartetDaten(){
        return new Promise((resolve) => {
            this.getWorker().onmessage = (event) => {
                resolve(event.data);
            };
        }); 
    }

    static starteWorker(id,name,path){
        return new Promise((resolve, reject) => {
            if (typeof(Worker) !== "undefined") {
                try{
                    let object=new WorkerObject(id,name,path);
                    resolve(object);  

                }
                catch(err){
                    reject(err);
                }
            } else {
                reject("Dein Browser hat keinen WebWorker Support!")
            } 
        }); 
    }

    static beendeAlleWorker() {
        WorkerObject.worker.forEach(object =>{
            object.workerBeenden();
        })
        WorkerObject.worker=[];
    }
}


