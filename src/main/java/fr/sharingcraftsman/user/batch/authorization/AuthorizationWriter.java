package fr.sharingcraftsman.user.batch.authorization;

import fr.sharingcraftsman.user.batch.common.PersistenceActionner;
import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class AuthorizationWriter implements ItemWriter<AuthorizationEntity> {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationWriter.class);

  @PersistenceContext
  protected EntityManager entityManager;

  public void write(List<? extends AuthorizationEntity> authorizations) throws Exception {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Writing to JPA with {} items.", authorizations.size());
    }

    if (!authorizations.isEmpty()) {
      for (AuthorizationEntity authorization : authorizations) {
        PersistenceActionner.persistOrMerge(entityManager, authorization);
      }
      entityManager.flush();
      entityManager.clear();
    }
  }
}
