package com.aliakseila.questSystem.core.service.event;

import com.aliakseila.questSystem.core.repository.event.QuestEventRepo;
import com.aliakseila.questSystem.core.service.quest.QuestLineService;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.quest.Quest;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.QuestEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestEventService implements EventService<QuestEvent> {

    private final QuestEventRepo questEventRepo;
    private final QuestLineService questLineService;

    @Override
    public QuestEvent save(QuestEvent event) {
        return questEventRepo.save(event);
    }

    @Override
    public void deleteAll() {
        questEventRepo.deleteAll();
    }

    @Override
    public QuestLine trigger(QuestEvent event, QuestLine questLine) {
        List<Quest> history = questLine.getHistory();
        Quest quest = event.getQuest();
        history.add(quest);
        questLine.setEvent(event.getNextEvent());
        return questLineService.save(questLine);
    }
}
