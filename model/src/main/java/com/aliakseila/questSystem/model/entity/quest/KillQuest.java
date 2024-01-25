package com.aliakseila.questSystem.model.entity.quest;


import com.aliakseila.questSystem.model.entity.person.Person;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("com.aliakseila.questSystem.model.entity.quest.KillQuest")
public class KillQuest extends Quest {

    @OneToOne
    private Person target;

}
