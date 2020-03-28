package br.com.koradi.batch;

import br.com.koradi.dto.model.UbsDto;
import org.springframework.batch.item.ItemProcessor;

public class UbsItemProcessor implements ItemProcessor<UbsDto, UbsDto> {
  @Override
  public UbsDto process(UbsDto ubs) throws Exception {
    return ubs;
  }
}
