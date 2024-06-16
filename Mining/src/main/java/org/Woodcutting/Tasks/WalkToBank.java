package org.Woodcutting.Tasks;

import org.Woodcutting.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class WalkToBank extends Task {

    public WalkToBank(){
        super();
        this.name = "WalkToBank";
    }
    @Override
    public boolean shouldExecute() {
        return Skill.Woodcutting.realLevel() == 25 && Inventory.stream().name("Bronze axe").isNotEmpty()
                || Skill.Woodcutting.realLevel() == 31 && Inventory.stream().name("Mithril axe").isNotEmpty()
                || Skill.Woodcutting.realLevel() == 41 && Inventory.stream().name("Adamant axe").isNotEmpty();
    }

    @Override
    public void execute() {
        System.out.println("Walking to the Bank");
        Movement.moveToBank();
        System.out.println("Done Movement");
        Bank.open();
    }
}