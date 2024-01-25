package com.aliakseila.questSystem.core.service.dialogue;

import com.aliakseila.questSystem.core.repository.dialogue.DialogueOptionRepo;
import com.aliakseila.questSystem.model.entity.quest.event.Event;
import com.aliakseila.questSystem.model.entity.quest.event.dialogue.DialogueOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DialogueOptionService {

    private final DialogueOptionRepo dialogueOptionRepo;

    public DialogueOption save(DialogueOption option){
        return dialogueOptionRepo.save(option);
    }

    public DialogueOption createAndSave(String text, Event event) {
        DialogueOption option = new DialogueOption();
        option.setAnswer(text);
        option.setNextEvent(event);
        return save(option);
    }

    public void deleteAll() {
        dialogueOptionRepo.deleteAll();
    }
}
