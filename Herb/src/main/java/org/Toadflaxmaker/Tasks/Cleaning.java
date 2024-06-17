package org.Toadflaxmaker.Tasks;

import org.Toadflaxmaker.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;


public class Cleaning extends Task {

    public Cleaning() {
        super();
        this.name = "Cleaning";
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Grimy toadflax").isNotEmpty();
    }

    @Override
    public void execute() {
        Game.tab(Game.Tab.INVENTORY);
        for (int i = 0; i < 28; i++) {
            Item herb = Inventory.itemAt(i);
            if (herb.name().contains("Grimy")) {
                herb.interact("Clean");
                System.out.println("Cleaning herbs");
                Condition.sleep(Random.nextInt(175, 225));
            }
        }
    }
}



