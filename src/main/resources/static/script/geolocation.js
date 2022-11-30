var loc = document.getElementById("userLocation");

function findLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showYourLocation);
    } else {
        loc.innerHTML = "이 문장은 사용자의 웹 브라우저가 Geolocation API를 지원하지 않을 때 나타납니다!";
    }
}

function showYourLocation(position) {
    loc.innerHTML = "현재 사용자는 위도 " + position.coords.latitude + ", 경도 " + position.coords.longitude + "에 위치하고 있습니다.";
}