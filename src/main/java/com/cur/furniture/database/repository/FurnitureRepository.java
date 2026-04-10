package com.cur.furniture.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cur.furniture.database.entity.Furniture;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long>, JpaSpecificationExecutor<Furniture> {

}
