package com.aliakseila.questSystem.core.service.dialogue;

import com.aliakseila.questSystem.core.repository.dialogue.DialogueOptionRepo;
import com.aliakseila.questSystem.model.entity.quest.event.Event;
import com.aliakseila.questSystem.model.entity.quest.event.dialogue.DialogueOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DialogueOptionService {

    private final DialogueOptionRepo dialogueOptionRepo;

    public DialogueOption save(DialogueOption option){
        return dialogueOptionRepo.save(option);
    }

    public DialogueOption create(String text, Event event) {
        DialogueOption option = new DialogueOption();
        option.setAnswer(text);
        option.setNextEvent(event);
        return option;
    }

    public void deleteAll() {
        dialogueOptionRepo.deleteAll();
    }

    public List<DialogueOption> findByDialogueId(Long id) {
        return dialogueOptionRepo.findAllByDialogueId(id);
    }

    public List<DialogueOption> saveAll(List<DialogueOption> options) {
        return dialogueOptionRepo.saveAll(options);
    }
}
