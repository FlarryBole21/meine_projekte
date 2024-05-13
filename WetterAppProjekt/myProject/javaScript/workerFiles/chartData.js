//zur Konvetierung der Daten in ein Form, mit welcher die chart-Funktion arbeiten kann
onmessage = (event) => {
    if(event.data.status === 'convertDataToChartData'){
        dailyData=event.data.data.chartData;
        colors=event.data.data.colors;
        chartData=[];
        Object.entries(dailyData).forEach(([key,value])=>{
            if(key != 'time'){
                chartData.push({[key]:value});
            }
        })

        chartMappedData = Object.values(chartData).map((element)=>{
            //Auswahl der zufälligen farben für die Graphen
            let randomIndex = Math.floor(Math.random() * colors.length);
            backgroundColorValue=colors[randomIndex].backgroundColor;
            borderColorValue=colors[randomIndex].borderColor;
            return {
                label:Object.keys(element)[0],
                data:Object.values(element)[0],
                
                backgroundColor: backgroundColorValue,
                borderColor: borderColorValue,
                borderWidth: 1
            };
        })
        let wetterDaten = {
        labels: dailyData.time,
        datasets: chartMappedData
        };
        postMessage({status:'convertDataToChartData',data:wetterDaten});
    }
};