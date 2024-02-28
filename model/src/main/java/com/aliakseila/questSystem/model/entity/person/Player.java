package com.aliakseila.questSystem.model.entity.person;

import com.aliakseila.questSystem.model.entity.quest.questLine.PlayerQuestLine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("com.aliakseila.questSystem.model.entity.person.Player")
public class Player extends Person {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "player")
    private List<PlayerQuestLine> questLines;

}
