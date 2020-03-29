package br.com.koradi.service.impl;

import br.com.koradi.dto.mapper.UbsMapper;
import br.com.koradi.dto.model.UbsDto;
import br.com.koradi.model.ubs.Ubs;
import br.com.koradi.repository.UbsRepository;
import br.com.koradi.service.UbsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.koradi.exception.EnterpriseException.throwException;
import static br.com.koradi.exception.EntityType.UBS;
import static br.com.koradi.exception.ExceptionType.DUPLICATE_ENTITY;
import static br.com.koradi.exception.ExceptionType.ENTITY_NOT_FOUND;
import static java.lang.Double.parseDouble;
import static java.util.Optional.ofNullable;

/**
 * Customer Service default implementation
 *
 * @author Cláudio Margulhano
 */
@Component
public class UbsServiceImpl implements UbsService {

  @Autowired private UbsRepository ubsRepository;

  @Autowired private ModelMapper modelMapper;

  @Autowired private UbsMapper ubsMapper;

  @Override
  public UbsDto create(UbsDto ubsDto) {
    Optional<Ubs> ubs = ofNullable(ubsRepository.findByName(ubsDto.getName()));
    if (!ubs.isPresent()) {
      Ubs customerModel = modelMapper.map(ubsDto, Ubs.class);
      return modelMapper.map(ubsRepository.save(customerModel), UbsDto.class);
    }
    throw throwException(UBS, DUPLICATE_ENTITY, ubsDto.getName());
  }

  @Override
  public UbsDto findCustomerById(String id) {
    Optional<Ubs> ubs = ubsRepository.findById(id);
    if (ubs.isPresent()) {
      Ubs customerModel = ubs.get();
      return modelMapper.map(customerModel, UbsDto.class);
    }
    throw throwException(UBS, ENTITY_NOT_FOUND, id);
  }

  @Override
  public UbsDto update(UbsDto ubsDto) {
    Optional<Ubs> customer = ubsRepository.findById(ubsDto.getId());
    if (customer.isPresent()) {
      Ubs customerModel = ubsMapper.of(customer.get(), ubsDto);
      return modelMapper.map(ubsRepository.save(customerModel), UbsDto.class);
    }
    throw throwException(UBS, ENTITY_NOT_FOUND, ubsDto.getName());
  }

  @Override
  public Page<UbsDto> findAll(Pageable pageable, String query) {
    String[] queryParams = query.split(",");
    double lat = parseDouble(queryParams[0]);
    double lon = parseDouble(queryParams[1]);
    double distance = 10;
    if (queryParams.length == 3) {
      distance = parseDouble(queryParams[2]);
    }
    List<Ubs> ubs = ubsRepository.findUbsWithNearestLocation(lat, lon, distance);
    List<UbsDto> ubsDtos = new ArrayList<>();
    ubs.forEach(
        _ubs -> {
          ubsDtos.add(toCustomerDto(_ubs));
        });
    int fromIndex = pageable.getPageNumber() * pageable.getPageSize();
    int toIndex = fromIndex + pageable.getPageSize();
    if (toIndex > ubs.size()) {
      toIndex = ubs.size();
    }
    Page<UbsDto> pages =
        new PageImpl<UbsDto>(ubsDtos.subList(fromIndex, toIndex), pageable, ubs.size());
    return pages;
  }

  /**
   * Maps {@link Ubs} to {@link UbsDto}
   *
   * @param ubs {@link Ubs}
   * @return {@link UbsDto}
   */
  private UbsDto toCustomerDto(Ubs ubs) {
    return ubsMapper.of(ubs);
  }
}
