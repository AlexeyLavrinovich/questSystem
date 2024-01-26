package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.quest.QuestLineRepo;
import com.aliakseila.questSystem.core.service.event.DialogueEventService;
import com.aliakseila.questSystem.core.service.event.EventService;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestLineService {

    private final QuestLineRepo questLineRepo;
    private final DialogueEventService dialogueEventService;

    public void deleteAll() {
        questLineRepo.deleteAll();
    }

    public QuestLine save(QuestLine questLine) {
        return questLineRepo.save(questLine);
    }

    public List<QuestLine> getQuestLinesByPlayerId(Long npcId){
        return questLineRepo.findByOwnerIdAndExecutorIsNull(npcId);
    }

    public QuestLine trigger(QuestLine questLine){
        return dialogueEventService.trigger(questLine.getEvent(), questLine);
    }
}
