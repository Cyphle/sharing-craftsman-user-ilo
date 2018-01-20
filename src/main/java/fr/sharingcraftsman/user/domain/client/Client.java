package fr.sharingcraftsman.user.domain.client;

public class Client {
  private final String name;
  private final String secret;

  public Client(String name, String secret) {
    this.name = name;
    this.secret = secret;
  }

  public static Client from(String name, String secret) {
    if (name.isEmpty() || secret.isEmpty())
      throw new InvalidClientException();

    return new Client(name, secret);
  }

  public String getSecret() {
    return secret;
  }

  public String getName() {
    return name;
  }
}
