package breakfast.hackers.superballservice;

import org.springframework.stereotype.Service;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;

@Service
public class SuperballSpeechlet implements Speechlet {

    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {    
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        return null;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {

        Intent intent = request.getIntent();
        if (intent == null)
            throw new SpeechletException("Unrecognized intent");

        String intentName = intent.getName();       

        if ( intentName.equals("TerriblyInterestingIntent") ) {

            String speechText = "Hello, World.  I am a Spring Boot custom skill.";

            SimpleCard card = new SimpleCard();
            card.setTitle("Hello World");
            card.setContent(speechText);

            PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
            speech.setText(speechText);

            SpeechletResponse response = SpeechletResponse.newTellResponse(speech, card);
            return response;
        }
        else {
            throw new SpeechletException("I don't understand that intent.");
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

    }
}