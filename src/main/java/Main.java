import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import com.google.gson.Gson;

import Model.Hotel;
import Repository.Acme;
import Repository.Paperflie;
import Repository.Patagonia;
import Util.Format;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {

    // Retrieve input arguments for hotel IDs and destination IDs
    String hotelIdsInput = args[0];
    String destinationIdsInput = args[1];

    // Parse the input strings into sets of hotel IDs and destination IDs
    Set<String> hotelIds = parseInput(hotelIdsInput);
    Set<String> destinationIds = parseInput(destinationIdsInput);

    // Determine if we need to retrieve all hotels (if either set is empty)
    boolean isGetAll = destinationIds.isEmpty() || hotelIds.isEmpty();

    // Create a map to store unique hotels by their ID
    Map<String, Hotel> result = new HashMap<String, Hotel>();

    // Create a list to hold hotel data from different sources
    List<Hotel> data = new ArrayList<Hotel>();
    data.addAll(Acme.fetchData());
    data.addAll(Patagonia.fetchData());
    data.addAll(Paperflie.fetchData());

    for (Hotel item : data) {

      // Check if we need all hotels or if the current hotel matches the given IDs
      if (isGetAll || (hotelIds.contains(item.getId()) &&
          destinationIds.contains(String.valueOf(item.getDestinationId())))) {

        if (result.isEmpty() || !result.containsKey(item.getId())) {

          // Add the hotel to the result map
          result.put(item.getId(), item);
        } else {

          // Merge the current hotel data with the existing hotel data
          result.put(item.getId(), Format.merge(result.get(item.getId()), item));
        }
      }
    }

    // Print the result as JSON
    System.out.println(new Gson().toJson(new ArrayList<Hotel>(result.values())));
  }

  // Parsing the input string into a set of strings
  private static Set<String> parseInput(String input) {
    if ("none".equalsIgnoreCase(input) || input.isBlank()) {

      return Collections.emptySet();
    }

    return new HashSet<String>(Arrays.asList(input.split(",")));
  }
}