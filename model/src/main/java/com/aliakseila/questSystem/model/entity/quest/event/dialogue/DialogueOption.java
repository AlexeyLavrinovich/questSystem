package com.aliakseila.questSystem.model.entity.quest.event.dialogue;

import com.aliakseila.questSystem.model.entity.quest.event.QuestEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_dialogue_option")
public class DialogueOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;

    @OneToOne
    @JoinColumn(name = "event_id")
    private QuestEvent nextEvent;

}
