package com.aliakseila.questSystem.core.service.quest.questLine;

import com.aliakseila.questSystem.core.repository.quest.questLine.QuestLineNodeRepo;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.quest.questLine.EmbeddedQuestLineNodeId;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLineNode;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLineStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestLineNodeService {

    private final QuestLineNodeRepo questLineNodeRepo;

    public QuestLineNode save(QuestLineNode node){
        return questLineNodeRepo.save(node);
    }

    public QuestLineNode getOrCreate(Player player, QuestLine questLine) {
        Optional<QuestLineNode> node = questLineNodeRepo.findAllById_QuestLineId(questLine.getId())
                .stream()
                .filter(questLineNode -> !questLineNode.getStatus().equals(QuestLineStatus.COMPLETED))
                .filter(questLineNode -> questLineNode.getPlayer().getId().equals(player.getId()))
                .findFirst();
        if(node.isEmpty()){
            return createAndSave(player, questLine);
        }
        return node.orElseThrow();
    }

    private QuestLineNode createAndSave(Player player, QuestLine questLine) {
        QuestLineNode questLineNode = new QuestLineNode();
        questLineNode.setId(new EmbeddedQuestLineNodeId(questLine.getId(), player.getId()));
        questLineNode.setQuestLine(questLine);
        questLineNode.setPlayer(player);
        questLineNode.setStatus(QuestLineStatus.IN_PROGRESS);
        return save(questLineNode);
    }

    public List<QuestLineNode> getByQuestLineId(Long id) {
        return questLineNodeRepo.findAllById_QuestLineId(id);
    }

    public List<QuestLineNode> getByPlayerId(Long id) {
        return questLineNodeRepo.findAllById_PlayerId(id);
    }

    public List<QuestLine> getQuestLinesByPlayer(Player player) {
        return getByPlayerId(player.getId())
                .stream()
                .map(QuestLineNode::getQuestLine)
                .collect(Collectors.toList());
    }
}
