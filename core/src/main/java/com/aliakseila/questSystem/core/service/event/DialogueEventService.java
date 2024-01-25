package com.aliakseila.questSystem.core.service.event;

import com.aliakseila.questSystem.core.repository.event.DialogueEventRepo;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DialogueEventService implements EventService<DialogueEvent> {

    private final DialogueEventRepo dialogueEventRepo;

    @Override
    public DialogueEvent save(DialogueEvent event) {
        return dialogueEventRepo.save(event);
    }

    @Override
    public void deleteAll() {
        dialogueEventRepo.deleteAll();
    }
}
