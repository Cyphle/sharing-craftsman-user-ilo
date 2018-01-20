package fr.sharingcraftsman.user.batch.client;

import fr.sharingcraftsman.user.config.DataSourcesConfig;
import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import fr.sharingcraftsman.user.infrastructure.models.ClientEntity;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ClientReader {
  @Autowired
  private DataSourcesConfig dataSourcesConfig;

  public FlatFileItemReader<ClientEntity> reader() {
    FlatFileItemReader<ClientEntity> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource(dataSourcesConfig.getClientPath()));
    reader.setLineMapper(new DefaultLineMapper<ClientEntity>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[] { "name", "secret" });
      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<ClientEntity>() {{
        setTargetType(ClientEntity.class);
      }});
    }});
    return reader;
  }
}
