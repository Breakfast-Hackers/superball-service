document.addEventListener("DOMContentLoaded", function(event) {
	var socket = new SockJS('/superball-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
	    console.log('Connected: ' + frame);
	    stompClient.subscribe('/topic/movements', function (message) {
	    	const movement = JSON.parse(message.body)
	        $('#movementsContainer').append('<div><span>' + movement.action + '</span></div>');
	    });
	    stompClient.subscribe('/topic/commands', function (message) {
	    	const command = JSON.parse(message.body)
	        $('#commandsContainer').append('<div><span>' + command.action + '</span></div>');
	    });
	});
});
