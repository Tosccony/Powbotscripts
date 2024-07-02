package org.Aio.Magic;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.SkillExpGainedWaiter;

public class lvls7to25 extends Task {

    public lvls7to25() {
        super();
        this.name = "lvls7to25";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Magic.realLevel() >= 7 && Skill.Magic.realLevel() <= 55 && Inventory.stream().name("Cosmic rune").isNotEmpty()
                && Inventory.stream().name("Sapphire ring").isNotEmpty();
    }

    @Override
    public void execute() {
        Item water = Inventory.stream().name("Staff of water").first();
       // Item Ring = Inventory.stream().name("Sapphire ring").first();
        if (Inventory.stream().name("Staff of water").isNotEmpty()) {
            Condition.wait(() -> Inventory.stream().name("Staff of water").isNotEmpty(), 150, 10);
            water.interact("Wield");
        } else {
            if (Inventory.stream().name("Sapphire ring").isNotEmpty()) {
               for (int i = 1; i < 28; i++) {
                   Item Ring = Inventory.itemAt(i);
                   if (Ring.name().contains("ring")) {
                   Magic.Spell.ENCHANT_LEVEL_1_JEWELLERY.cast("Cast");
                   Magic.Spell.ENCHANT_LEVEL_1_JEWELLERY.canCast();
                   Ring.click(new SkillExpGainedWaiter(Skill.Magic));
                   }
               }
            }
        }
    }
}


