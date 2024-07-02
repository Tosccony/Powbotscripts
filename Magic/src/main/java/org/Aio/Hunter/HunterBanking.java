package org.Aio.Hunter;

import org.Aio.Magic.lvls1to7;
import org.Aio.Magic.lvls7to25;
import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;



public class HunterBanking extends Task {

    public HunterBanking() {
        super();
        this.name = "HunterBanking";
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.isEmpty() || Skill.Hunter.realLevel() == 9 && Inventory.stream().name("Bird snare").isEmpty()
                || Skill.Hunter.realLevel() ==29 && Inventory.stream().name("Rope").isEmpty();
    }
    @Override
    public void execute() {
        if (!Bank.opened()){
            Bank.open();
        }
        if(Bank.opened()) {
            Bank.depositInventory();
            Bank.depositEquipment();
            if (Skill.Hunter.realLevel() < 29) {
                if (Bank.withdraw("Bird snare", 10)) {
                    Condition.wait(() -> Inventory.stream().name("Bird snare").isNotEmpty(), 150, 10);
                }
            }
            if (Skill.Hunter.realLevel() >= 29 && Skill.Hunter.realLevel() < 60) {
                if (Bank.withdraw("Rope", 5)) {
                    System.out.println("Withdrawing Ropes");
                    Condition.wait(() -> Inventory.stream().name("Rope").isNotEmpty(), 150, 10);
                }
                if (Bank.withdraw("Small fishing net", 5)) {
                    System.out.println("Withdrawing nets");
                    Condition.wait(() -> Inventory.stream().name("Small fishing net").isNotEmpty(), 150, 10);
                }
            }
            if (Inventory.isNotEmpty()){
                Bank.close();
            }
        }
    }
}

