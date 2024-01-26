package com.aliakseila.questSystem.core.service.event;

import com.aliakseila.questSystem.core.repository.event.DialogueEventRepo;
import com.aliakseila.questSystem.core.service.dialogue.DialogueOptionService;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import com.aliakseila.questSystem.model.entity.quest.event.Event;
import com.aliakseila.questSystem.model.entity.quest.event.dialogue.DialogueOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DialogueEventService implements EventService<DialogueEvent> {

    private final DialogueEventRepo dialogueEventRepo;
    private final DialogueOptionService dialogueOptionService;

    @Override
    public DialogueEvent save(DialogueEvent event) {
        return dialogueEventRepo.save(event);
    }

    @Override
    public void deleteAll() {
        dialogueEventRepo.deleteAll();
    }

    @Override
    public QuestLine trigger(DialogueEvent event, QuestLine questLine) {
        System.out.println(event.getDialogue().getText());
        dialogueOptionService.findByDialogueId(event.getDialogue().getId()).forEach(o -> System.out.printf("%d %s%n", o.getId(), o.getAnswer()));
        return questLine;
    }

    @Override
    public DialogueEvent findById(Long id) {
        return dialogueEventRepo.findById(id).orElseThrow();
    }

    public Event chooseOption(Long dialogueOptionId) {
        return dialogueOptionService.findById(dialogueOptionId).getNextEvent();
    }

    public boolean isDialogueEvent(Long id) {
        return dialogueEventRepo.existsById(id);
    }
}
