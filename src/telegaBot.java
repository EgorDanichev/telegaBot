import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendVenue;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static java.lang.System.out;


public class telegaBot extends TelegramLongPollingBot {

    public static void main(String[] args) throws FileNotFoundException {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new telegaBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "@Advice_It_Bot";
    }

    @Override
    public String getBotToken() {
        return "365169818:AAGeBJ3pLtkdgPNys0MyRwvu-t-hE8jmtxE";
    }


    @Override
    public void onUpdateReceived(Update update) {
        city Moscow = new city();
        Message message = update.getMessage();

        subway MoscowSubway = new subway();

        if (message != null && message.hasText()) {

            String firstName = message.getFrom().getFirstName();

                if ( (message.getText().equals("/help"))||( message.getText().equals("/start")) ){
                    sendMsg(message, "Привет, "+ firstName+ "! Просто напиши мне название станции и я расскажу что там есть. ");
                } else {
                    if (MoscowSubway.isStation(message.getText())) {
                        try {
                            sendPlaces(message);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    } else {
                        sendMsg(message, "Я не знаю такую станцию метро!");
                    }
                }

            }
        }

private  void whatToAnswer(){

    }



private void sendPlaces(Message message) throws FileNotFoundException {

    city Moscow = new city();
    ArrayList<place> places = Moscow.getPlacesList(message.getText());


        if (!places.isEmpty()){
            for (int i=0;i<places.size();i++) {
              sendMsg(message,"*"+places.get(i).name+"*\n"+places.get(i).notes);
              sendMap(message,places.get(i));

            }
        }
    }



private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



private void sendMap(Message message, place place) {
        SendVenue sendVenue = new SendVenue();
        sendVenue.setLatitude(place.latitude);
        sendVenue.setLongitude(place.longitude);
        sendVenue.setTitle(place.name);
        sendVenue.setChatId(message.getChatId().toString());
        sendVenue.setAddress("");
        try {
            sendVenue(sendVenue);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}