
var main = function (url, accessId) {
    var socket = new SockJS(url);
    var loggingDiv = document.getElementById(accessId);
    socket.onopen = function() {
        console.log("connected");
//        loggingDiv.innerHTML += 'connected</br>';
    };

    socket.onclose = function() {
        console.log("closed");
//        loggingDiv.innerHTML += 'closed</br>';
    };

    socket.onmessage = function(e) {
        console.log("receive {}", e.data);

        loggingDiv.innerHTML += e.data+'</br>';
    };

    this.send = function(msg) {
        socket.send(msg);
    }
}