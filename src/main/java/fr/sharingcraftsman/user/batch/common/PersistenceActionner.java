package fr.sharingcraftsman.user.batch.common;

import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import fr.sharingcraftsman.user.infrastructure.models.ClientEntity;
import fr.sharingcraftsman.user.infrastructure.models.UserAuthorizationEntity;
import fr.sharingcraftsman.user.infrastructure.models.UserEntity;

import javax.persistence.EntityManager;

public class PersistenceActionner {
  public static void persistOrMerge(EntityManager entityManager, AuthorizationEntity authorization) {
    if (authorization.getId() < 0) {
      entityManager.persist(authorization);
    } else {
      entityManager.merge(authorization);
    }
  }

  public static void persistOrMerge(EntityManager entityManager, UserEntity user) {
    if (user.getId() < 0) {
      entityManager.persist(user);
    } else {
      entityManager.merge(user);
    }
  }

  public static void persistOrMerge(EntityManager entityManager, ClientEntity client) {
    if (client.getId() < 0) {
      entityManager.persist(client);
    } else {
      entityManager.merge(client);
    }
  }

  public static void persistOrMerge(EntityManager entityManager, UserAuthorizationEntity authorization) {
    if (authorization.getId() < 0) {
      entityManager.persist(authorization);
    } else {
      entityManager.merge(authorization);
    }
  }
}
