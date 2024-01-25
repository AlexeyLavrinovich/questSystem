package com.aliakseila.questSystem.core.repository.dialogue;

import com.aliakseila.questSystem.model.entity.quest.event.dialogue.DialogueOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DialogueOptionRepo extends JpaRepository<DialogueOption, Long> {

    List<DialogueOption> findAllByDialogueId(Long id);
}
