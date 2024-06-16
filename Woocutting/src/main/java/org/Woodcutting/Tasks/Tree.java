package org.Woodcutting.Tasks;

import org.Woodcutting.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class Tree extends Task {

    public Tree() {
        super();
        this.name = "Oak";
    }

    Tile tree_oak = new Tile(3165, 3416, 0);

    @Override
    public boolean shouldExecute() {
        var lvl1 = Skill.Woodcutting.realLevel();
        return Skill.Woodcutting.realLevel() < 15 && Inventory.stream().name("Bronze axe").isNotEmpty();

    }

    @Override
    public void execute() {
        if (tree_oak.distance() > 10) ; {
            Movement.moveTo(tree_oak); }
        if (Inventory.stream().name("Logs").count() <27) {
            GameObject Tree = Objects.stream().name("Tree").nearest().first();
            if (Tree.inViewport()) {
                Tree.click();
                System.out.println("We should be chopping if not we fucked up");
                Condition.wait(() -> !Tree.valid(), 250, 150);
            }
        } else if (Inventory.isFull()) {
            for (int i = 0; i < 28; i++) {
                Item log = Inventory.itemAt(i);
                if (log.name().contains("Logs")) {
                    log.interact("Drop");
                    System.out.println("Dropping logs");
                    Condition.sleep(Random.nextInt(150, 140));
                }
            }
        }
    }
}
