package org.Aio.Hunter;

import org.Aio.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.dax.api.models.RunescapeBank;

public class HuntingWalktoBank extends Task {

    public HuntingWalktoBank(){
        super();
        this.name = "HuntingWalktoBank";
    }
    @Override
    public boolean shouldExecute() {
        return  Inventory.isEmpty() || Skill.Hunter.realLevel() == 9 && Inventory.stream().name("Bird snare").isEmpty()
                || Skill.Hunter.realLevel() ==29 && Inventory.stream().name("Rope").isEmpty();
    }

    @Override
    public void execute() {
        System.out.println("Walking to the Bank");
        Movement.moveToBank(RunescapeBank.GRAND_EXCHANGE);
        System.out.println("Done Movement");
        Bank.open();
    }
}

