package com.aliakseila.questSystem.core.service.quest.questLine;

import com.aliakseila.questSystem.core.repository.quest.questLine.PlayerQuestLineRepo;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.quest.questLine.EmbeddedQuestLineNodeId;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.questLine.PlayerQuestLine;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLineStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerQuestLineService {

    private final PlayerQuestLineRepo playerQuestLineRepo;

    public PlayerQuestLine save(PlayerQuestLine node){
        return playerQuestLineRepo.save(node);
    }

    public PlayerQuestLine getOrCreate(Player player, QuestLine questLine) {
        Optional<PlayerQuestLine> node = playerQuestLineRepo.findAllById_QuestLineId(questLine.getId())
                .stream()
                .filter(questLineNode -> !questLineNode.getStatus().equals(QuestLineStatus.COMPLETED))
                .filter(questLineNode -> questLineNode.getPlayer().getId().equals(player.getId()))
                .findFirst();
        if(node.isEmpty()){
            return createAndSave(player, questLine);
        }
        return node.orElseThrow();
    }

    private PlayerQuestLine createAndSave(Player player, QuestLine questLine) {
        PlayerQuestLine playerQuestLine = new PlayerQuestLine();
        playerQuestLine.setId(new EmbeddedQuestLineNodeId(questLine.getId(), player.getId()));
        playerQuestLine.setQuestLine(questLine);
        playerQuestLine.setPlayer(player);
        playerQuestLine.setStatus(QuestLineStatus.IN_PROGRESS);
        return save(playerQuestLine);
    }

    public List<PlayerQuestLine> getByQuestLineId(Long id) {
        return playerQuestLineRepo.findAllById_QuestLineId(id);
    }

    public List<QuestLine> getByPlayerId(Long id) {
        return playerQuestLineRepo.findQuestLineById_PlayerId(id);
    }

    public List<QuestLine> getQuestLinesByPlayer(Player player) {
        return getByPlayerId(player.getId());
    }
}
