package fr.sharingcraftsman.user.domain.userauthorization;

import fr.sharingcraftsman.user.domain.authorization.Groups;
import fr.sharingcraftsman.user.domain.user.Username;

import java.util.Arrays;

public class UserAuthorization {
  private final Username username;
  private final String group;

  public UserAuthorization(Username username, String group) {
    this.username = username;
    this.group = group;
  }

  public String getUsername() {
    return username.getUsername();
  }

  public String getGroup() {
    return group;
  }

  public static UserAuthorization from(String username, String group) {
    boolean isGroupValid = Arrays.stream(Groups.values())
            .anyMatch(validGroup -> validGroup.name().equals(group));

    if (!isGroupValid)
      throw new InvalidUserAuthorizationException();

    return new UserAuthorization(Username.from(username), group);
  }
}
