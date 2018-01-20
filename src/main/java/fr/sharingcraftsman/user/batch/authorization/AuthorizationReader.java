package fr.sharingcraftsman.user.batch.authorization;

import fr.sharingcraftsman.user.config.DataSourcesConfig;
import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationReader {
  @Autowired
  private DataSourcesConfig dataSourcesConfig;

  public FlatFileItemReader<AuthorizationEntity> reader() {
    FlatFileItemReader<AuthorizationEntity> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource(dataSourcesConfig.getAuthotizationPath()));
    reader.setLineMapper(new DefaultLineMapper<AuthorizationEntity>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[] { "group", "role" });
      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<AuthorizationEntity>() {{
        setTargetType(AuthorizationEntity.class);
      }});
    }});
    return reader;
  }
}
