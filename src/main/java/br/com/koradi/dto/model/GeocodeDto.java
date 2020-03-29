package br.com.koradi.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Geocode DTO
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeDto {
  private Double latitude;
  private Double longitude;
}
