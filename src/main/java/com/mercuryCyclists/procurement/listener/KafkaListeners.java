package com.mercuryCyclists.procurement.listener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mercuryCyclists.procurement.entity.Supplier;
import com.mercuryCyclists.procurement.repository.SupplierRepository;
import com.mercuryCyclists.procurement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Listener component for Kafka messaging
 */
@Transactional
@Component
public class KafkaListeners {

    private final SupplierRepository supplierRepository;
    private final SupplierService supplierService;

    private static final String GETPARTSFROMPRODUCT = "http://localhost:8081/api/v1/product/{productId}/part";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final JsonParser jsonParser = new JsonParser();

    @Autowired
    public KafkaListeners(SupplierRepository supplierRepository, SupplierService supplierService) {
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
    }

    /**
     * Processes sales pushed to the backorder topic
     * Extracts the saleId and productId from sale
     * gets parts from productId
     * gets supplier from parts
     * saves sale based on supplier provided
     * @param data pulled from backorder topic
     */
    @KafkaListener(topics = "backorder", groupId = "backorder")
    void backOrderListener(String data){
        try{
            JsonObject backorder = jsonParser.parse(data).getAsJsonObject();

            Long saleId =  backorder.get("id").getAsLong();
            String productId =  backorder.get("productId").getAsString();

            Map<String, String> params = new HashMap<>();
            params.put("productId", productId);
            String partsResponse = restTemplate.getForObject(GETPARTSFROMPRODUCT, String.class, params);

            JsonArray parts = jsonParser.parse(partsResponse).getAsJsonArray();

            parts.forEach((partElement) -> {
                JsonObject part = partElement.getAsJsonObject();
                Long supplierId =  part.get("supplierId").getAsLong();

                Supplier supplier = supplierService.getSupplier(supplierId);
                supplier.getBackOrders().add(saleId);
                supplierRepository.save(supplier);
            });

        } catch (Exception e){
            System.err.printf("Failed to process kafka message %s, err: %s %n", data, e);
        }
    }
}
