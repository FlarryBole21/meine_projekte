const { createCanvas } = require('canvas'); 

function mazeCanvas(width,height,backgroundcolor,data){
    const {gridNumber} = require('./maze');
    const canvas = createCanvas(width,height); 
    const context = canvas.getContext('2d');
    context.fillStyle = backgroundcolor;
    context.fillRect(0, 0, canvas.width, canvas.height);
    //context.fillStyle = 'cyan';
    //context.fillRect(10, 10, 100, 100);

    /* data.forEach(element=>{
        console.log('+++++++++++++++++++++++++++++++++++++')
        element.forEach(fieldClass=>{
            console.log('-------------------------------------------------------')
            console.log(fieldClass.getValidDirections())
            console.log('-------------------------------------------------------')

        })
        console.log('+++++++++++++++++++++++++++++++++++++')
    }) */

    let myWidth = canvas.width;
    let myHeight = canvas.height;
    stepWidth=Math.floor(myWidth/gridNumber);
    stepHeight=Math.floor(myHeight/gridNumber);
    
    ctx=canvas.getContext('2d');
    ctx.strokeStyle = 'white';
    ctx.lineWidth = 2;

    /* ctx.beginPath();
    ctx.strokeStyle = 'white';
    ctx.lineWidth = 2;
    j=stepWidth;
    for(i=0;i<=myWidth;i+=stepWidth){
        for(k=0;k<myHeight;k=k+stepHeight){
            if (i != 0 && k != 0){
                drawLine(ctx,i,k,j,k);
            }
        }
        j=i;
    }
    ctx.stroke();
    ctx.closePath();

    ctx.beginPath();
    ctx.strokeStyle = 'white';
    ctx.lineWidth = 2;
    j=stepHeight;
    for(i=0;i<=myHeight;i+=stepHeight){
        for(k=0;k<myWidth;k=k+stepWidth){
            if (i != 0 && k != 0){
                drawLine(ctx,k,i,k,j);
            }
        }
        j=i;
    }
    ctx.stroke();
    ctx.closePath(); */

    cellSize=Math.floor(myHeight/gridNumber);

    function drawMaze() {
        //ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.strokeStyle = 'black';
        ctx.lineWidth = 2;

        data.forEach((row, index1) => {
            row.forEach((cell, index2) => {
             
                if (cell.north==null) {
                    ctx.beginPath();
                    ctx.moveTo(index2 * cellSize, index1 * cellSize);
                    ctx.lineTo((index2 + 1) * cellSize, index1 * cellSize);
                    ctx.stroke();
                }
          
                if (cell.east==null) {
                    ctx.beginPath();
                    ctx.moveTo((index2 + 1) * cellSize, index1 * cellSize);
                    ctx.lineTo((index2 + 1) * cellSize, (index1 + 1) * cellSize);
                    ctx.stroke();
                }
             
                if (cell.south==null) {
                    ctx.beginPath();
                    ctx.moveTo(index2 * cellSize, (index1 + 1) * cellSize);
                    ctx.lineTo((index2 + 1) * cellSize, (index1 + 1) * cellSize);
                    ctx.stroke();
                }
            
                if (cell.west==null) {
                    ctx.beginPath();
                    ctx.moveTo(index2 * cellSize, index1 * cellSize);
                    ctx.lineTo(index2 * cellSize, (index1 + 1) * cellSize);
                    ctx.stroke();
                }
            });
        });
    }

    drawMaze();

    return canvas.toDataURL();
}


module.exports = { mazeCanvas };