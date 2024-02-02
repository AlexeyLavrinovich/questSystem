package com.aliakseila.questSystem.core.service.event;

import com.aliakseila.questSystem.core.repository.event.QuestEventRepo;
import com.aliakseila.questSystem.core.repository.quest.QuestRepo;
import com.aliakseila.questSystem.model.entity.quest.Quest;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.QuestEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestEventService implements EventService<QuestEvent> {

    private final QuestEventRepo questEventRepo;
    private final QuestRepo questRepo;

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
        List<Quest> history = questRepo.findByQuestLineId(questLine.getId());
        Quest quest = event.getQuest();
        quest.setQuestLine(questLine);
        questRepo.save(quest);
        history.add(event.getQuest());
        questLine.setEvent(event.getNextEvent());
        return questLine;
    }

    @Override
    public QuestEvent findById(Long id) {
        return questEventRepo.findById(id).orElseThrow();
    }
}
