package Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Config.PatagoniaDeserializer;

import java.util.ArrayList;

import Model.Hotel;

public class Patagonia {

  private static final String URL = "https://5f2be0b4ffc88500167b85a0.mockapi.io/suppliers/patagonia";

  public static List<Hotel> fetchData() {

    List<Hotel> result = new ArrayList<Hotel>();

    // Build an HTTP GET request to the specified URL
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URL))
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();

    // Initialize the response variable to null
    HttpResponse<String> response = null;
    try {

      // Send the request and receive the response
      response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

      // Create a Gson object with a custom deserializer for the Hotel class
      Gson gson = new GsonBuilder()
          .registerTypeAdapter(Hotel.class, new PatagoniaDeserializer())
          .create();

      // Parse the JSON response into a list of Hotel objects using Gson
      result = gson.fromJson(response.body(), new TypeToken<ArrayList<Hotel>>() {
      }.getType());
    } catch (IOException e) {

      e.printStackTrace();
    } catch (InterruptedException e) {

      e.printStackTrace();
    }

    return result;
  }
}