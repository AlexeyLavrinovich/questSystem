package com.aliakseila.questSystem.core.service.event;

import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.Event;
import org.springframework.stereotype.Service;

@Service
public interface EventService <T extends Event> {

    T save (T event);

    void deleteAll();

    QuestLine trigger(T event, QuestLine questLine);

    T findById(Long id);
}
