package org.Aio.Thieving;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.SkillExpGainedWaiter;

import static org.powbot.api.rt4.Inventory.itemAt;

public class Thieving1to5 extends Task {

    public Thieving1to5() {
        super();
        this.name = "Thieving1to5";
    }
    public Tile Edge = new Tile(3097,3509,0);

    @Override
    public boolean shouldExecute() {
        return Skill.Thieving.realLevel() < 5 && Inventory.stream().name("Lobster").isNotEmpty();
    }

    @Override
    public void execute() {
        Npc Man = Npcs.stream().name("Man").first();
        Item Pouch = Inventory.stream().name("Coin pouch").first();
        if (Inventory.stream().name("Lobster").isNotEmpty() && Edge.distance() > 10){
            Movement.moveTo(Edge);
        }else {
            if (Edge.distance() < 10) {
                Game.tab(Game.Tab.INVENTORY);
                if (Pouch.stackSize() == 28) {
                    if (Pouch.name().contains("pouch")) {
                        Pouch.interact("Open-all");
                        Condition.wait(() -> !Pouch.interact("Open-all"), 150, 10);

                    }
                } else {
                    if (Inventory.stream().name("Coin pouch").count() < 28) {
                        if (Man.valid()) {
                            System.out.println("Stealing money from the poor");
                            Man.interact("Pickpocket");
                            Condition.wait(() -> new SkillExpGainedWaiter(Skill.Thieving).succeeded(), 150, 10);
                        }
                    }
                }
            }
        }
    }
}
