
var audio1 = document.getElementById("audio1");
var audio2 = document.getElementById("audio2");
var audio3 = document.getElementById("audio3");
var audio4 = document.getElementById("audio4");
var audio5 = document.getElementById("audio5");
var audio6 = document.getElementById("audio6");

audio1.addEventListener("ended", function() {
  audio2.play();
});

audio2.addEventListener("ended", function() {
  audio3.play();
});

audio3.addEventListener("ended", function() {
  audio4.play();
});

audio4.addEventListener("ended", function() {
  audio5.play();
});

audio5.addEventListener("ended", function() {
  audio6.play();
});

audio6.addEventListener("ended", function() {
  audio1.play();
});

// audio1.play(); // Start playing the first audio file






function openMusicLibrary() {
  var elements = document.getElementsByClassName("meinUmschalter");

  for (var i = 0; i < elements.length; i++) {
    elements[i].controls = !elements[i].controls;
    
    
  }

  var elements = document.getElementsByClassName("cover4");

  div.cover4 = div.cover5
 

}








      




// function openMusicLibrary() {
//     var audio = document.getElementsByClassName("meinUmschalter");
//     if (!audio.paused) {
//       var xhttp = new XMLHttpRequest();
//       xhttp.onreadystatechange = function() {
//           if (this.readyState == 4 && this.status == 200) {
//           // Antwort des Servers verarbeiten
//           document.write(this.responseText);
//           }
//       };
//       document.body.innerHTML = "";
//       xhttp.open('GET', 'playlist_01_Normal.php?befehl2=mein_befehl', true);
//       xhttp.send();

      
//     }
// }

// function openMusicLibrary() {
//       window.open('audio.php', '_blank');
// }


function playTrack(track) {
    var audioPlayer = document.getElementById('audioPlayer');
    var audioSource = document.getElementById('audioSource');
    audioSource.src = track;
    audioPlayer.load();
    audioPlayer.play();
}

function mechanismusAusloesen() {
// Hier kommt der Code für den Mechanismus hin
// ...

// AJAX-Anfrage an den Server senden
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    // Antwort des Servers verarbeiten
    document.write(this.responseText);
  }
};
document.body.innerHTML = "";
xhttp.open('GET', 'playlist_01_Normal.php?befehl=mein_befehl', true);
xhttp.send();
}
