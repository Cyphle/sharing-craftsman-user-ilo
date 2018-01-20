package fr.sharingcraftsman.user.domain.authorization;

import java.util.Arrays;

public class Authorization {
  private String group;
  private String role;

  public Authorization(String group, String role) {
    this.group = group;
    this.role = role;
  }

  public String getGroup() {
    return group;
  }

  public String getRole() {
    return role;
  }

  public static Authorization from(String group, String role) {
    boolean isGroupValid = Arrays.stream(Groups.values())
            .anyMatch(validGroup -> validGroup.name().equals(group));

    if (!isGroupValid)
      throw new InvalidGroupException();

    return new Authorization(group, role);
  }
}
