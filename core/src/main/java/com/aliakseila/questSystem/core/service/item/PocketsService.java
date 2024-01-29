package com.aliakseila.questSystem.core.service.item;

import com.aliakseila.questSystem.core.repository.item.PocketsRepo;
import com.aliakseila.questSystem.model.entity.person.Pockets;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PocketsService {

    private final PocketsRepo pocketsRepo;

    public Pockets createAndSave(double sum){
        Pockets pockets = new Pockets();
        pockets.setMoney(sum);
        return save(pockets);
    }

    public Pockets save(Pockets pockets) {
        return pocketsRepo.save(pockets);
    }

}
