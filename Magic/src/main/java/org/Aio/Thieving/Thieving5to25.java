package org.Aio.Thieving;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.event.GameActionEvent;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import java.util.ArrayList;

public class Thieving5to25 extends Task {
    public Thieving5to25() {
        super();
        this.name = "lvls5to25";
    }
    ArrayList<Thieving5to25> stallitems = new ArrayList<>();
    public Tile Ardy = new Tile(2669,3310,0);
    @Override
    public boolean shouldExecute() {
        return Skill.Thieving.realLevel() > 5 && Skill.Thieving.realLevel() < 26;
    }

    @Override
    public void execute() {
        Item Pouch = Inventory.stream().name("Coin pouch").first();
        Item Bread = Inventory.stream().name("Bread").first();
        Item Choco = Inventory.stream().name("Chocolate slice").first();
        Item Cake = Inventory.stream().name("Cake").first();
        GameObject Baker = Objects.stream().within(2).id(11730).nearest().first();
        if (Ardy.distance() > 2) {
            if (Pouch.stackSize() > 0) {
                Pouch.interact("Open-all");
            }
            Movement.moveTo(Ardy);
        }else {
            if (Ardy.distance() < 2) {
                if (Baker.inViewport()) {
                    Baker.interact("Steal-from");
                    Condition.wait(() -> !Baker.valid(), 150, 10);
                    Inventory.drop(Bread, true);
                    Inventory.drop(Choco, true);
                    Inventory.drop(Cake, true);
                }
            }
        }
    }
}
