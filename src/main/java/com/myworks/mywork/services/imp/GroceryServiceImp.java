package com.myworks.mywork.services.imp;

import com.myworks.mywork.models.GroceryItem;
import com.myworks.mywork.repository.GroceryItemRepository;
import com.myworks.mywork.services.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroceryServiceImp implements GroceryService {
    private final GroceryItemRepository groceryItemRepository;

    @Autowired
    public GroceryServiceImp(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    @Transactional
    @Override
    public GroceryItem createGrocery(GroceryItem groceryItem) {
        return this.groceryItemRepository.save(groceryItem);
    }
}
