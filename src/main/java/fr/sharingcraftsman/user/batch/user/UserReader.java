package fr.sharingcraftsman.user.batch.user;

import fr.sharingcraftsman.user.config.DataSourcesConfig;
import fr.sharingcraftsman.user.infrastructure.models.UserEntity;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class UserReader {
  @Autowired
  private DataSourcesConfig dataSourcesConfig;

  public FlatFileItemReader<UserEntity> reader() {
    FlatFileItemReader<UserEntity> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource(dataSourcesConfig.getDataUserPath()));
    reader.setLineMapper(new DefaultLineMapper<UserEntity>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[] { "username", "password" });
      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<UserEntity>() {{
        setTargetType(UserEntity.class);
      }});
    }});
    return reader;
  }
}
