package managers;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Game;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameManager {
    private String _url;
    private final OkHttpClient httpClient;

    public GameManager(String url) {
        _url = url;
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(400, TimeUnit.SECONDS)
                .callTimeout(400, TimeUnit.SECONDS)
                .readTimeout(400, TimeUnit.SECONDS)
                .writeTimeout(400, TimeUnit.SECONDS)
                .build();
    }

    public Game trainRandom(Integer playerId) {
        Request request = new Request.Builder()
                .url(_url + "/train/random?playerId=" + playerId)

                .build();

        //Send request
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error while executing train random request" + response);

            //Read response
            String stateJson = response.body().string();

            //Deserialize
            return deserializeResponse(stateJson);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Game gamePlay(Integer playerId, Integer gameId) {
        Request request = new Request.Builder()
                .url(_url + "/game/play?playerId=" + playerId + "&gameId=" + gameId)
                .build();

        //Send request
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error while executing train random request" + response);

            //Read response
            String stateJson = response.body().string();

            //Deserialize
            return deserializeResponse(stateJson);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createGame(Integer gameId, Integer playerOne, Integer playerTwo) {
        Request request = new Request.Builder()
                .url(_url + "/admin/createGame?gameId=" + gameId + "&playerOne=" + playerOne + "&playerTwo=" + playerTwo)
                .build();

        //Send request
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error while executing train random request" + response);

            //Read response
            String stateJson = response.body().string();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game doAction(Integer playerId, Integer gameId, String action) {
        Request request = new Request.Builder()
                .url(_url + "/doAction?playerId=" + playerId + "&gameId=" + gameId + "&action=" + action)
                .build();

        //Send request
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Error while executing train random request" + response);

            //Read response
            String stateJson = response.body().string();

            //Deserialize
            return deserializeResponse(stateJson);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Game deserializeResponse(String stateJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Game game ;
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            game = objectMapper.readValue(stateJson, Game.class);
            return game;
        } catch (JsonProcessingException e) {
            throw e;
        }
    }
}
