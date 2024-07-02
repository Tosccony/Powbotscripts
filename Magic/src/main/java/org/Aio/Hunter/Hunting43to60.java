
package org.Aio.Hunter;

import org.Aio.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.SkillExpGainedWaiter;

import java.util.List;

public class Hunting43to60 extends Task {

    public Hunting43to60() {
        super();
        this.name = "Hunting29to43";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Hunter.realLevel() >= 43 && Skill.Hunter.realLevel() < 60 && Inventory.stream().name("Coins").isNotEmpty();
    }

    static Tile FalconStart = new Tile(2371, 3621, 0);
    static Tile faltop = new Tile(2366, 3619, 0);
    static Tile falbot = new Tile(2398, 3572, 0);
    public static Area Falcon = new Area(faltop, falbot);
    Tile Falcondrop = new Tile(3544, 3451, 0);
    static Tile Falconsecondstart = new Tile(2373, 3610, 0);
    static Tile Spotted = new Tile(2379, 3584, 0);
    public String Birdgiver = "Matthias";
    public String Spotted_Kebs = "Spotted kebbit";
    public String Dark_Kebs = "Dark kebbit";
    public String GyrFalcon = "Gyr falcon";

    // static Tile Swamp3 = new Tile(2611, 2929, 0);
    int Stile = 19222;
    // int net = 303;


    @Override
    public void execute() {
        Item fal_port = Inventory.stream().name("Piscatoris teleport").first();
        Npc Spotted_Kebbits = Npcs.stream().within(7).name(Spotted_Kebs).first();
        Npc Dark_Kebbits = Npcs.stream().within(5).name(Dark_Kebs).first();
        Npc Gyr_Falcon = Npcs.stream().within(Falcon).name(GyrFalcon).first();
        GameObject Stileobj = Objects.stream().within(10).id(19222).first();
        Game.tab(Game.Tab.INVENTORY);
        if (!Falcon.contains(Players.local())) {
            if (FalconStart.distance() > 10) {
                System.out.println("Teleporting");
                fal_port.interact("Teleport");
                Condition.sleep(Random.nextInt(2500, 3000));
                System.out.println("Moving to Falcon area");
                Movement.moveTo(FalconStart);
            }
            if (FalconStart.distance() < 10 && Objects.stream().id(Stile).first().valid()) {
                Stileobj.interact("Climb-over");
                Condition.sleep(Random.nextInt(5000, 6000));
                Movement.moveTo(Falconsecondstart);
            }
        }
        if (Falcon.contains(Players.local())) {
            System.out.println("Within Falcon area");
            if (!Equipment.itemAt(Equipment.Slot.MAIN_HAND).valid()) {
                Npc Matthias = Npcs.stream().name(Birdgiver).first();
                if (Matthias.inViewport()) {
                    Matthias.interact("Quick-falcon");
                    Condition.sleep(5000);
                    //Condition.wait(() ->)
                }
            } else {
                if (Equipment.itemAt(Equipment.Slot.MAIN_HAND).valid()) {
                    if (Spotted.distance() > 10) {
                        Movement.moveTo(Spotted);
                        Condition.wait(() -> Spotted.distance() < 10, 150, 10);
                    } else {
                        if (!Gyr_Falcon.valid() && Spotted.distance() < 15) {
                            Spotted_Kebbits.interact("Catch");
                            Condition.wait(() -> !Spotted_Kebbits.valid(), 350, 10);
                        }
                        if (Gyr_Falcon.valid()) {
                            Gyr_Falcon.interact("Retrieve");
                            Condition.wait(() -> !Gyr_Falcon.valid(), 350, 10);
                        } else {
                            if (Inventory.stream().name("Bones").count() > 5) {
                                List<Item> itemsToDrop = Inventory.stream().name("Bones", "Spotted kebbit fur", "Dark kebbit fur").list();
                                Inventory.drop(itemsToDrop);
                            }
                        }
                    }
                }
            }
        }
    }
}