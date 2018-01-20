package fr.sharingcraftsman.user.domain.userauthorization;

import fr.sharingcraftsman.user.infrastructure.models.UserAuthorizationEntity;
import org.springframework.batch.item.ItemProcessor;

public class UserAuthorizationItemProcessor implements ItemProcessor<UserAuthorizationEntity, UserAuthorizationEntity> {
  @Override
  public UserAuthorizationEntity process(UserAuthorizationEntity userAuthorizationEntity) throws Exception {
    UserAuthorization authorization = UserAuthorization.from(userAuthorizationEntity.getUsername(), userAuthorizationEntity.getGroup());
    return UserAuthorizationEntity.fromDomainToInfra(authorization);
  }
}
