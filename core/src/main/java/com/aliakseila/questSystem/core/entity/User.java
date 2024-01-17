package com.aliakseila.questSystem.core.entity;

import com.aliakseila.questSystem.core.entity.quest.Quest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Double money;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "_user_status", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "executor")
    private List<Quest> quests;

    public void pay(Double sum){
        this.money -= sum;
    }

    public void earn(Double sum){
        this.money += sum;
    }
}
