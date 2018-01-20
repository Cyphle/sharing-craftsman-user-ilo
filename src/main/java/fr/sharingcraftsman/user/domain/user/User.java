package fr.sharingcraftsman.user.domain.user;

public class User {
  private Username username;
  private Password password;

  private User(Username username) {
    this.username = username;
  }

  private User(Username username, Password password) {
    this.username = username;
    this.password = password;
  }

  public Username getUsername() {
    return username;
  }

  public String getUsernameContent() {
    return username.getUsername();
  }

  public Password getPassword() {
    return password;
  }

  public String getPasswordContent() {
    return password.getPassword();
  }

  public void setPassword(Password password) {
    this.password = password;
  }

  public void encrypt() {
    password = password.getEncryptedVersion();
  }

  public static User from(String username, String password) {
    return new User(Username.from(username), Password.from(password));
  }
}
