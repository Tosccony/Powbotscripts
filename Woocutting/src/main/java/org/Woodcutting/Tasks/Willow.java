package org.Woodcutting.Tasks;

import org.Woodcutting.Task;
import org.Woodcutting.Woodcutting;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class Willow extends Task {
    Woodcutting main;

    public Willow() {
        super();
        this.name = "Oak";
    }

    Tile Willow = new Tile(3220, 3307, 0);

    @Override
    public boolean shouldExecute() {
        var lvl1 = Skill.Woodcutting.realLevel();
        return Skill.Woodcutting.realLevel() > 35 && Skill.Woodcutting.realLevel() < 30 && Inventory.stream().name("Mithril axe").isNotEmpty()
               || Skill.Woodcutting.realLevel() > 30 && Skill.Woodcutting.realLevel() < 35 && Inventory.stream().name("Adamant axe").isNotEmpty();

    }

    @Override
    public void execute() {
        if (Willow.distance() > 10) ; {
            Movement.moveTo(Willow);
        }
        if (Inventory.stream().name("Willow logs").count() <27) {
            GameObject Tree = Objects.stream().name("Willow Tree").nearest().first();
            if (Tree.inViewport()) {
                Tree.click();
                System.out.println("We should be chopping if not we fucked up");
                Condition.wait(() -> !Tree.valid(), 250, 150);
            }
        } else if (Inventory.isFull()) {
                for (int i = 0; i < 28; i++) {
                    Item log = Inventory.itemAt(i);
                    if (log.name().contains("log")) {
                        log.interact("Drop");
                        System.out.println("Dropping logs");
                        Condition.sleep(Random.nextInt(150, 140));
                    }
                }
        }
    }
}

