package fr.sharingcraftsman.user.batch.user;

import fr.sharingcraftsman.user.infrastructure.models.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserJobCompletionNotificationListener extends JobExecutionListenerSupport {
  private static final Logger log = LoggerFactory.getLogger(UserJobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      List<UserEntity> results = jdbcTemplate
              .query("SELECT username, password FROM users",
                      (rs, row) -> UserEntity.from(rs.getString(1), rs.getString(2))
              );

      for (UserEntity user : results) {
        log.info("Found <" + user + "> in the database.");
      }
    }
  }
}
