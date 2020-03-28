package br.com.koradi.model.ubs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Customer Entity
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "ubs")
public class Ubs implements Serializable {
  @Id private String id;
  private String name;
  private String address;
  private String neighborhood;
  private String city;
  private String municipally;
  private String phone;
  private String latitude;
  private String longitude;
  private String size;
  private String adaptationforseniors;
  private String medicalequipment;
  private String medicine;
}
