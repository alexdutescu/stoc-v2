//This code is not to be used by any third party who is not a member of DoubleA team.
//var host = "ws://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":" +
//                (document.location.port == "" ? "8080" : document.location.port);

var host = "ws://5.14.71.116:8080/Management/notificare"; // nu e capabil sa obtina ipul extern, glassfish e setat pe localhost, deci trebuie bagat ipul manual
var wSocket = new WebSocket(host);
var browserSupport = ("WebSocket" in window) ? true : false;

// called body onLoad()
function initWebSocket()
{
    if (browserSupport)
    {
        wSocket.onopen = function()
        {
            //alert(" Web Socket is connected, sending data");
            wSocket.send("ping");
        };
    }
    else
    {
        // The browser doesn't support WebSocket
        alert("WebSocket is NOT supported by your Browser!");
    }
}

// called when a message is received
wSocket.onmessage = function(event)
{
    var received_msg = event.data;
//        alert('server msg:' + received_msg);
    alertWidget.renderMessage({summary: 'Comanda noua', detail: received_msg, severity: 0});
    document.getElementById('mesaj_trimis').innerHTML = received_msg;
};

// called when socket closes
wSocket.onclose = function()
{
    // websocket is closed.
    //alert("Connection is closed...");
};
function fadeoutUser() {
// setTimeout(function(){
//    $('[id$=errorUser]').blink();
// },2000);
    setTimeout(function() {
        $('[id$=errorUser]').removeAttr("style").hide().fadeIn();
    }, 1000);
    
    setTimeout(function(){
    $('[id$=errorUser]').fadeOut();
 },2000);
}

