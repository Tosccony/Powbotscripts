package org.Mining.Tasks;

import org.Mining.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class Miningsecond extends Task {

    public Miningsecond() {
        super();
        this.name = "Miningsecond";
    }

    Tile Iron = new Tile(3175, 3367, 0);

    @Override
    public boolean shouldExecute() {
        return Skill.Mining.realLevel() > 20 && Skill.Mining.realLevel() < 32 && Inventory.stream().name("Mithril pickaxe").isNotEmpty();
    }

    @Override
    public void execute() {
        if (Iron.distance() > 2) {
            Movement.moveTo(Iron);
        }
        Game.tab(Game.Tab.INVENTORY);
        if (!Inventory.isFull()) {
            GameObject Ore = Objects.stream().within(2).name("Iron rocks").nearest().first();
            if (Ore.inViewport()) {
                Ore.interact("Mine");
                System.out.println("We should be mining if not we fucked up");
                Condition.wait(() -> !Ore.valid(), 250, 150);
            }
        } else if (Inventory.isFull()) {
            for (int i = 1; i < 28; i++) {
                Item ores = Inventory.itemAt(i);
                if (ores.name().contains("ore")){
                    ores.interact("Drop");
                    System.out.println("Dropping ore");
                    Condition.sleep(Random.nextInt(150, 140));
                }
            }
        }
    }
}