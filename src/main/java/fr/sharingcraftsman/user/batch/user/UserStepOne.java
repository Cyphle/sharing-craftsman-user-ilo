package fr.sharingcraftsman.user.batch.user;

import fr.sharingcraftsman.user.domain.user.UserItemProcessor;
import fr.sharingcraftsman.user.infrastructure.models.UserEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserStepOne {
  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  private UserReader userReader;

  @Autowired
  private UserWriter userWriter;

  public UserItemProcessor processor = new UserItemProcessor();

  @Bean
  public Step step() {
    return stepBuilderFactory.get("step1")
            .<UserEntity, UserEntity> chunk(10)
            .reader(userReader.reader())
            .processor(processor)
            .writer(userWriter)
            .build();
  }
}
