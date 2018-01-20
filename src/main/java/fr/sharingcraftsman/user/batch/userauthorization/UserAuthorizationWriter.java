package fr.sharingcraftsman.user.batch.userauthorization;

import fr.sharingcraftsman.user.batch.common.PersistenceActionner;
import fr.sharingcraftsman.user.infrastructure.models.UserAuthorizationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserAuthorizationWriter implements ItemWriter<UserAuthorizationEntity> {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthorizationWriter.class);

  @PersistenceContext
  protected EntityManager entityManager;

  public void write(List<? extends UserAuthorizationEntity> authorizations) throws Exception {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Writing to JPA with {} items.", authorizations.size());
    }

    if (!authorizations.isEmpty()) {
      for (UserAuthorizationEntity authorization : authorizations) {
        PersistenceActionner.persistOrMerge(entityManager, authorization);
      }
      entityManager.flush();
      entityManager.clear();
    }
  }
}
