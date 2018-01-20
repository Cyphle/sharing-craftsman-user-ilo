package fr.sharingcraftsman.user.domain.user;

import fr.sharingcraftsman.user.infrastructure.models.UserEntity;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

public class UserItemProcessor implements ItemProcessor<UserEntity, UserEntity> {
  @Override
  public UserEntity process(UserEntity userEntity) throws Exception {
    User user = User.from(userEntity.getUsername(), userEntity.getPassword());
    user.encrypt();
    UserEntity modifiedUserEntity = UserEntity.fromDomainToInfra(user);
    modifiedUserEntity.setCreationDate(new Date());
    modifiedUserEntity.setLastUpdateDate(new Date());
    return modifiedUserEntity;
  }
}
