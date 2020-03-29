package br.com.koradi.service;

import br.com.koradi.dto.model.UbsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * UBS Service
 *
 * @author Cláudio Margulhano
 */
public interface UbsService {
  /**
   * Saves customer
   *
   * @param ubsDto {@link UbsDto}
   * @return {@link UbsDto}
   */
  UbsDto create(UbsDto ubsDto);

  /**
   * Finds UBS by id
   *
   * @param id Id
   * @return {@link UbsDto}
   */
  public UbsDto findCustomerById(String id);

  /**
   * Updates UBS
   *
   * @param ubsDto {@link UbsDto}
   * @return @return {@link UbsDto}
   */
  public UbsDto update(UbsDto ubsDto);

  /**
   * Finds UBS
   *
   * @param pageable {@link Pageable}
   * @return {@link Page}
   */
  public Page<UbsDto> findAll(Pageable pageable, String query);
}
