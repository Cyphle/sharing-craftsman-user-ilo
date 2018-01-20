package fr.sharingcraftsman.user.batch.userauthorization;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AddUserAuthorizationJob {
  @Autowired
  public JobBuilderFactory jobBuilderFactory;
  @Autowired
  private UserAuthorizationStepOne userAuthorizationStepOne;

  @Bean
  public Job importUserAuthorizationJob(UserAuthorizationJobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("importUserAuthorizationJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(userAuthorizationStepOne.step())
            .end()
            .build();
  }
}
