package br.com.koradi.repository;

import br.com.koradi.model.ubs.Ubs;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UBS Repository
 *
 * @author Cláudio Margulhano
 */
public interface UbsRepository extends PagingAndSortingRepository<Ubs, String> {

  Ubs findByName(String name);
}
