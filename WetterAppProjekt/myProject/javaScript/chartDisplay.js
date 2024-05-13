//Erstellung eines Canvas & Formatierung der CSS 
function chartDatenAnzeigen(wetterDaten,selectId,backgroundColorValue,borderColorValue,borderRadiusValue,widthValue,heightValue,marginTopValue,chartTypeValue){
    let div=document.createElement('div');
    let ctx=document.createElement('canvas');
    const subBlock=document.getElementById(selectId);
    ctx.style.backgroundColor=backgroundColorValue;
    ctx.style.border=borderColorValue;
    ctx.style.borderRadius=borderRadiusValue;
    div.style.width=widthValue;
    div.style.height=heightValue;
    div.style.marginTop=marginTopValue;
    div.appendChild(ctx);
    subBlock.appendChild(div);
    subBlock.style.marginTop='-20rem';
    ctx.getContext('2d');
    chartErzeugen(ctx,wetterDaten,chartTypeValue,'Daily');
}

//Entfernung des Canvas
function chartDatenEntfernen(selectId){
    const subBlock=document.getElementById(selectId);
    subBlock.style.marginTop='0rem';
    subBlockObject=new BlockObject(selectId,subBlock);
    subBlockObject.childRemoval(2);
}

//Erstellung des Canvas mit den Daten, die es erhalten hat
function chartErzeugen(chartElement,wetterDaten,typ,header,color1='rgba(255, 255, 255)',color2='rgba(255, 255, 255, 0.2)'){
    new Chart(chartElement, {
        type: typ,
        data: wetterDaten,
        options: {
            scales: {
                x: {
                    ticks: {
                        color: color1, 
                    },
                    grid: {
                        color: 'rgba(255, 255, 255, 0.2)'
                    },
                },
                y: {
                    beginAtZero: true,
                    ticks: {
                        color: color1, 
                    },
                    grid: {
                        color: 'rgba(255, 255, 255, 0.2)'
                    },
                },
            },
            plugins: {
                title: {
                    text: header,
                    display: true,
                    color: color1, 
                },
                legend: {
                    labels: {
                        color: color1,
                    },
                },
            },
        },
    });

}



