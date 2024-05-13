




<?php
// Check if data exists
if (file_exists('audio/audio_1.mp3')) {
  // Data exists, execute the script
  // Your PHP code here
  echo 'KIARA';
} else {
  // Data doesn't exist, display an error message
  echo 'Error: Data not found';
}


$id_song = 1;

if (file_exists('audio/audio_'.$id_song.'.mp3')){

  echo 'KIARA';

}
?>
