package com.aliakseila.questSystem.core.service.person;

import com.aliakseila.questSystem.core.repository.person.PlayerRepo;
import com.aliakseila.questSystem.core.service.item.PocketsService;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.person.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService implements PersonService<Player>{

    private final PlayerRepo playerRepo;
    private final PocketsService pocketsService;

    @Override
    public Player save(Player player) {
        return playerRepo.save(player);
    }

    @Override
    public Player getOrCreate(String name, double sum) {
        Optional<Player> player = playerRepo.findByUsername(name);
        if (player.isEmpty()){
            return createAndSave(name, sum);
        }
        return player.orElseThrow();
    }

    @Override
    public Player createAndSave(String name, double sum){
        Player player = new Player();
        player.setUsername(name);
        player.setPockets(pocketsService.create(sum));
        player.setStatus(Collections.singleton(Status.ALIVE));
        return save(player);
    }

    @Override
    public Player getByUsername(String name) {
        return playerRepo.findByUsername(name).orElseThrow();
    }

    @Override
    public void deleteAll() {
        playerRepo.deleteAll();
    }

    public List<Player> findAll() {
        return playerRepo.findAll();
    }
}
