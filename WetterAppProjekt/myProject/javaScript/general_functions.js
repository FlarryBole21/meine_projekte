//Generelle Funktionen, die nicht sinnvoll gruppiert werden konnten

//Gibt Fehlermeldungen zurück
function errorMessage(data){
    try{
        throw new Error(data);     
    }
    catch(err){
        console.log(err);
    }
};

//Gibt eine Liste mit Objekten zurück. Diese beinhalten Farbwertpaarungen --> Wird für den Canvas später verwendet
function randomColors(){
    return [
        { backgroundColor: 'rgba(75, 192, 192, 0.2)', borderColor: 'rgba(75, 122, 132, 1)' },
        { backgroundColor: 'rgba(255, 99, 132, 0.2)', borderColor: 'rgba(255, 99, 132, 1)' },
        { backgroundColor: 'rgba(54, 162, 235, 0.2)', borderColor: 'rgba(54, 162, 235, 1)' },
        { backgroundColor: 'rgba(153, 102, 255, 0.2)', borderColor: 'rgba(153, 102, 255, 1)' },
        { backgroundColor: 'rgba(255, 159, 64, 0.2)', borderColor: 'rgba(255, 159, 64, 1)' },
        { backgroundColor: 'rgba(128, 0, 0, 0.2)', borderColor: 'rgba(128, 0, 0, 1)' },
        { backgroundColor: 'rgba(0, 128, 0, 0.2)', borderColor: 'rgba(0, 128, 0, 1)' },
        { backgroundColor: 'rgba(0, 0, 128, 0.2)', borderColor: 'rgba(0, 0, 128, 1)' },
        { backgroundColor: 'rgba(255, 140, 0, 0.2)', borderColor: 'rgba(255, 140, 0, 1)' },
        { backgroundColor: 'rgba(220, 20, 60, 0.2)', borderColor: 'rgba(220, 20, 60, 1)' },
        { backgroundColor: 'rgba(0, 255, 255, 0.2)', borderColor: 'rgba(0, 255, 255, 1)' },
        { backgroundColor: 'rgba(255, 0, 255, 0.2)', borderColor: 'rgba(255, 0, 255, 1)' },
        { backgroundColor: 'rgba(255, 255, 0, 0.2)', borderColor: 'rgba(255, 255, 0, 1)' },
        { backgroundColor: 'rgba(128, 128, 0, 0.2)', borderColor: 'rgba(128, 128, 0, 1)' },
        { backgroundColor: 'rgba(0, 128, 128, 0.2)', borderColor: 'rgba(0, 128, 128, 1)' },
        { backgroundColor: 'rgba(128, 0, 128, 0.2)', borderColor: 'rgba(128, 0, 128, 1)' },
        { backgroundColor: 'rgba(255, 182, 193, 0.2)', borderColor: 'rgba(255, 182, 193, 1)' },
        { backgroundColor: 'rgba(0, 0, 0, 0.2)', borderColor: 'rgba(0, 0, 0, 1)' },
        { backgroundColor: 'rgba(128, 0, 128, 0.2)', borderColor: 'rgba(128, 0, 128, 1)' },
        { backgroundColor: 'rgba(0, 128, 0, 0.2)', borderColor: 'rgba(0, 128, 0, 1)' },
        { backgroundColor: 'rgba(0, 0, 128, 0.2)', borderColor: 'rgba(0, 0, 128, 1)' },
        { backgroundColor: 'rgba(255, 140, 0, 0.2)', borderColor: 'rgba(255, 140, 0, 1)' },
        { backgroundColor: 'rgba(128, 128, 128, 0.2)', borderColor: 'rgba(128, 128, 128, 1)' },
        { backgroundColor: 'rgba(70, 130, 180, 0.2)', borderColor: 'rgba(70, 130, 180, 1)' },
        { backgroundColor: 'rgba(255, 20, 147, 0.2)', borderColor: 'rgba(255, 20, 147, 1)' },
        { backgroundColor: 'rgba(173, 216, 230, 0.2)', borderColor: 'rgba(173, 216, 230, 1)' },
        { backgroundColor: 'rgba(255, 215, 0, 0.2)', borderColor: 'rgba(255, 215, 0, 1)' },
        { backgroundColor: 'rgba(255, 0, 0, 0.2)', borderColor: 'rgba(255, 0, 0, 1)' },
        { backgroundColor: 'rgba(0, 255, 0, 0.2)', borderColor: 'rgba(0, 255, 0, 1)' },
        { backgroundColor: 'rgba(0, 0, 255, 0.2)', borderColor: 'rgba(0, 0, 255, 1)' },
        { backgroundColor: 'rgba(255, 69, 0, 0.2)', borderColor: 'rgba(255, 69, 0, 1)' },
        { backgroundColor: 'rgba(139, 69, 19, 0.2)', borderColor: 'rgba(139, 69, 19, 1)' },
        { backgroundColor: 'rgba(255, 228, 196, 0.2)', borderColor: 'rgba(255, 228, 196, 1)' },
        { backgroundColor: 'rgba(240, 230, 140, 0.2)', borderColor: 'rgba(240, 230, 140, 1)' },
        { backgroundColor: 'rgba(0, 139, 139, 0.2)', borderColor: 'rgba(0, 139, 139, 1)' },
        { backgroundColor: 'rgba(128, 0, 0, 0.2)', borderColor: 'rgba(128, 0, 0, 1)' }
    ];
}
