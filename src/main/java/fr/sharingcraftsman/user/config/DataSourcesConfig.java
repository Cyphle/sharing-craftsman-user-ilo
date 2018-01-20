package fr.sharingcraftsman.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourcesConfig {
  @Value("${data.user}")
  private String dataUserPath;

  @Value("${data.authotization}")
  private String authotizationPath;

  @Value("${data.user-authorization}")
  private String userAuthorizationPath;

  @Value("${data.client}")
  private String clientPath;

  public String getDataUserPath() {
    return dataUserPath;
  }

  public String getAuthotizationPath() {
    return authotizationPath;
  }

  public String getUserAuthorizationPath() {
    return userAuthorizationPath;
  }

  public String getClientPath() {
    return clientPath;
  }
}
