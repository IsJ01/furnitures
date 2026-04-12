package com.cur.furniture.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cur.furniture.database.entity.DealFurniture;

@Repository
public interface DealFurnitureRepository extends JpaRepository<DealFurniture, Long> {

}
