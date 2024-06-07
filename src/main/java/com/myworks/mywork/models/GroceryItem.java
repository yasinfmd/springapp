package com.myworks.mywork.models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItem {


        @Id
        private String id;

        private String name;
        private int quantity;
        private String category;


}
