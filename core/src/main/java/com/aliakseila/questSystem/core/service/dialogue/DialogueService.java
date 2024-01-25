package com.aliakseila.questSystem.core.service.dialogue;

import com.aliakseila.questSystem.core.repository.dialogue.DialogueRepo;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import com.aliakseila.questSystem.model.entity.quest.event.dialogue.Dialogue;
import com.aliakseila.questSystem.model.entity.quest.event.dialogue.DialogueOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DialogueService {

    private final DialogueRepo dialogueRepo;

    public Dialogue save(Dialogue dialogue){
        return dialogueRepo.save(dialogue);
    }

    public Dialogue createAndSave(String text, List<DialogueOption> options) {
        Dialogue dialogue = new Dialogue();
        dialogue.setText(text);
        dialogue.setOptions(options);
        return save(dialogue);
    }

    public void deleteAll() {
        dialogueRepo.deleteAll();
    }
}
