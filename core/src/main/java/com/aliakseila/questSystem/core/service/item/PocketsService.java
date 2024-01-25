package com.aliakseila.questSystem.core.service.item;

import com.aliakseila.questSystem.core.repository.item.PocketsRepo;
import com.aliakseila.questSystem.model.entity.person.Pockets;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PocketsService {

    private final PocketsRepo pocketsRepo;

    public Pockets create(double sum){
        Pockets pockets = new Pockets();
        pockets.setMoney(sum);
        return pockets;
    }

}
