package fr.sharingcraftsman.user.batch.userauthorization;

import fr.sharingcraftsman.user.batch.user.UserJobCompletionNotificationListener;
import fr.sharingcraftsman.user.infrastructure.models.AuthorizationEntity;
import fr.sharingcraftsman.user.infrastructure.models.UserAuthorizationEntity;
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
public class UserAuthorizationJobCompletionNotificationListener extends JobExecutionListenerSupport {
  private static final Logger log = LoggerFactory.getLogger(UserJobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserAuthorizationJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      List<UserAuthorizationEntity> results = jdbcTemplate
              .query("SELECT * FROM user_authorizations",
                      (rs, row) -> UserAuthorizationEntity.from(rs.getString(2), rs.getString(3))
              );

      for (UserAuthorizationEntity authorization : results) {
        log.info("Found <" + authorization + "> in the database.");
      }
    }
  }
}
