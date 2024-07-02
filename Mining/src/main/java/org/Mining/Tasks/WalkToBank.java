package org.Mining.Tasks;

import org.Mining.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;

public class WalkToBank extends Task {

    public WalkToBank(){
        super();
        this.name = "WalkToBank";
    }
    @Override
    public boolean shouldExecute() {
        return Skill.Mining.realLevel() == 21 && Inventory.stream().name("Bronze pickaxe").isNotEmpty()
                || Skill.Mining.realLevel() == 31 && Inventory.stream().name("Adamant pickaxe").isEmpty()
                || Skill.Mining.realLevel() == 41 && Inventory.stream().name("Rune pickaxe").isEmpty()
                && Bank.nearest().distance()> 5;
    }

    @Override
    public void execute() {
        if (Bank.nearest().distance()> 5)
        System.out.println("Walking to the Bank");
        Movement.moveToBank();
        System.out.println("Done Movement");
        Bank.open();
    }
}