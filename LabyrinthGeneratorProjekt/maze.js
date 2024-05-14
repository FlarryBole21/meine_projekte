const { Stack, Player,Feld }= require('./classes');
//const { serverRun } = require('./start_server');

const gridNumber=10

/* test_output=Test(79);
console.log(test_output,'\n');

let idGrid=createField(gridNumber);
console.log('\n','Id grid:','\n');
console.log(idGrid,'\n');

let fieldGrid = createField(gridNumber,true);
console.log('\n','Fielded grid:','\n');
console.log(fieldGrid,'\n') */

//console.log('-------------------------------------------------------------------------------------','\n')

/* runMazeCreate()
    .then((data)=> serverRun(data))
    .catch(()=>console.log(`Fehler: ${err}`)) */


function runMazeCreate(){

        try{
            let fieldGrid = createField(gridNumber,true);

            console.log('Creating Maze...','\n');
            let visited = new Array()
            let stack = new Stack()
            let skipCounter=2;
            currentField = Math.floor(Math.random()*gridNumber*gridNumber)
            visited.push(currentField)
            stack.addToStack(currentField)

            do{
                if(visited.length == 100 ){
                    if(skipCounter != 0){
                        skipCounter--;
                    }
                   
                }
                currentField = stack.getStack()[stack.getStack().length - 1];
                if(skipCounter != 0){
                    console.log(`Current --> ${currentField}`,'\n')
                }
                neighbourData=getNeighbours(currentField)
                //console.log(neighbourData)
            
                valid_neighbours=Object.fromEntries(Object.entries(neighbourData.valid_neighbours)
                    .filter(([_,val])=>!visited.includes(val)))
                neighboursLength=Object.values(valid_neighbours).length;

                neighbourData.possible_neighbour_count=neighboursLength
                neighbourData.valid_neighbours=Object.assign({},valid_neighbours)
                neighbourData.visited_fields=visited.sort((a,b)=>a-b)
                neighbourData.visited_fields_count=visited.length
                neighbourData.stack=stack.getStack()
                neighbourData.stack_count=stack.getStackLength()
            
                if(neighboursLength == 0){

                    currentField=stack.removeLastAndReturn()
                    neighbourData.stack_count=stack.getStackLength()
                   
                    if(skipCounter != 0){
                        console.log(neighbourData)
                    }
                    
                    let coord = getCoordFromId( currentField)

                    if(stack.getStack().length > 0){
                        beforeCurrentField=stack.getStack()[stack.getStack().length - 1] 
                        let target = getCoordFromId( beforeCurrentField )
                        if(skipCounter == 2){
                            console.log('\n',`From Current --> ${currentField}`, `Coords --> X: ${coord.x} Y: ${coord.y}`,'\n')
                            console.log('\n',`Moving Back To --> ${beforeCurrentField}`, `Coords --> X: ${target.x} Y: ${target.y}`,'\n')
                        }
                        else if(skipCounter == 1){
                            lastStackedNumber=stack.getStack()[0]
                            let target = getCoordFromId( lastStackedNumber )
                            console.log('\n','All fields visited...','\n')
                            console.log('\n',`From Current --> ${currentField}`, `Coords --> X: ${coord.x} Y: ${coord.y}`,'\n')
                            console.log('\n',`Skipping Back To --> ${lastStackedNumber}`, `Coords --> X: ${target.x} Y: ${target.y}`,'\n')
                            console.log(`.....`)
                            console.log(`.....`)
                            console.log(`.....`)
                        }
                        
                    }else{
                        console.log('\n',`Final Current --> ${currentField}`, `Coords --> X: ${coord.x} Y: ${coord.y}`,'\n')
                    }

                }else{
                    console.log(neighbourData,'\n')
                    let randomNeighbour_obj=getRandomNeighbour(neighboursLength,valid_neighbours)
                    console.log(`Next Field Chosen -->`, randomNeighbour_obj,'\n')
                    randomNeighbour=Object.values(randomNeighbour_obj)[0]
                    let coord = getCoordFromId( currentField)
			        let target = getCoordFromId( randomNeighbour )
                    console.log(`From Current --> ${currentField}`, `Coords --> X: ${coord.x} Y: ${coord.y}`,'\n')
                    console.log(`Moving To --> ${randomNeighbour}`, `Coords --> X: ${target.x} Y: ${target.y}`,'\n')

                    switch( Object.keys(randomNeighbour_obj)[0] ) {
                        case 'north':
                            fieldGrid[coord.y][coord.x].north = fieldGrid[target.y][target.x]
                            fieldGrid[target.y][target.x].south = fieldGrid[coord.y][coord.x]
                        break
                        case 'east':
                            fieldGrid[coord.y][coord.x].east = fieldGrid[target.y][target.x]
                            fieldGrid[target.y][target.x].west = fieldGrid[coord.y][coord.x]
                        break
                        case 'south':
                            fieldGrid[coord.y][coord.x].south = fieldGrid[target.y][target.x]
                            fieldGrid[target.y][target.x].north = fieldGrid[coord.y][coord.x]	 		
                        break
                        case 'west':
                            fieldGrid[coord.y][coord.x].west = fieldGrid[target.y][target.x]
                            fieldGrid[target.y][target.x].east = fieldGrid[coord.y][coord.x] 
                        break
                    }

                    currentField=randomNeighbour
                    stack.addToStack(currentField);
                    visited.push(currentField);
                    //break;
                }
            
            }while (stack.getStack().length > 0)

        
           console.log('\n','Altered fielded grid:','\n');
           console.log(fieldGrid)

           return fieldGrid
        }catch(err){
            console.log(err)
        }
}


