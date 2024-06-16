package org.TestLibrary.TestScript1.Tasks;

import org.TestLibrary.TestScript1.Task;
import org.TestLibrary.TestScript1.TestScript1;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;

public class WalkToNpc extends Task {


    TestScript1 main;
    public WalkToNpc(TestScript1 main){
        super();
        this.name = "WalkToNpc";
        this.main = main;
    }
    Tile Chicken = new Tile(3177, 3295, 0);
    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Lobster").isNotEmpty() && Chicken.distance() >10;
    }

    @Override
    public void execute() {
        System.out.println("Walking to the Npc");
        Movement.moveTo(Chicken);
        System.out.println("Done Movement");
    }
}
