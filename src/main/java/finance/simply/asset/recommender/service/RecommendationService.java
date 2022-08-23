package finance.simply.asset.recommender.service;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.model.Customer;
import finance.simply.asset.recommender.model.Deal;
import finance.simply.asset.recommender.repository.CustomerRepository;
import finance.simply.asset.recommender.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RecommendationService {

    private final DealRepository dealRepository;

    private final CustomerRepository customerRepository;

    private final AssetService assetService;

    @Autowired
    public RecommendationService(DealRepository dealRepository, CustomerRepository customerRepository,
                                 AssetService assetService) {
        this.dealRepository = dealRepository;
        this.customerRepository = customerRepository;
        this.assetService = assetService;
    }

    public Map<Customer, List<Asset>> getRecommendations(LocalDate date) {
        List<Deal> deals = dealRepository.findAll();
        List<Customer> customers = customerRepository.findAll();

        Map<Customer, List<Asset>> recommendations = new HashMap<>();

        for (Customer customer : customers) {
            double currentPayment = 0.0;
            List<String> customerAssetTypes = new ArrayList<>();

            for (Deal deal : deals) {
                if (deal.getCustomers().contains(customer)) {
                    if (!date.isBefore(deal.getStartDate()) && !date.isAfter(deal.getEndDate())) {
                        int numCustomers = deal.getCustomers().size();
                        double totalAssetCost =
                                deal.getAssets().stream().reduce(0.0, (subTotal, asset) -> subTotal + asset.getCost(), Double::sum);
                        currentPayment += totalAssetCost / numCustomers;
                    }

                    customerAssetTypes.addAll(deal.getAssets().stream().map(asset -> asset.getAssetType().getName()).collect(Collectors.toList()));
                }
            }

            final double currentSpareCash = customer.getAffordability() - currentPayment;
            recommendedAssets(recommendations, customer, customerAssetTypes, currentSpareCash);
        }

        return recommendations;
    }

    private void recommendedAssets(Map<Customer, List<Asset>> recommendations,
                                   final Customer customer,
                                   final List<String> customerTypes,
                                   final double currentSpareCash) {
        List<Asset> unsoldAssets = assetService.getUnsoldAssets();
        for (Asset asset : unsoldAssets) {
            if (asset.getCost() <= currentSpareCash &&
                    customerTypes.contains(asset.getAssetType().getName())) {
                recommendations.computeIfAbsent(customer, cust -> new ArrayList<>()).add(asset);
            }
        }

        // Sort assets by gap between spare cash and asset cost
        List<Asset> assets = recommendations.get(customer);
        if (assets != null) {
            List<Asset> sorted = assets.stream().sorted((o1, o2) -> (int) (o1.getCost() - currentSpareCash)).collect(Collectors.toList());
            recommendations.put(customer, sorted);
        }
    }

}
