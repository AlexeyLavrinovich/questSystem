package com.aliakseila.questSystem.core.service.item;

import com.aliakseila.questSystem.core.repository.item.ItemRepo;
import com.aliakseila.questSystem.model.entity.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;

    public Item create(String name){
        Item item = new Item();
        item.setName(name);
        return item;
    }

    public void deleteAll() {
        itemRepo.deleteAll();
    }
}
