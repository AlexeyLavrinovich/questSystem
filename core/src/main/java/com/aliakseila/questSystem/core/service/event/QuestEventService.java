package com.aliakseila.questSystem.core.service.event;

import com.aliakseila.questSystem.core.repository.event.QuestEventRepo;
import com.aliakseila.questSystem.model.entity.quest.event.QuestEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestEventService implements EventService<QuestEvent> {

    private final QuestEventRepo questEventRepo;

    @Override
    public QuestEvent save(QuestEvent event) {
        return questEventRepo.save(event);
    }

    @Override
    public void deleteAll() {
        questEventRepo.deleteAll();
    }
}
