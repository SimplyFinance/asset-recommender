package finance.simply.asset.recommender.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "asset")
/**
 * Entity class for an asset.
 */
public class Asset {

  /**
   * Enum for the different asset types.
   */
  public enum Type {
    AGRICULTURE,
    TRANSPORT,
    CONSTRUCTION,
    WASTE
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "cost")
  private double cost;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private Type type;
}
