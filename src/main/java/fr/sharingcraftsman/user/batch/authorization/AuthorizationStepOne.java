package fr.sharingcraftsman.user.batch.authorization;

import fr.sharingcraftsman.user.domain.authorization.AuthorizationItemProcessor;
import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationStepOne {
  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  private AuthorizationReader authorizationReader;

  @Autowired
  private AuthorizationWriter authorizationWriter;

  private AuthorizationItemProcessor processor = new AuthorizationItemProcessor();

  @Bean
  public Step step() {
    return stepBuilderFactory.get("step1")
            .<AuthorizationEntity, AuthorizationEntity> chunk(10)
            .reader(authorizationReader.reader())
            .processor(processor)
            .writer(authorizationWriter)
            .build();
  }
}
