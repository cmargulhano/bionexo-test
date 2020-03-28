package br.com.koradi.dto.mapper;

import br.com.koradi.controller.v1.request.UbsRequest;
import br.com.koradi.dto.model.UbsDto;
import br.com.koradi.model.ubs.Ubs;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UBS Mapper
 *
 * @author Cláudio Margulhano
 */
@Component
public class UbsMapper {

  @Autowired private ModelMapper mapper;

  /**
   * Maps {@link Ubs} to {@link UbsDto}
   *
   * @param ubs {@link Ubs}
   * @return {@link UbsDto}
   */
  public UbsDto of(Ubs ubs) {
    return mapper.map(ubs, UbsDto.class);
  }

  /**
   * Maps {@link UbsRequest} to {@link UbsDto}
   *
   * @param ubs {@link UbsRequest}
   * @return {@link UbsDto}
   */
  public UbsDto of(UbsRequest ubs) {
    return mapper.map(ubs, UbsDto.class);
  }

  /**
   * Merge {@link Ubs} and {@link UbsDto}
   *
   * @param ubs {@link Ubs}
   * @param ubsDto {@link UbsDto}
   * @return {@link Ubs}
   */
  public Ubs of(Ubs ubs, UbsDto ubsDto) {
    if (ubsDto.getName() != null && !"".equals(ubsDto.getName())) {
      ubs.setName(ubsDto.getName());
    }
    return ubs;
  }
}
