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
      "(6371 * ACOS(COS(RADIANS(:latitude)) * COS(RADIANS(u.latitude)) * COS(RADIANS(u.longitude) - RADIANS(:longitude)) + SIN(RADIANS(:latitude)) * SIN(RADIANS(u.latitude))))";

  @Query(
      "SELECT u FROM Ubs u WHERE "
          + HAVERSINE_PART
          + " < :distance ORDER BY "
          + HAVERSINE_PART
          + " ASC")
  public List<Ubs> findUbsWithNearestLocation(
      @Param("latitude") double latitude,
      @Param("longitude") double longitude,
      @Param("distance") double distance);
}
