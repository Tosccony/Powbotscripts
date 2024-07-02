package org.Aio.Firemaking;

import org.Aio.Task;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.dax.api.models.RunescapeBank;

public class FmWalkToBank extends Task {

    public FmWalkToBank(){
        super();
        this.name = "FmWalkToBank";
    }
    public Tile fm = new Tile(3253,3419,0);
    @Override
    public boolean shouldExecute() {
        return fm.distance() > 5 && Inventory.stream().count() < 2;
    }

    @Override
    public void execute() {
        if (fm.distance() > 5)
            System.out.println("Walking to the Bank");
        Movement.moveTo(fm);
        System.out.println("Done Movement");
        Bank.open();
    }
}
