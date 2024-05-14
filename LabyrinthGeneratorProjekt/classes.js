class Feld{
    constructor(){
        this.north=null
        this.east=null
        this.south=null
        this.west=null
    }

    getDirections(){
        return {north:this.north,east:this.east,south:this.south,west:this.west}
    }

    getValidDirections(){
        return Object.entries({north:this.north,east:this.east,south:this.south,west:this.west}).filter(([_,value])=> value != null)
    }
}

class Stack{
    constructor(){
        this.stack=new Array()
    }

    getStack(){
        return this.stack
    }

    removeLastAndReturn(){
        return this.stack.pop()
    }

    addToStack(element){
        this.stack.push(element)
    }

    getStackLength(){
        return this.stack.length
    }
    
}


class Player{
    constructor(currentField,name){
        this.currentField=currentField
        this.name = name
    }
 
}

module.exports = {
    Feld,
    Stack,
    Player
};
