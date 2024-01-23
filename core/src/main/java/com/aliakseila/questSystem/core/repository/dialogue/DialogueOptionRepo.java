package com.aliakseila.questSystem.core.repository.dialogue;

import com.aliakseila.questSystem.model.entity.quest.event.dialogue.DialogueOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogueOptionRepo extends JpaRepository<DialogueOption, Long> {
}
