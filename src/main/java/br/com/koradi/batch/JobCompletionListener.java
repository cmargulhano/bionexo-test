package br.com.koradi.batch;

import br.com.koradi.dto.model.UbsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JobCompletionListener implements JobExecutionListener {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired public JdbcTemplate jdbcTemplate;

  @Override
  public void beforeJob(JobExecution jobExecution) {
    logger.info("Executing job id " + jobExecution.getId());
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      List<UbsDto> result =
          jdbcTemplate.query(
              "SELECT id, name FROM ubs",
              new RowMapper<UbsDto>() {
                @Override
                public UbsDto mapRow(ResultSet rs, int row) throws SQLException {
                  return new UbsDto().setId(rs.getString(1)).setName(rs.getString(2));
                }
              });
      logger.info("Number of Records:" + result.size());
    }
  }
}
