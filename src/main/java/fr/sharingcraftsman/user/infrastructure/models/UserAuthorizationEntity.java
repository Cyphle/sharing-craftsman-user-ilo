package fr.sharingcraftsman.user.infrastructure.models;

import fr.sharingcraftsman.user.domain.userauthorization.UserAuthorization;

import javax.persistence.*;

@Entity
@Table(name = "user_authorizations")
public class UserAuthorizationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;
  @Column(name = "username")
  private String username;
  @Column(name = "access_group")
  private String group;

  public UserAuthorizationEntity() {
  }

  private UserAuthorizationEntity(String username, String group) {
    this.username = username;
    this.group = group;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public static UserAuthorizationEntity from(String username, String group) {
    return new UserAuthorizationEntity(username, group);
  }

  public static UserAuthorizationEntity fromDomainToInfra(UserAuthorization authorization) {
    return new UserAuthorizationEntity(authorization.getUsername(), authorization.getGroup());
  }
}
