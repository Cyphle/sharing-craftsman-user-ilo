package fr.sharingcraftsman.user.batch.user;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AddUserJob {
  @Autowired
  public JobBuilderFactory jobBuilderFactory;
  @Autowired
  private UserStepOne userStepOne;

  @Bean
  public Job importUserJob(UserJobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(userStepOne.step())
            .end()
            .build();
  }
}
