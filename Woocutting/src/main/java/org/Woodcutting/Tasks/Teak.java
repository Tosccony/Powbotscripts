package org.Woodcutting.Tasks;

import org.Woodcutting.Task;
import org.Woodcutting.Woodcutting;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.dax.api.DaxWalker;

public class Teak extends Task {
    Woodcutting main;

    public Teak() {
        super();
        this.name = "Oak";
    }

    Tile Teak = new Tile(2334, 3048, 0);

    @Override
    public boolean shouldExecute() {
        var lvl1 = Skill.Woodcutting.realLevel();
        return Skill.Woodcutting.realLevel() > 34 && Skill.Woodcutting.realLevel() < 41 && Inventory.stream().name("Adamant axe").isNotEmpty()
                || Skill.Woodcutting.realLevel() > 40 && Skill.Woodcutting.realLevel() < 70 && Inventory.stream().name("Rune axe").isNotEmpty();

    }

    @Override
    public void execute() {
        if (Teak.distance() > 10) ; {
            //Movement.findPath(Teak);
            Movement.moveTo(Teak);
            //DaxWalker.walkTo(Teak);
        }
        if (Inventory.stream().name("Teak logs").count() <27) {
            GameObject Tree = Objects.stream().name("Teak Tree").nearest().first();
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
