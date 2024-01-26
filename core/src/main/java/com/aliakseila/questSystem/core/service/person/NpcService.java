package com.aliakseila.questSystem.core.service.person;

import com.aliakseila.questSystem.core.repository.person.NpcRepo;
import com.aliakseila.questSystem.core.service.item.PocketsService;
import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.person.Status;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NpcService implements PersonService<Npc> {

    private final NpcRepo npcRepo;
    private final PocketsService pocketsService;

    @Override
    public Npc save(Npc npc) {
        return npcRepo.save(npc);
    }

    @Override
    public Npc getOrCreate(String name, double sum) {
        Optional<Npc> npc = npcRepo.findByUsername(name);
        if (npc.isEmpty()){
            return createAndSave(name, sum);
        }
        return npc.orElseThrow();
    }



    @Override
    public Npc createAndSave(String name, double sum){
        Npc npc = new Npc();
        npc.setUsername(name);
        npc.setPockets(pocketsService.create(sum));
        npc.setStatus(Collections.singleton(Status.ALIVE));
        return save(npc);
    }

    @Override
    public Npc getByUsername(String name) {
        return npcRepo.findByUsername(name).orElseThrow();
    }

    @Override
    public void deleteAll() {
        npcRepo.deleteAll();
    }
}
