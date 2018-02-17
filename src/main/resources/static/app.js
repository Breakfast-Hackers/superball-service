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
	    
	    stompClient.subscribe('/topic/obstacles', function (message) {
	    	const obstacle = JSON.parse(message.body)
	        $('#obstaclesContainer').append('<div style="background-color:' + obstacle.color + '"><span>' + obstacle.position + '</span></div>');
	    });
	    
	    stompClient.subscribe('/topic/duration', function (message) {
	    	const duration = JSON.parse(message.body)
	        $('#durationContainer').html('<div>' + duration.duration + '</div>');
	    });
	    
	    stompClient.subscribe('/topic/highscore', function (message) {
	    	const highscore = JSON.parse(message.body)
	    	$('#highscoreContainer').html('');
	    	highscore.scores.forEach(score => $('#highscoreContainer').append('<div>' + score + '</div>'));
	    });
	});
});
