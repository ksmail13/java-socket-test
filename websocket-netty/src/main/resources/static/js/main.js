
var WSocketOption = function (host, port, path, secure) {
    return {host: host, port: port, path: path, secure: secure};
}

var WSocket = function (option) {
    if (WebSocket) { // use websocket if browser can
        var _send = initWebSocket(option);
        this.send = _send;
    } else { // use ajax in jquery when browser can't use websocket
        this.send = initLongPolling(option);
    }
}
function initWebSocket(option) {
    var wsClosed = false;
    var url = 'ws'+(option.secure===true?'s':'')+'://'+option.host+':'+option.port+option.path;
    var socket = new WebSocket(url);

    socket.onopen = function () {
        wsClosed = false;
        open();
    };

    socket.onclose = function () {
        wsClosed = true;
        close();
    };

    socket.addEventListener('message', function (e) {
        received(e);
    });

    return function(msg) {
        if(wsClosed === false) {
            socket.send(msg);
        } else {
            console.log('already closed');
        }
    };
}

function initLongPolling(url) {
    return function (msg) {
        var url = 'http'+(option.secure===true?'s':'')+'://'+option.host+':'+option.port+option.path;
        $.ajax({
            url: url,
            async:true,
            data: msg,
            method:'POST',
            timeout: 0
        }).done(function (data, status) {
            received({data: data, status: status});
        }).fail(function (xhr, status, error) {
            received({data: error, status: status});
        });
    };
}

function received(e) {
    console.log(e);
}

function close() {
    console.log('closed');
}

function open() {
    console.log('opened');
}