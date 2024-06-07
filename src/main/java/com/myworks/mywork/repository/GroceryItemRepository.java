package com.myworks.mywork.repository;

import com.myworks.mywork.models.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroceryItemRepository  extends MongoRepository<GroceryItem,String> {
}
