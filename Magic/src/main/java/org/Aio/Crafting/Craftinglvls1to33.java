package org.Aio.Crafting;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class Craftinglvls1to33 extends Task {

    public Craftinglvls1to33() {
        super();
        this.name = "Craftinglvls1to33";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Crafting.realLevel()<32 && Inventory.stream().name("Glassblowing pipe").isNotEmpty() && Inventory.stream().name("Molten glass").isNotEmpty();
    }
    public int Craftingbeer = 270;
    public int beer = 14;

    @Override
    public void execute() {
        Game.tab(Game.Tab.INVENTORY);
        String itemName = "Glassblowing pipe";
        String itemName2 = "Molten glass";
        Item Pipe = Inventory.stream().name(itemName).first();
        Item Glass = Inventory.stream().name(itemName2).first();
        if (Pipe.valid() && Glass.valid()) {
            Pipe.useOn(Glass);
            Condition.wait(() -> Widgets.widget(Craftingbeer).valid(), 100, 6);
            if (Widgets.component(270,14,38).visible()) {
                Widgets.component(270,14,38).click();
                Condition.wait(() -> Inventory.stream().name("Molten glass").count() == 0, 100, 125);
            }
        }
    }
}

