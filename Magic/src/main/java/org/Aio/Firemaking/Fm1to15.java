package org.Aio.Firemaking;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.event.SkillExpGainedEvent;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.SkillExpGainedWaiter;


public class Fm1to15 extends Task {

    public Fm1to15() {
        super();
        this.name = "Fm1to15";
    }
    public Tile logs = new Tile(3249,3428,0);
    @Override
    public boolean shouldExecute() {
        return Inventory.stream().name("Tinderbox").isNotEmpty() && Inventory.stream().name("Logs").isNotEmpty();
    }

    @Override
    public void execute() {
        if (Inventory.stream().name("Logs").isNotEmpty()){
            Movement.moveTo(logs);

        }
        if (logs.distanceTo(logs) < 10) {
            Game.tab(Game.Tab.INVENTORY);
            Item Logs = Inventory.stream().name("Logs").first();
            Item Tinder = Inventory.stream().name("Tinderbox").first();
            if (logs.valid()) {
                Tinder.interact("Use");
                Logs.click();
                System.out.println("Making fire");
                Condition.wait(() -> Players.local().animation() != 1 && !Players.local().inMotion(), 2000, 20);
            }
        }
    }
}
