package org.Aio.Thieving;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import java.util.List;

public class Thieving25to55 extends Task {

    public Thieving25to55() {
        super();
        this.name = "lvls5to25";
    }
    public Tile Kour = new Tile(1798,3608,0);
    @Override
    public boolean shouldExecute() {
        return Skill.Thieving.realLevel() > 24 && Skill.Thieving.realLevel() < 55;
    }
    @Override
    public void execute() {
        GameObject Fruit = Objects.stream().within(2).id(28823).nearest().first();
        if (Kour.distance() > 6) {
            Movement.moveTo(Kour);
        }else {
            if (Kour.distance() < 3) {
                if (Fruit.inViewport()) {
                    Fruit.interact("Steal-from");
                    Condition.wait(() -> !Fruit.valid(), 150, 10);
                }
                if (Inventory.isFull()) {
                    List<Item> Dropthieve = Inventory.stream().name("Cooking apple", "Banana", "Strawberry", "Jangerberries", "Lemon", "Redberries", "Pineapple",
                            "Lime", "Strange fruit", "Golovanova fruit top", "Papaya fruit", "Bread", "Chocolate slice", "Cake").list();
                    Inventory.drop(Dropthieve);
                }
            }
        }
    }
}