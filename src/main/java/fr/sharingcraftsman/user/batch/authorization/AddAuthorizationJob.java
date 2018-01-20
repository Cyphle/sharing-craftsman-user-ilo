package fr.sharingcraftsman.user.batch.authorization;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AddAuthorizationJob {
  @Autowired
  public JobBuilderFactory jobBuilderFactory;
  @Autowired
  private AuthorizationStepOne authorizationStepOne;

  @Bean
  public Job importAuthorizationJob(AuthorizationJobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("importAuthorizationJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(authorizationStepOne.step())
            .end()
            .build();
  }
}
