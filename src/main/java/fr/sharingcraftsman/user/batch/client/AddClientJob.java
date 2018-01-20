package fr.sharingcraftsman.user.batch.client;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AddClientJob {
  @Autowired
  public JobBuilderFactory jobBuilderFactory;
  @Autowired
  private ClientStepOne clientStepOne;

  @Bean
  public Job importClientJob(ClientJobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("importClientJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(clientStepOne.step())
            .end()
            .build();
  }
}
