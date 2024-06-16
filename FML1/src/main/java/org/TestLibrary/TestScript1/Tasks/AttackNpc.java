package org.TestLibrary.TestScript1.Tasks;

import org.TestLibrary.TestScript1.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class AttackNpc extends Task {

    public AttackNpc(){
        super();
        this.name = "AttackNpc";
    }


    @Override
    public boolean shouldExecute(){
        return !new EatFood().shouldExecute()
                && !Players.local().interacting().valid();
                //Npcs.stream().within(15).name("Guard").filtered(npc -> !npc.healthBarVisible()).isNotEmpty();

    }

    @Override
    public void execute(){
        Npc target;
        if (Npcs.stream().interactingWithMe().isNotEmpty()){
            target = Npcs.stream().interactingWithMe().nearest().first();
        } else {
            target = Npcs.stream().within(15).name("Guard").filtered(npc -> !npc.healthBarVisible()).nearest().first();
        }
        if (target.valid()){
            System.out.println("Found target: "+target.name());
            if (target.inViewport()){
                System.out.println("Visible target, commencing attack");
                if (target.interact("Attack")){
                    Condition.wait(()->Players.local().interacting().valid(), 150, 10);
                }
            } else {
                System.out.println("Target not visible, commence finding target");
                Camera.turnTo(target);
            }
        } else {
            System.out.println("Target not found");
        }

    }
}
