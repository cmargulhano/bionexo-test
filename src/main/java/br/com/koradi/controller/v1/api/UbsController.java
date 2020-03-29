package br.com.koradi.controller.v1.api;

import br.com.koradi.dto.mapper.UbsMapper;
import br.com.koradi.dto.model.UbsDto;
import br.com.koradi.dto.response.Response;
import br.com.koradi.service.UbsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * Customer controller
 *
 * @author Cláudio Margulhano
 */
@RestController
@RequestMapping("/api/v1/ubs")
@Api(
    value = "ubs-application",
    description = "Operations pertaining to UBS application")
public class UbsController {
  @Autowired private UbsService ubsService;
  @Autowired private ModelMapper modelMapper;
  @Autowired private UbsMapper ubsMapper;

  /**
   * Finds all UBS
   *
   * @param page {@link Pageable} page
   * @param assembler {@link PagedResourcesAssembler} assembler
   * @return {@link Response} all UBS
   */
  @ApiOperation(value = "Finds all UBS", response = Response.class)
  @GetMapping
  public PagedResources<Resource<UbsDto>> findAll(
      Pageable page, PagedResourcesAssembler<UbsDto> assembler, @RequestParam String query) {
    return assembler.toResource(ubsService.findAll(page, query));
  }
}
