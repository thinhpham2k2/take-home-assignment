package Model;

import java.util.Set;

public class Amenity {

  private Set<String> general;
  private Set<String> room;

  // NoArgsConstructor
  public Amenity() {
  }

  // AllArgsConstructor
  public Amenity(Set<String> general, Set<String> room) {
    this.general = general;
    this.room = room;
  }

  // Getters and Setters
  public Set<String> getGeneral() {
    return general;
  }

  public void setGeneral(Set<String> general) {
    this.general = general;
  }

  public Set<String> getRoom() {
    return room;
  }

  public void setRoom(Set<String> room) {
    this.room = room;
  }
}