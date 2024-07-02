package org.Aio.Magic;

import org.Aio.Task;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class lvls1to7 extends Task {

    public lvls1to7() {
        super();
        this.name = "lvls1to7";
    }
    public Tile Chicken = new Tile(3233,3295,0);

    @Override
    public boolean shouldExecute() {
        return Skill.Magic.realLevel() <= 7 && Inventory.stream().name("Mind rune").isNotEmpty();
    }
//widget for spell = 593 - component 26
    @Override
    public void execute() {
        Item Staff = Inventory.stream().name("Staff of air").first();
        Item item = Equipment.itemAt(Equipment.Slot.MAIN_HAND);
        if(Inventory.stream().name("Staff of air").isNotEmpty()){
            System.out.println("Equipping Staff");
            Staff.interact("Wield");
            Condition.wait(()-> !Staff.valid(), 150,10);
            Game.tab(Game.Tab.EQUIPMENT);
            if (item != Item.getNil()) {
                System.out.println("Item found was " + item.name());
                if (Chicken.distance() > 5) {
                    Movement.moveTo(Chicken);
                }
            }
        }else {
            if (Movement.distance(Chicken)<10) {
                Npc Chicken = Npcs.stream().name("Chicken").first();
                if (Chicken.valid()){
                    Magic.Spell.WIND_STRIKE.cast();
                    Chicken.click();
                    Condition.sleep(1200);
                }
            }
        }
    }
}

