package finance.simply.asset.recommender.model;

import javax.persistence.*;

@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private double cost;

    @OneToOne
    @JoinColumn(name = "type_id")
    private AssetType assetType;

    @ManyToOne
    @JoinColumn(name = "deal_id")
    private Deal deal;

    public Asset() {
    }

    public Asset(Integer id, String name, double cost, AssetType assetType, Deal deal) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.assetType = assetType;
        this.deal = deal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

}
