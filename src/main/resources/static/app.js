document.addEventListener("DOMContentLoaded", function(event) {
	var socket = new SockJS('/superball-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
	    console.log('Connected: ' + frame);
	    stompClient.subscribe('/topic/movements', function (message) {
	    	const movement = JSON.parse(message.body)
	        $('#websocketContainer').append('<div><span>' + movement.direction + '</span></div>');
	    });
	});
});
