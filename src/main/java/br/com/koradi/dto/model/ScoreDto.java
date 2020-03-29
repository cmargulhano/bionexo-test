package br.com.koradi.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Score DTO
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
public class ScoreDto {
  public static final String SCORE_1 = "Desempenho mediano ou  um pouco abaixo da média";
  public static final String SCORE_2 = "Desempenho acima da média";
  public static final String SCORE_3 = "Desempenho muito acima da média";
  private Integer size;
  private Integer adaptation_for_seniors;
  private Integer medical_equipment;
  private Integer medicine;

  public static int score(String desc) {
    if (desc.equals(SCORE_1)) {
      return 1;
    } else if (desc.equals(SCORE_2)) {
      return 2;
    } else if (desc.equals(SCORE_3)) {
      return 3;
    }
    return 0;
  }
}
