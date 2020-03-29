package br.com.koradi.repository;

import br.com.koradi.model.ubs.Ubs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * UBS Repository
 *
 * @author Cláudio Margulhano
 */
public interface UbsRepository extends PagingAndSortingRepository<Ubs, String> {

  Ubs findByName(String name);

  static String HAVERSINE_PART =
      "(6371 * ACOS(COS(RADIANS(:latitude)) * COS(RADIANS(c.latitude)) * COS(RADIANS(c.longitude) - RADIANS(:longitude)) + SIN(RADIANS(:latitude)) * SIN(RADIANS(c.latitude))))";

  @Query(
      "SELECT c FROM Ubs c WHERE "
          + HAVERSINE_PART
          + " < :distance ORDER BY "
          + HAVERSINE_PART
          + " DESC")
  public List<Ubs> findClientWithNearestLocation(
      @Param("latitude") double latitude,
      @Param("longitude") double longitude,
      @Param("distance") double distance,
      Pageable pageable);
}
