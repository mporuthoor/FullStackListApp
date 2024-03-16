package com.project.listconstructorbackend.repository;

import com.project.listconstructorbackend.model.ListOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ListOrderRepository extends JpaRepository<ListOrder, UUID> {
    List<ListOrder> findAllByOrderByIndexAsc();
}
