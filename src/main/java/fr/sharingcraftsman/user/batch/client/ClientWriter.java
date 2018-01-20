package fr.sharingcraftsman.user.batch.client;

import fr.sharingcraftsman.user.batch.common.PersistenceActionner;
import fr.sharingcraftsman.user.infrastructure.models.ClientEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class ClientWriter implements ItemWriter<ClientEntity> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientWriter.class);

  @PersistenceContext
  protected EntityManager entityManager;

  public void write(List<? extends ClientEntity> clients) throws Exception {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Writing to JPA with {} items.", clients.size());
    }

    if (!clients.isEmpty()) {
      for (ClientEntity client : clients) {
        PersistenceActionner.persistOrMerge(entityManager, client);
      }
      entityManager.flush();
      entityManager.clear();
    }
  }
}
