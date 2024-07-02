package org.Aio.Hunter;

import org.Aio.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;



public class Hunting29to43 extends Task {

    public Hunting29to43() {
        super();
        this.name = "Hunting29to43";
    }

    @Override
    public boolean shouldExecute() {
        return Skill.Hunter.realLevel() > 29 && Skill.Hunter.realLevel() < 43 && Inventory.stream().name("Small fishing net").isNotEmpty() && Inventory.stream().name("Rope").isNotEmpty();
    }
    static Tile Swampstart = new Tile(3551, 3451, 0);
    static Tile Swamptop = new Tile (3555, 3448, 0);
    static Tile Swampbot = new Tile (3547, 3451, 0);
    public static Area Swamp = new Area (Swamptop, Swampbot);
    Tile Swampdrop = new Tile(3544, 3451, 0);
    static Tile Swamp1 = new Tile(3550, 3448, 0);
    static Tile Swamp2 = new Tile(3553, 3449, 0);
    static Tile Swamp3 = new Tile(2611, 2929, 0);
    public static Area Swampone = new Area(Swamp1, Swamp1);
    public static Area Swamptwo = new Area(Swamp2, Swamp2);
    int rope = 954;
    int net = 303;


    @Override
    public void execute() {
        GameObject Tree = Objects.stream().within(Swamp).id(9341).first();
        GameObject Trap = Objects.stream().within(Swamp).id(9343).first();
        GameObject trapped = Objects.stream().within(Swamp).id(9004).first();
        GroundItem Rope = GroundItems.stream().id(rope).first();
        GroundItem Net = GroundItems.stream().id(net).first();
        if (!Swamp.contains(Players.local())){
            System.out.println("Moving to Lizards");
            Movement.moveTo(Swampstart);
        }
        if (Swamp.contains(Players.local())) {
            System.out.println("We are within hunting area");
            Game.tab(Game.Tab.INVENTORY);
            if (Tree.valid()) {
                System.out.println("Setting trap on first tree");
                Tree.interact("Set-trap");
                Condition.wait(() -> !Tree.valid(), 150, 10);
                //Condition.sleep(Random.nextInt(1500, 2000));
            } else {
                if (trapped.valid()){
                    System.out.println("Checking traps");
                    trapped.interact("Check");
                    Condition.wait(() -> !trapped.valid(), 150,10);
                }else {
                    if (Rope.valid() || Net.valid()) {
                        Rope.interact("Take");
                        Condition.wait(() -> !Rope.valid(), 150, 10);
                        Net.interact("Take");
                        Condition.wait(() -> !Net.valid(), 150, 10);
                    } else {
                        if (Inventory.stream().name("Swamp lizard").count() > 5) {
                            for (int i = 0; i < 28; i++) {
                                Item Swamp_Lizard = Inventory.stream().name("Swamp lizard").first();
                                if (Swamp_Lizard.name().contains("Swamp")) {
                                    System.out.println("Dropping Bones & Meat");
                                    Swamp_Lizard.interact("Release");
                                    Condition.sleep(Random.nextInt(225, 300));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

