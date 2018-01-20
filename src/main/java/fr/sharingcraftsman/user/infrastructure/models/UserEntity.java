package fr.sharingcraftsman.user.infrastructure.models;


import fr.sharingcraftsman.user.domain.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;
  @Column(name = "username", unique = true)
  private String username;
  @Column(name = "lastname")
  private String lastname;
  @Column(name = "firstname")
  private String firstname;
  @Column(name = "password")
  private String password;
  @Column(name = "email")
  private String email;
  @Column(name = "website")
  private String website;
  @Column(name = "github")
  private String github;
  @Column(name = "linkedin")
  private String linkedin;
  @Column(name = "is_active")
  private boolean isActive = true;
  @Column(name = "picture")
  private String picture;
  @Column(name = "creation_date")
  private Date creationDate;
  @Column(name = "last_update_date")
  private Date lastUpdateDate;

  public UserEntity() {
  }

  private UserEntity(String username, String password) {
    this.username = username;
    this.password = password;
  }

  private UserEntity(
          String username,
          String firstname,
          String lastname,
          String email,
          String website,
          String github,
          String linkedin,
          String picture) {
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.website = website;
    this.github = github;
    this.linkedin = linkedin;
    this.picture = picture;
  }

  private UserEntity(
          String username,
          String password,
          String firstname,
          String lastname,
          String email,
          String website,
          String github,
          String linkedin,
          String picture) {
    this(username, firstname, lastname, email, website, github, linkedin, picture);
    this.password = password;
  }

  private UserEntity(
          String username,
          String password,
          String firstname,
          String lastname,
          String email,
          String website,
          String github,
          String linkedin,
          String picture,
          boolean isActive,
          Date creationDate,
          Date lastUpdateDate) {
    this(username, password, firstname, lastname, email, website, github, linkedin, picture);
    this.isActive = isActive;
    this.creationDate = creationDate;
    this.lastUpdateDate = lastUpdateDate;
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

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getGithub() {
    return github;
  }

  public void setGithub(String github) {
    this.github = github;
  }

  public String getLinkedin() {
    return linkedin;
  }

  public void setLinkedin(String linkedin) {
    this.linkedin = linkedin;
  }

  public String getPicture() {
    return picture;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public static UserEntity from(String username, String password) {
    return new UserEntity(username, password);
  }

  public static UserEntity fromDomainToInfra(User user) {
    return new UserEntity(user.getUsernameContent(), user.getPasswordContent());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserEntity userEntity = (UserEntity) o;

    if (username != null ? !username.equals(userEntity.username) : userEntity.username != null) return false;
    if (lastname != null ? !lastname.equals(userEntity.lastname) : userEntity.lastname != null) return false;
    if (firstname != null ? !firstname.equals(userEntity.firstname) : userEntity.firstname != null) return false;
    return password != null ? password.equals(userEntity.password) : userEntity.password == null;
  }

  @Override
  public int hashCode() {
    int result = username != null ? username.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", website='" + website + '\'' +
            ", github='" + github + '\'' +
            ", linkedin='" + linkedin + '\'' +
            ", isActive=" + isActive +
            ", picture='" + picture + '\'' +
            ", creationDate=" + creationDate +
            ", lastUpdateDate=" + lastUpdateDate +
            '}';
  }
}

