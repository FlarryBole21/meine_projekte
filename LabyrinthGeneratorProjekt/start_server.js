const http = require('http');
const { runMazeCreate } = require('./maze'); 


//const fs = require('fs');

const PORT = 8000;

//const filePath = './index.html'; 

function generateHTML(data) {
    const { htmlContent } = require('./html');
    const { mazeCanvas} = require('./canvas');

    const canvasDataURL = mazeCanvas(800,800,'white',data);

    const content=htmlContent(canvasDataURL)
  
    return content;
}

function toReadableStream(element){
    return require('stream').Readable.from(element)
}

function serverRun(data){
   
    //let serverStarted = false;

    const server = http.createServer((req, res) => {

        if (req.url === '/favicon.ico') {
            res.writeHead(404);
            res.end();
            return;
        }

        data= runMazeCreate()

        
        res.writeHead(200, { 'Content-Type': 'text/html' });
        const generatedHTML = generateHTML(data); 
        const readableStream = toReadableStream(generatedHTML);
        readableStream.pipe(res);
        //serverStarted = true;
        

        //res.end(generatedHTML); 
    });
    
    server.listen(PORT, () => {
        console.log('Server läuft auf http://localhost:8000');
       
    });

}

serverRun();

module.exports = { serverRun };
