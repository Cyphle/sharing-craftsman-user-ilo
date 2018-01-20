package fr.sharingcraftsman.user.batch.user;

import fr.sharingcraftsman.user.batch.common.PersistenceActionner;
import fr.sharingcraftsman.user.infrastructure.models.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserWriter implements ItemWriter<UserEntity> {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserWriter.class);

  @PersistenceContext
  protected EntityManager entityManager;

  public void write(List<? extends UserEntity> users) throws Exception {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Writing to JPA with {} items.", users.size());
    }

    if (!users.isEmpty()) {
      for (UserEntity user : users) {
        PersistenceActionner.persistOrMerge(entityManager, user);
      }
      entityManager.flush();
      entityManager.clear();
    }
  }
}
