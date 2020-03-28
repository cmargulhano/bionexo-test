package br.com.koradi.service;

import br.com.koradi.repository.UbsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UbsServiceTests {
  @Autowired private UbsService ubsService;
  @Autowired private UbsRepository ubsRepository;

  @Test
  public void findUbs() {
    assertThat(0).isEqualTo(0);
  }
}
