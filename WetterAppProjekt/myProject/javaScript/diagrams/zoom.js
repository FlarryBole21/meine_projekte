//Sorgt dafür, dass das bild größer oder kleiner wird
function zoomIn(event) {
    const imageContainer = document.querySelector('#subBlock');
    const zoomableImage = document.querySelector('.zoomable-image');

    // Mausposition relativ zum Bildcontainer berechnen
    const x = event.pageX - imageContainer.offsetLeft;
    const y = event.pageY - imageContainer.offsetTop;

    // Mausposition als Prozentsatz der Bildgröße berechnen
    const xPercent = 500;
    const yPercent = 500;

    // Bild vergrößern und die Mausposition zentrieren
    zoomableImage.style.transformOrigin = `${xPercent}% ${yPercent}%`;
    imageContainer.classList.add('image-enlarged');
}

function zoomOut() {
    const imageContainer = document.querySelector('#subBlock');
    imageContainer.classList.remove('image-enlarged');
}
