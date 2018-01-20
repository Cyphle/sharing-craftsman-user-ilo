package fr.sharingcraftsman.user.batch.userauthorization;

import fr.sharingcraftsman.user.domain.userauthorization.UserAuthorizationItemProcessor;
import fr.sharingcraftsman.user.infrastructure.models.UserAuthorizationEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserAuthorizationStepOne {
  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  private UserAuthorizationReader userAuthorizationReader;

  @Autowired
  private UserAuthorizationWriter userAuthorizationWriter;

  private UserAuthorizationItemProcessor processor = new UserAuthorizationItemProcessor();

  @Bean
  public Step step() {
    return stepBuilderFactory.get("step1")
            .<UserAuthorizationEntity, UserAuthorizationEntity> chunk(10)
            .reader(userAuthorizationReader.reader())
            .processor(processor)
            .writer(userAuthorizationWriter)
            .build();
  }
}
