package Config;

import com.google.gson.*;

import Model.Amenity;
import Model.Hotel;
import Model.Location;
import Util.Format;

import java.lang.reflect.Type;
import java.util.HashSet;

public class AcmeDeserializer implements JsonDeserializer<Hotel> {

    public Hotel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Hotel hotel = new Hotel();

        // Set Id
        hotel.setId(Format.tryParseString(jsonObject.get("Id")));
        // Set DestinationId
        hotel.setDestinationId(Format.tryParseInt(jsonObject.get("DestinationId")));
        // Set Name
        hotel.setName(Format.tryParseString(jsonObject.get("Name")));
        // Set Location
        Location location = new Location();
        location.setLat(Format.tryParseDouble(jsonObject.get("Latitude")));
        location.setLng(Format.tryParseDouble(jsonObject.get("Longitude")));
        location.setAddress(Format.tryParseString(jsonObject.get("Address")));
        location.setCity(Format.tryParseString(jsonObject.get("City")));
        location.setCountry(Format.tryParseString(jsonObject.get("Country")));
        hotel.setLocation(location);
        // Set Description
        hotel.setDescription(Format.tryParseString(jsonObject.get("Description")));
        // Set Amenities
        Amenity amenity = new Amenity();
        amenity.setGeneral(Format.tryParseSetString(jsonObject.get("Facilities")));
        amenity.setRoom(new HashSet<String>());
        hotel.setAmenities(amenity);

        return hotel;
    }
}