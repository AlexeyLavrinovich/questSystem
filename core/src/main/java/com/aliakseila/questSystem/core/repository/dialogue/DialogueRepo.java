package com.aliakseila.questSystem.core.repository.dialogue;

import com.aliakseila.questSystem.model.entity.quest.event.dialogue.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogueRepo extends JpaRepository<Dialogue, Long> {
}
