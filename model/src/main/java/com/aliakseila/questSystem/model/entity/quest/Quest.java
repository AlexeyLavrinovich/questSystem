package com.aliakseila.questSystem.model.entity.quest;

import com.aliakseila.questSystem.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @OneToOne
    @JoinColumn(name = "creator_id")
    @ToString.Exclude
    protected User creator;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    @ToString.Exclude
    protected User executor;


    public void passTheQuest(){
        creator.pay(prize);
        executor.earn(prize);
    }

}
