package com.example.adapter.output.repository;

import com.example.adapter.output.repository.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductEntityRepository extends MongoRepository<ProductEntity, String> {
    @Query("{\n" +
            "  \"tenantId\": \"255dbfe7a02ff7d9661426a7\",\n" +
            "  \"$and\": [\n" +
            "    { \"start\": { \"$gte\": ISODate(\"2025-01-01T03:00:00Z\") } },\n" +
            "    { \"end\": { \"$lte\": ISODate(\"2025-01-05T23:59:59Z\") } }\n" +
            "  ]\n" +
            "}")
    List<ProductEntity> findByFilter(LocalDateTime start, LocalDateTime end);

}
