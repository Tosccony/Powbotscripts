package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;


public class WalkToBank extends Task {

    public WalkToBank(){
        super();
        this.name = "WalkToBank";
    }
    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Guam leaf").isEmpty() && Bank.nearest().distance() >10;
    }

    @Override
    public void execute() {
        System.out.println("Walking to the Bank");
        Movement.moveToBank();
        System.out.println("Done Movement");
    }
}