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

public class PaperflieDeserializer implements JsonDeserializer<Hotel> {

    public Hotel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Hotel hotel = new Hotel();

        // Set Id
        hotel.setId(Format.tryParseString(jsonObject.get("hotel_id")));
        // Set DestinationId
        hotel.setDestinationId(Format.tryParseInt(jsonObject.get("destination_id")));
        // Set Name
        hotel.setName(Format.tryParseString(jsonObject.get("hotel_name")));
        // Set Location
        Location location = new Location();
        location.setAddress(Format.tryParseString(jsonObject.getAsJsonObject("location").get("address")));
        location.setCountry(Format.tryParseString(jsonObject.getAsJsonObject("location").get("country")));
        hotel.setLocation(location);
        // Set Description
        hotel.setDescription(Format.tryParseString(jsonObject.get("details")));
        // Set Amenities
        Amenity amenity = new Amenity();
        amenity.setGeneral(Format.tryParseSetString(jsonObject.getAsJsonObject("amenities").get("general")));
        amenity.setRoom(Format.tryParseSetString(jsonObject.getAsJsonObject("amenities").get("room")));
        hotel.setAmenities(amenity);
        // Set Images
        Image image = new Image();
        image.setRooms(Format.tryParseSetDetailPaperflie(jsonObject.getAsJsonObject("images").get("rooms")));
        image.setSite(Format.tryParseSetDetailPaperflie(jsonObject.getAsJsonObject("images").get("site")));
        image.setAmenities(new HashSet<Detail>());
        hotel.setImages(image);
        // Set BookingConditions
        hotel.setBooking_conditions(Format.tryParseListString(jsonObject.get("booking_conditions")));

        return hotel;
    }
}