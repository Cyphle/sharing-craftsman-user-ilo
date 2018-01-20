package fr.sharingcraftsman.user.domain.client;

import fr.sharingcraftsman.user.domain.user.Password;
import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import fr.sharingcraftsman.user.infrastructure.models.ClientEntity;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

public class ClientItemProcessor implements ItemProcessor<ClientEntity, ClientEntity> {
  @Override
  public ClientEntity process(ClientEntity clientEntity) throws Exception {
    Password secret = Password.from(clientEntity.getSecret()).getEncryptedVersion();
    Client client = Client.from(clientEntity.getName(), secret.getPassword());
    ClientEntity modifiedClient = ClientEntity.fromDomainToInfra(client);
    modifiedClient.setCreationDate(new Date());
    modifiedClient.setLastUpdateDate(new Date());
    return modifiedClient;
  }
}
