package org.Aio.Thieving;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

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
        Item Apple = Inventory.stream().name("Cooking apple").first();
        Item Banana = Inventory.stream().name("Banana").first();
        Item Straw = Inventory.stream().name("Strawberry").first();
        Item Jang = Inventory.stream().name("Jangerberries").first();
        Item Lemon = Inventory.stream().name("Lemon").first();
        Item Red = Inventory.stream().name("Redberries").first();
        Item Pine = Inventory.stream().name("Pineapple").first();
        Item Lime = Inventory.stream().name("Lime").first();
        Item Strange = Inventory.stream().name("Strange fruit").first();
        Item Gol = Inventory.stream().name("Golovanova fruit top").first();
        Item Pap = Inventory.stream().name("Papaya fruit").first();
        Item Bread = Inventory.stream().name("Bread").first();
        Item Choco = Inventory.stream().name("Chocolate slice").first();
        Item Cake = Inventory.stream().name("Cake").first();
        if (Kour.distance() > 6) {
            Movement.moveTo(Kour);
        }else {
            if (Kour.distance() < 3) {
                if (Fruit.inViewport()) {
                    Fruit.interact("Steal-from");
                    Condition.wait(() -> !Fruit.valid(), 150, 10);
                    Inventory.drop(Apple, true);
                    Inventory.drop(Banana, true);
                    Inventory.drop(Straw, true);
                    Inventory.drop(Jang, true);
                    Inventory.drop(Lemon, true);
                    Inventory.drop(Red, true);
                    Inventory.drop(Pine, true);
                    Inventory.drop(Lime, true);
                    Inventory.drop(Strange, true);
                    Inventory.drop(Gol, true);
                    Inventory.drop(Pap, true);
                    Inventory.drop(Bread, true);
                    Inventory.drop(Choco, true);
                    Inventory.drop(Cake, true);
                }
            }
        }
    }
}