function createField(size=10, content=false){
    gesamtFeld = new Array(size).fill(0)
    
    gesamtFeld=gesamtFeld.map((_,index1) => new Array(size).fill(0).map((_,index2)=>
    {   
        if(content !== false){
            element=new Feld()

        }else{
            element=getIdFromCoord(index2,index1,size).id
        }
      
        return element}
    ))

    return gesamtFeld

}

function getNeighbours(id,size=gridNumber){

    if(id-size < 0 || id >= size*size){
        id_North = null;
    }else{
        id_North = id-size;
    }

    if(id+size >= size*size || id < 0){
        id_South = null;

    }else{
        id_South = id+size;
    }

    if (id-1 < 0 || id-1 >= size*size || id%size==0){
        id_West = null;
    }else{
        id_West = id-1;
    }
   
    if (id+1 >= size*size || id < 0 || (id+1) % size == 0){
        id_East = null;
    }else{
        id_East = id+1; 
    }

    all_neighbours={north: id_North, east: id_East, south: id_South, west: id_West}
    valid_neighbours={}

    neighbour_functions = 4
    Object.entries(all_neighbours).forEach(([key,val]) =>{
        if (val == null){
            neighbour_functions--;
        }else{
            valid_neighbours[key]=val;
        }
    })

    return {possible_neighbour_count:neighbour_functions,valid_neighbours:valid_neighbours,all_neighbours:all_neighbours}
}

function getIdFromCoord(x,y,size=gridNumber){
    return {id:y*size+x}
}

function getCoordFromId(id, size=gridNumber) {
    let x;
    let y;

    if (id < 0 || id >= size * size) {
        console.error("ID is not in grid range\n");
        return null;
    }

    y = Math.floor(id / gridNumber);
    x = id % gridNumber;

  
    return {x:x,y:y};
} 

function getRandomNeighbour(count,myObject){

    random= Math.floor(Math.random()*count)
    return Object.fromEntries([Object.entries(myObject)[random]])

}

function Test(id){

    console.log(`\nCalling the Test Function with id ${id} on grid size ${gridNumber}x${gridNumber} (MIN: ${0} MAX: ${gridNumber*gridNumber-1})\n`)
    output_01=getNeighbours(id)
    output_02=getCoordFromId(id)
    if(output_02){
        output_03=getIdFromCoord(output_02.x,output_02.y)
    }else{
        output_03=null;
    }
    if(output_01.possible_neighbour_count > 0){
        output_04=getRandomNeighbour(output_01.possible_neighbour_count,output_01.valid_neighbours)

    }else{
        output_04=null
    }

    return {getNeighboursFunction:output_01,
            getCoordFromIdFunction:output_02,
            getIdFromCoordFunction:output_03,
            getRandomNeighbourFunction:output_04
        }
}

module.exports = { gridNumber, runMazeCreate};










