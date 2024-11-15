package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonElement;

import Model.Amenity;
import Model.Hotel;
import Model.Image;
import Model.Location;
import Model.Image.Detail;

public class Format {

  // Merge two hotels
  public static Hotel merge(Hotel hotel1, Hotel hotel2) {

    // Set DestinationId
    if (hotel2.getDestinationId() != 0)
      hotel1.setDestinationId(hotel2.getDestinationId());
    // Set Name
    if (hotel2.getName() != null && !hotel2.getName().isBlank())
      hotel1.setName(hotel2.getName());
    // Set Location
    if (hotel1.getLocation() == null) {

      if (hotel2.getLocation() != null) {

        hotel1.setLocation(hotel2.getLocation());
      } else {

        hotel1.setLocation(new Location());
      }
    } else {

      // Set Latitude
      if (hotel2.getLocation().getLat() != 0)
        hotel1.getLocation().setLat(hotel2.getLocation().getLat());
      // Set Longitude
      if (hotel2.getLocation().getLng() != 0)
        hotel1.getLocation().setLng(hotel2.getLocation().getLng());
      // Set Address
      if (hotel2.getLocation().getAddress() != null && !hotel2.getLocation().getAddress().isBlank())
        hotel1.getLocation().setAddress(hotel2.getLocation().getAddress());
      // Set City
      if (hotel2.getLocation().getCity() != null && !hotel2.getLocation().getCity().isBlank())
        hotel1.getLocation().setCity(hotel2.getLocation().getCity());
      // Set Country
      if (hotel2.getLocation().getCountry() != null && !hotel2.getLocation().getCountry().isBlank())
        hotel1.getLocation().setCountry(hotel2.getLocation().getCountry());
    }
    // Set Desciption
    if (hotel2.getDescription() != null && !hotel2.getDescription().isBlank())
      hotel1.setDescription(hotel2.getDescription());
    // Set Amenities
    if (hotel1.getAmenities() == null) {

      if (hotel2.getAmenities() != null) {

        hotel1.setAmenities(hotel2.getAmenities());
      } else {

        hotel1.setAmenities(new Amenity());
      }
    } else {

      // Set General
      if (hotel2.getAmenities().getGeneral() != null && !hotel2.getAmenities().getGeneral().isEmpty())
        hotel1.getAmenities().getGeneral().addAll(hotel2.getAmenities().getGeneral());
      // Set Room
      if (hotel2.getAmenities().getRoom() != null && !hotel2.getAmenities().getRoom().isEmpty())
        hotel1.getAmenities().getRoom().addAll(hotel2.getAmenities().getRoom());
    }
    // Set Images
    if (hotel1.getImages() == null) {
      if (hotel2.getImages() != null) {

        hotel1.setImages(hotel2.getImages());
      } else {

        hotel1.setImages(new Image());
      }
    } else {

      // Set Rooms
      if (hotel2.getImages().getRooms() != null && !hotel2.getImages().getRooms().isEmpty())
        hotel1.getImages().getRooms().addAll(hotel2.getImages().getRooms());
      // Set Site
      if (hotel2.getImages().getSite() != null && !hotel2.getImages().getSite().isEmpty())
        hotel1.getImages().getSite().addAll(hotel2.getImages().getSite());
      // Set Amenities
      if (hotel2.getImages().getAmenities() != null && !hotel2.getImages().getAmenities().isEmpty())
        hotel1.getImages().getAmenities().addAll(hotel2.getImages().getAmenities());
    }
    // Set BookingConditions
    if (hotel2.getBooking_conditions() != null && !hotel2.getBooking_conditions().isEmpty())
      hotel1.setBooking_conditions(hotel2.getBooking_conditions());

    return hotel1;
  }

  // Try to parse the JsonElement into a String
  public static String tryParseString(JsonElement el) {

    try {

      final String value = el.getAsString();
      return value.trim();
    } catch (Exception e) {

      return "";
    }
  }

  // Try to parse the JsonElement into a double
  public static double tryParseDouble(JsonElement el) {

    try {

      return el.getAsDouble();
    } catch (Exception e) {

      return 0;
    }
  }

  // Try to parse the JsonElement into a int
  public static int tryParseInt(JsonElement el) {

    try {

      return el.getAsInt();
    } catch (Exception e) {

      return 0;
    }
  }

  // Try to parse the JsonElement into a Set of strings
  public static Set<String> tryParseSetString(JsonElement el) {

    try {

      Set<String> result = new HashSet<String>();

      for (JsonElement addressElement : el.getAsJsonArray()) {

        String trimmedAddress = tryParseString(addressElement);
        result.add(toLowerCaseWithSpaces(trimmedAddress));
      }

      return result;
    } catch (Exception e) {

      return Collections.emptySet();
    }
  }

  // Use regex to replace positions before capital letters with a space
  public static String toLowerCaseWithSpaces(String str) {

    // Initialize a HashSet to store vocabulary words
    Set<String> vocabulary = new HashSet<String>(Arrays.asList(
        "wifi",
        "defi",
        "mifi",
        "qr code",
        "iot",
        "hdmi",
        "usb",
        "vpn",
        "led",
        "nfc",
        "sql",
        "pdf",
        "csv",
        "sim"));

    if (vocabulary.contains(str.toLowerCase()))
      return str.toLowerCase();

    return str.replaceAll("([a-z])([A-Z])", "$1 $2")
        .replaceAll("([A-Z]+)([A-Z][a-z])", "$1 $2")
        .toLowerCase();
  }

  // Try to parse the JsonElement into a List of strings
  public static List<String> tryParseListString(JsonElement el) {

    try {

      List<String> result = new ArrayList<String>();

      for (JsonElement addressElement : el.getAsJsonArray()) {

        String trimmedAddress = tryParseString(addressElement);
        result.add(trimmedAddress);
      }

      return result;
    } catch (Exception e) {

      return Collections.emptyList();
    }
  }

  // Try to parse the JsonElement into a Set of details for Paperflie
  public static Set<Detail> tryParseSetDetailPaperflie(JsonElement el) {

    try {

      Set<Detail> result = new HashSet<Detail>();

      for (JsonElement detailElement : el.getAsJsonArray()) {

        // Set Detail
        Detail detail = new Image().new Detail();
        detail.setLink(tryParseString(detailElement.getAsJsonObject().get("link")));
        detail.setDescription(tryParseString(detailElement.getAsJsonObject().get("caption")));
        result.add(detail);
      }

      return result;
    } catch (Exception e) {

      return Collections.emptySet();
    }
  }

  // Try to parse the JsonElement into a Set of details for Patagonia
  public static Set<Detail> tryParseSetDetailPatagonia(JsonElement el) {

    try {

      Set<Detail> result = new HashSet<Detail>();

      for (JsonElement detailElement : el.getAsJsonArray()) {

        // Set Detail
        Detail detail = new Image().new Detail();
        detail.setLink(tryParseString(detailElement.getAsJsonObject().get("url")));
        detail.setDescription(tryParseString(detailElement.getAsJsonObject().get("description")));
        result.add(detail);
      }

      return result;
    } catch (Exception e) {

      return Collections.emptySet();
    }
  }
}