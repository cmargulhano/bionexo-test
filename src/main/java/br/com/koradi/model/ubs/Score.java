package br.com.koradi.model.ubs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "score")
public class Score implements Serializable {
  @Id private String id;
  private ScoreType size;
  private ScoreType adaptation_for_seniors;
  private ScoreType medical_equipment;
  private ScoreType medicine;
}
