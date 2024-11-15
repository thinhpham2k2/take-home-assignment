package Model;

import java.util.List;

public class Hotel {

  private String id;
  private int destination_id;
  private String name;
  private Location location;
  private String description;
  private Amenity amenities;
  private Image images;
  private List<String> booking_conditions;

  // NoArgsConstructor
  public Hotel() {
  }

  // AllArgsConstructor
  public Hotel(String id, int destination_id, String name, String description, Location location,
      Amenity amenities, Image images, List<String> booking_conditions) {
    this.id = id;
    this.destination_id = destination_id;
    this.name = name;
    this.description = description;
    this.location = location;
    this.amenities = amenities;
    this.images = images;
    this.booking_conditions = booking_conditions;
  }

  // Getters and Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getDestinationId() {
    return destination_id;
  }

  public void setDestinationId(int destination_id) {
    this.destination_id = destination_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Amenity getAmenities() {
    return amenities;
  }

  public void setAmenities(Amenity amenities) {
    this.amenities = amenities;
  }

  public Image getImages() {
    return images;
  }

  public void setImages(Image images) {
    this.images = images;
  }

  public List<String> getBooking_conditions() {
    return booking_conditions;
  }

  public void setBooking_conditions(List<String> booking_conditions) {
    this.booking_conditions = booking_conditions;
  }
}