package org.Woodcutting.Tasks;

import org.Woodcutting.Task;
import org.powbot.api.Condition;
import org.powbot.api.Input;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.walking.model.Skill;

public class Bankz extends Task {

    public Bankz() {
        super();
        this.name = "Banking";
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.isEmpty() || Skill.Woodcutting.realLevel() == 25 && Inventory.stream().name("Bronze axe").isNotEmpty()
                || Skill.Woodcutting.realLevel() == 31 && Inventory.stream().name("Mithril axe").isNotEmpty()
                || !new Tree().shouldExecute() && !new Oak().shouldExecute() && !new Willow().shouldExecute() && !new Teak().shouldExecute();
    }

    @Override
    public void execute() {
        System.out.println("Opening bank");
        if (Bank.open()) {
            Bank.depositInventory();
            System.out.println("Finding Items");
            if (Skill.Woodcutting.realLevel() < 15) {
                Bank.withdraw("Bronze axe", 1);
                Condition.wait(() -> Inventory.stream().name("Bronze axe").isNotEmpty(), 250, 10);
            }
            if (Skill.Woodcutting.realLevel() == 21) {
                Bank.withdraw("Mithril axe", 1);
                Condition.wait(() -> Inventory.stream().name("Mithril axe").isNotEmpty(), 250, 10);
            }
            if (Skill.Woodcutting.realLevel() == 31) {
                Bank.withdraw("Adamant axe", 1);
                Condition.wait(() -> Inventory.stream().name("Adamant axe").isNotEmpty(), 250, 10);
            }
            if (Skill.Woodcutting.realLevel() == 41) {
                Bank.withdraw("Rune axe", 1);
                Condition.wait(() -> Inventory.stream().name("Rune axe").isNotEmpty(), 250, 10);
            }
        }
        if (Bank.opened() && !shouldExecute()) {
            if (Random.nextBoolean()) {
                System.out.println("Banking.close()");
                Bank.close();
            } else {
                System.out.println("Input.back()");
                Input.back();
            }
        }
    }
}