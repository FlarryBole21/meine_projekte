function htmlContent (imgSource=''){
    return `<!DOCTYPE html>
        <html>
        <head>
            <title>My Maze Project</title>
            <style>
                #gridBlock {
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }

                *{
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }
        
                body{
                    background-color: rgb(38, 42, 43);
                }
            </style>
        </head>
        <body>
            <div id='gridBlock'>
                <img src="${imgSource}" />
            </div>
        </body>
        </html>
    `;
}

module.exports = {
    htmlContent
};
