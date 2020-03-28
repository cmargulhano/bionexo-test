package br.com.koradi.config;

import br.com.koradi.batch.JobCompletionListener;
import br.com.koradi.batch.UbsItemProcessor;
import br.com.koradi.dto.model.UbsDto;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration extends DefaultBatchConfigurer {

  @Autowired public JobBuilderFactory jobBuilderFactory;

  @Autowired public StepBuilderFactory stepBuilderFactory;

  @Bean
  public FlatFileItemReader<UbsDto> reader() {
    return new FlatFileItemReaderBuilder<UbsDto>()
        .name("personItemReader")
        .resource(new ClassPathResource("ubs.csv"))
        .linesToSkip(1)
        .delimited()
        .names(
            new String[] {
              "latitude",
              "longitude",
              "municipally",
              "id",
              "name",
              "address",
              "neighborhood",
              "city",
              "phone",
              "size",
              "adaptationforseniors",
              "medicalequipment",
              "medicine"
            })
        .targetType(UbsDto.class)
        .build();
  }

  @Bean
  public DefaultLineMapper<UbsDto> lineMapper() {
    return new DefaultLineMapper<UbsDto>() {
      {
        setLineTokenizer(
            new DelimitedLineTokenizer() {
              {
                setNames(
                    new String[] {
                      "latitude",
                      "longitude",
                      "municipally",
                      "id",
                      "name",
                      "address",
                      "neighborhood",
                      "city",
                      "phone",
                      "size",
                      "adaptationforseniors",
                      "medicalequipment",
                      "medicine"
                    });
              }
            });
        setFieldSetMapper(
            new BeanWrapperFieldSetMapper<UbsDto>() {
              {
                setTargetType(UbsDto.class);
              }
            });
      }
    };
  }

  @Bean
  public ItemProcessor<UbsDto, UbsDto> processor() {
    return new UbsItemProcessor();
  }

  @Bean
  public JdbcBatchItemWriter<UbsDto> writer(DataSource dataSource) {
    JdbcBatchItemWriter<UbsDto> writer = new JdbcBatchItemWriter<UbsDto>();
    writer.setItemSqlParameterSourceProvider(
        new BeanPropertyItemSqlParameterSourceProvider<UbsDto>());
    writer.setSql(
        "INSERT INTO ubs(id, name, address, city, phone, latitude, longitude, neighborhood, municipally, size, adaptationforseniors, medicalequipment, medicine) "
            + "VALUES (:id, :name, :address, :city, :phone, :latitude, :longitude, :neighborhood, :municipally, :size, :adaptationforseniors, :medicalequipment, :medicine)");
    writer.setDataSource(dataSource);
    return writer;
  }

  @Bean
  public Step step1(
      ItemReader<UbsDto> reader,
      ItemWriter<UbsDto> writer,
      ItemProcessor<UbsDto, UbsDto> processor) {
    return stepBuilderFactory
        .get("step")
        .<UbsDto, UbsDto>chunk(10)
        .reader(reader())
        .processor(processor)
        .writer(writer)
        .build();
  }

  @Bean
  public Job readCSVFileJob(JobCompletionListener listener, Step step1) {
    return jobBuilderFactory
        .get("importUbsJob")
        .incrementer(new RunIdIncrementer())
        .flow(step1)
        .end()
        .build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Override
  public void setDataSource(DataSource dataSource) {}
}
