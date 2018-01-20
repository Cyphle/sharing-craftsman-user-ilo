package fr.sharingcraftsman.user.domain.user;

public class Username {
  private String username;

  public Username(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public static Username from(String username) throws UsernameException {
    if (username.isEmpty())
      throw new UsernameException("Username cannot be empty");

    return new Username(username);
  }
}
