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

  static final String HAVERSINE_PART =
      "(6371 * acos(cos(radians(:latitude)) * cos(radians(m.latitude)) * cos(radians(m.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(m.latitude))))";

  @Query(
      "SELECT m FROM Ubs m WHERE "
          + HAVERSINE_PART
          + " < :distance ORDER BY "
          + HAVERSINE_PART
          + " DESC")
  public List<Ubs> findEntitiesByLocation(
      @Param("latitude") final double latitude,
      @Param("longitude") final double longitude,
      @Param("distance") final double distance,
      Pageable pageable);
}
