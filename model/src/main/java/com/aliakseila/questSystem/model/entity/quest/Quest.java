package com.aliakseila.questSystem.model.entity.quest;

import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "_quest")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Double prize;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "quest_line_id")
    private QuestLine questLine;
}
