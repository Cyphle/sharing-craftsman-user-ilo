package fr.sharingcraftsman.user.batch.userauthorization;

import fr.sharingcraftsman.user.config.DataSourcesConfig;
import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import fr.sharingcraftsman.user.infrastructure.models.UserAuthorizationEntity;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class UserAuthorizationReader {
  @Autowired
  private DataSourcesConfig dataSourcesConfig;

  public FlatFileItemReader<UserAuthorizationEntity> reader() {
    FlatFileItemReader<UserAuthorizationEntity> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource(dataSourcesConfig.getUserAuthorizationPath()));
    reader.setLineMapper(new DefaultLineMapper<UserAuthorizationEntity>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[] { "username", "group" });
      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<UserAuthorizationEntity>() {{
        setTargetType(UserAuthorizationEntity.class);
      }});
    }});
    return reader;
  }
}
