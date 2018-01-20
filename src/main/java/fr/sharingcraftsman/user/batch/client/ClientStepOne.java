package fr.sharingcraftsman.user.batch.client;

import fr.sharingcraftsman.user.domain.client.ClientItemProcessor;
import fr.sharingcraftsman.user.infrastructure.models.ClientEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientStepOne {
  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  private ClientReader clientReader;

  @Autowired
  private ClientWriter clientWriter;

  private ClientItemProcessor processor = new ClientItemProcessor();

  @Bean
  public Step step() {
    return stepBuilderFactory.get("step1")
            .<ClientEntity, ClientEntity> chunk(10)
            .reader(clientReader.reader())
            .processor(processor)
            .writer(clientWriter)
            .build();
  }
}
