package org.Aio.Fletching;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.SkillExpGainedWaiter;

public class Fletching1to25 extends Task {
    public Fletching1to25() {
        super();
        this.name = "Fletching1to25";
    }

    public int FletchingWidget = 270;
    public int Arrows = 14;

    @Override
    public boolean shouldExecute() {
        return Skill.Fletching.realLevel() < 25 && Inventory.stream().name("Arrow Shaft").isNotEmpty() && Inventory.stream().name("Feather").isNotEmpty();
    }
    @Override
    public void execute() {
        String itemName = "Arrow Shaft";
        String itemName2 = "Feather";
        Item Shaft = Inventory.stream().name(itemName).first();
        Item Feather = Inventory.stream().name(itemName2).first();
        if (Shaft.valid() && Feather.valid()) {
            Shaft.useOn(Feather);
            Condition.wait(() -> Widgets.widget(FletchingWidget).component(Arrows).visible(), 100, 6);
        }
        if (Widgets.widget(FletchingWidget).component(Arrows).visible()) {
            Widgets.widget(FletchingWidget).component(Arrows).click(new SkillExpGainedWaiter(Skill.Fletching));
            Condition.wait(() -> Players.local().animation() == 1, 500, 20);
        }
    }
}
