package Model;

public class Location {

  private double lat;
  private double lng;
  private String address;
  private String city;
  private String country;

  // NoArgsConstructor
  public Location() {
  }

  // AllArgsConstructor
  public Location(double lat, double lng, String address, String city, String country) {
    this.lat = lat;
    this.lng = lng;
    this.address = address;
    this.city = city;
    this.country = country;
  }

  // Getters and Setters
  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}