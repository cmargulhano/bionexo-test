package br.com.koradi.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonSerialize(using = UbsSerializer.class)
public class UbsDto {
  private String id;
  private String name;
  private String address;
  private String neighborhood;
  private String city;
  private String municipally;
  private String phone;
  private Double latitude;
  private Double longitude;
  private String size;
  private String adaptationforseniors;
  private String medicalequipment;
  private String medicine;
}
