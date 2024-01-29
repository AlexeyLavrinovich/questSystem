package com.aliakseila.questSystem.core.service.item;

import com.aliakseila.questSystem.core.repository.item.ItemRepo;
import com.aliakseila.questSystem.model.entity.Item;
import com.aliakseila.questSystem.model.entity.person.Person;
import com.aliakseila.questSystem.model.entity.person.Pockets;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;

    public Item save(Item item){
        return itemRepo.save(item);
    }

    public Item deleteAndSave(Item item){
        itemRepo.delete(item);
        return save(item);
    }

    public Item createAndSave(String name, Pockets pockets){
        Item item = new Item();
        item.setName(name);
        item.setOwner(pockets);
        return save(item);
    }

    public void deleteAll() {
        itemRepo.deleteAll();
    }

    public Item findByName(String name) {
        return itemRepo.findByName(name);
    }
}
