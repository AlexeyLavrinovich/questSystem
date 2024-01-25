package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.quest.QuestLineRepo;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestLineService {

    private final QuestLineRepo questLineRepo;

    public void deleteAll() {
        questLineRepo.deleteAll();
    }

    public void save(QuestLine questLine) {
        questLineRepo.save(questLine);
    }
}
