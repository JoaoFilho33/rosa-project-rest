package com.rosa.rosaRest.repositories;

import com.rosa.rosaRest.Enum.StatusEnum;
import com.rosa.rosaRest.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    List<ProductModel> findByStatus(StatusEnum status);

    List<ProductModel> findByNameContainingIgnoreCase(String search);
}
