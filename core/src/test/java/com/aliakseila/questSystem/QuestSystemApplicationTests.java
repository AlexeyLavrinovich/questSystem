package com.aliakseila.questSystem;

import com.aliakseila.questSystem.core.QuestSystemCoreApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = QuestSystemCoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class QuestSystemApplicationTests {

}
