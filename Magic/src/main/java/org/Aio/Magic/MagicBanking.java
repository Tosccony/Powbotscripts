package org.Aio.Magic;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.walking.model.Skill;

public class MagicBanking extends Task {

    public MagicBanking() {
        super();
        this.name = "Banking";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Magic.realLevel() <54 || Inventory.isEmpty() || !new lvls1to7().shouldExecute() || !new lvls7to25().shouldExecute() && Bank.nearest().distance()<5;
    }
    @Override
    public void execute() {
        Item water = Inventory.stream().name("Staff of water").first();
        if (!Bank.opened()){
            Bank.open();
        }
        if(Bank.opened()) {
            if (Skill.Magic.realLevel() < 7) {
                if (Bank.withdraw("Staff of air", 1)) {
                    if (Bank.withdraw("Mind rune", Bank.Amount.ALL)) {
                        Condition.wait(() -> Inventory.stream().name("Staff of air").isNotEmpty(), 150, 10);
                    }
                }
            }
            if (Skill.Magic.realLevel() >= 7 && Skill.Magic.realLevel() <= 55) {
                Bank.depositAllExcept("Cosmic rune");
                if (Bank.withdraw("Staff of water", 1)) {
                    Inventory.stream().name("Staff of water").isNotEmpty();
                    //Condition.wait(() -> Inventory.stream().name("Staff of water").isNotEmpty(), 150, 10);
                    water.interact("Wield");
                } else {
                    if (Bank.withdraw("Cosmic rune", Bank.Amount.ALL)) {
                    }
                    if (Bank.withdraw("Sapphire ring", 27)) {
                        // Condition.wait(() ->Inventory.stream().name("Sapphire ring").isNotEmpty(), 150,10);
                    }
                }
                if (Inventory.isNotEmpty()){
                    Bank.close();
                }
            }
        }
    }
}


