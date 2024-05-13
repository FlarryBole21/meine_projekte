//Manche objekte benötigen Extra-Methoden, weswegen für diese speziell eine Klasse angefertigt wurde
class BlockObject{
    static blocks=[];
    #id;
    #type='div';
    #object;
    constructor(id,object){
        this.#id=id;
        this.#object=object;
        BlockObject.blocks.push(this);
    }

    getId(){
        return this.#id;
      }
    setId(value){
        if(value instanceof String){
            this.#id=value;
        }
        else{
            console.log('Ungültiger Wert für Set-Methode. Nur Ganzzahlen erlaubt!');
            return;
        }
    }
    getType(){
    return this.#type;
    }
    setType(value){
        this.#type=value;
    }
    getObject(){
    return this.#object;
    }
    setObject(value){
        this.#object=value;
    }

    childRemoval(number){
        let childCount = this.getObject().childNodes.length;
        if(childCount>0){
            if(childCount>number){
                while (true){
                    let childCount = this.getObject().childNodes.length;
                    if(childCount > number){
                        let object=this.getObject();
                        object.removeChild(object.lastChild);
                    }
                    else{
                        break;
                    }
                }
            }
        }
        else{
            console.log('Block-Element enthält keine Kindelemente')
        }  
    }
    
    createAppendDiv(element1,className=null,listInListInList=false){
        const block=document.createElement('div');
        if(listInListInList){
            element1.forEach((element2,index2) =>{
                if(index2==0 || index2==1){
                    block.innerHTML+='<br>';
                }
                else{
                    block.innerHTML+='<br><br>';
                }
                if(element2[0] == 'Title'){
                    block.innerHTML+=`<span class='underlined header'>${element2[1]}</span>`;
                }else{
                    block.innerHTML+=`${element2[0]}: ${element2[1]}`; 
                }
            })
            block.innerHTML+='<br><br>';
        }
        if(className != null){
            block.classList.add(className);
        }
        this.getObject().appendChild(block);
    }

    static ueberschreibeListe(neueListe) {
        BlockObject.blocks = neueListe;
    }
}
