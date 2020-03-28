package br.com.koradi.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * UBS DTO
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UbsDto {
  private String id;
  private String name;
  private String address;
  private String city;
  private String phone;
  private LatLongDto geocode;
  private ScoreDto scores;

  private String lat;
  private String lon;
  private String size;
  private String adaptationForSeniors;
  private String medicalEquipment;
  private String medicine;
}
