package com.fanxan.bepo.repository;

import com.fanxan.bepo.models.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository  extends JpaRepository<Items, Integer> {
}
