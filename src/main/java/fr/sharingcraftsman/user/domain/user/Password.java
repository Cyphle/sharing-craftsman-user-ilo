package fr.sharingcraftsman.user.domain.user;

import fr.sharingcraftsman.user.domain.utils.Crypter;
import fr.sharingcraftsman.user.domain.utils.CrypterFactory;

public class Password {
  public static Crypter crypter = CrypterFactory.getCrypter();

  private String password;

  private Password(String password) {
    this.password = password;
  }

  public Password getEncryptedVersion() {
    return new Password(crypter.encrypt(password));
  }

  public String getPassword() {
    return password;
  }

  public static Password from(String password) throws PasswordException {
    if (password.isEmpty())
      throw new PasswordException("Password cannot be empty");

    return new Password(password);
  }
}
