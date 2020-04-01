package br.com.koradi.service;

import br.com.koradi.dto.model.UbsDto;
import br.com.koradi.repository.UbsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.lang.Math.*;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.PageRequest.of;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UbsServiceTests {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired private UbsService ubsService;
  @Autowired private UbsRepository ubsRepository;

  @Test
  public void findUbs() {
    double lat = -23.2616148d;
    double lng = -45.894472099999994d;
    double distance = 10.0d;
    List<UbsDto> ubs =
        ubsService
            .findAll(
                of(0, 9999),
                valueOf(lat).concat(",").concat(valueOf(lng).concat(",").concat(valueOf(distance))))
            .getContent();
    ubs.forEach(
        _ubs -> {
          logger.info(_ubs.toString());
          double _distance = distance(lat, lng, _ubs.getLatitude(), _ubs.getLongitude());
          logger.info("Distance: " + _distance);
          assertThat(_distance).isLessThan(distance);
        });

    assertThat(ubs.size()).isEqualTo(31);
  }

  double distance(double lat1, double long1, double lat2, double long2) {
    // Convert the latitudes and longitudes from degree to radians.
    lat1 = toRadians(lat1);
    long1 = toRadians(long1);
    lat2 = toRadians(lat2);
    long2 = toRadians(long2);

    // Haversine Formula
    double dlong = long2 - long1;
    double dlat = lat2 - lat1;

    double ans = pow(sin(dlat / 2), 2) + cos(lat1) * cos(lat2) * pow(sin(dlong / 2), 2);

    ans = 2 * asin(sqrt(ans));

    // Radius of Earth in Kilometers
    double R = 6371;

    // Calculate the result
    ans = ans * R;

    return ans;
  }
}
