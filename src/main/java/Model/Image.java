package Model;

import java.util.Objects;
import java.util.Set;

public class Image {

  private Set<Detail> rooms;
  private Set<Detail> site;
  private Set<Detail> amenities;

  // NoArgsConstructor
  public Image() {
  }

  // AllArgsConstructor
  public Image(Set<Detail> rooms, Set<Detail> site, Set<Detail> amenities) {
    this.rooms = rooms;
    this.site = site;
    this.amenities = amenities;
  }

  // Getters and Setters
  public Set<Detail> getRooms() {
    return rooms;
  }

  public void setRooms(Set<Detail> rooms) {
    this.rooms = rooms;
  }

  public Set<Detail> getSite() {
    return site;
  }

  public void setSite(Set<Detail> site) {
    this.site = site;
  }

  public Set<Detail> getAmenities() {
    return amenities;
  }

  public void setAmenities(Set<Detail> amenities) {
    this.amenities = amenities;
  }

  public class Detail {

    private String link;
    private String description;

    // NoArgsConstructor
    public Detail() {
    }

    // AllArgsConstructor
    public Detail(String link, String description) {
      this.link = link;
      this.description = description;
    }

    // Getters and Setters
    public String getLink() {
      return link;
    }

    public void setLink(String link) {
      this.link = link;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof Detail))
        return false;
      Detail detail = (Detail) o;
      return Objects.equals(link, detail.getLink()) &&
          Objects.equals(description, detail.getDescription());
    }

    @Override
    public int hashCode() {
      return Objects.hash(link, description);
    }
  }
}