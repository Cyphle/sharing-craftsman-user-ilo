package fr.sharingcraftsman.user.domain.authorization;

import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import org.springframework.batch.item.ItemProcessor;

public class AuthorizationItemProcessor implements ItemProcessor<AuthorizationEntity, AuthorizationEntity> {
  @Override
  public AuthorizationEntity process(AuthorizationEntity authorizationEntity) throws Exception {
    Authorization authorization = Authorization.from(authorizationEntity.getGroup(), authorizationEntity.getRole());
    return AuthorizationEntity.fromDomainToInfra(authorization);
  }
}
