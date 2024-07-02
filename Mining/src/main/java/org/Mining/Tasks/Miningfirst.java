package org.Mining.Tasks;

import org.Mining.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class Miningfirst extends Task {

    public Miningfirst() {
        super();
        this.name = "Miningfirst";
    }

    Tile Tin = new Tile(3177, 3367, 0);

    @Override
    public boolean shouldExecute() {
        return Skill.Mining.realLevel() < 14 && Inventory.stream().name("Bronze pickaxe").isNotEmpty() && Tin.distance()<10;
    }

    @Override
    public void execute() {
        if (Tin.distance() > 10) {
            Movement.moveTo(Tin);
        }
        if (Inventory.stream().name("Tin ore").count() <27) {
            GameObject Ore = Objects.stream().name("Tin rocks").nearest().first();
            if (Ore.inViewport()) {
                Ore.interact("Mine");
                System.out.println("We should be mining if not we fucked up");
                Condition.wait(() -> !Ore.valid(), 150, 75);
            }
        } else if (Inventory.isFull()) {
            Game.tab(Game.Tab.INVENTORY);
            for (int i = 0; i < 28; i++) {
                Item Ore = Inventory.itemAt(i);
                if (Ore.name().contains("Tin ore")) {
                    Ore.interact("Drop");
                    System.out.println("Dropping ore");
                    Condition.sleep(Random.nextInt(150, 140));
                }
            }
        }
    }
}