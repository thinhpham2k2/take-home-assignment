package Config;

import com.google.gson.*;

import Model.Amenity;
import Model.Hotel;
import Model.Location;
import Model.Image.Detail;
import Model.Image;
import Util.Format;

import java.lang.reflect.Type;
import java.util.HashSet;

public class PatagoniaDeserializer implements JsonDeserializer<Hotel> {

    public Hotel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Hotel hotel = new Hotel();

        // Set Id
        hotel.setId(Format.tryParseString(jsonObject.get("id")));
        // Set DestinationId
        hotel.setDestinationId(Format.tryParseInt(jsonObject.get("destination")));
        // Set Name
        hotel.setName(Format.tryParseString(jsonObject.get("name")));
        // Set Location
        Location location = new Location();
        location.setLat(Format.tryParseDouble(jsonObject.get("lat")));
        location.setLng(Format.tryParseDouble(jsonObject.get("lng")));
        location.setAddress(Format.tryParseString(jsonObject.get("address")));
        hotel.setLocation(location);
        // Set Description
        hotel.setDescription(Format.tryParseString(jsonObject.get("info")));
        // Set Amenities
        Amenity amenity = new Amenity();
        amenity.setGeneral(new HashSet<String>());
        amenity.setRoom(Format.tryParseSetString(jsonObject.get("amenities")));
        hotel.setAmenities(amenity);
        // Set Images
        Image image = new Image();
        image.setRooms(Format.tryParseSetDetailPatagonia(jsonObject.getAsJsonObject("images").get("rooms")));
        image.setSite(new HashSet<Detail>());
        image.setAmenities(Format.tryParseSetDetailPatagonia(
                jsonObject.getAsJsonObject("images").get("amenities")));
        hotel.setImages(image);

        return hotel;
    }
}