package org.TestLibrary.TestScript1;

import org.TestLibrary.TestScript1.Tasks.*;
import org.powbot.api.Notifications;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(name = "FML", description = "Idfk bruh", author = "Tosccon", category = ScriptCategory.Combat)

public class TestScript1 extends AbstractScript {


    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("FML", "", "127.0.0.1:5715", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();
    public Tile startTile = Tile.getNil();
    Tile Ge = new Tile(3165, 3486, 0);
    @Override
    public void onStart(){
        startTile = Players.local().tile();
        DaxWalker.blacklistTeleports(Teleport.values());
        taskList.add(new EatFood());
        taskList.add(new AttackNpc());
        //taskList.add(new CombatStyle());
        taskList.add(new WalkToBank());
        taskList.add(new Banking());
        taskList.add(new WalkToNpc(this));
    }


    @Override
    public void poll() {
        if(Skill.Defence.realLevel() == 20){
            Notifications.showNotification("Done level 20s");
            Movement.moveTo(Ge);
            ScriptManager.INSTANCE.stop();
        }
        for (Task task: taskList){
            System.out.println("Checking task: "+task.name);
            if (task.shouldExecute()){
                System.out.println("Running task: "+task.name);
                task.execute();
            }
            if (ScriptManager.INSTANCE.isStopping() || ScriptManager.INSTANCE.isPaused()){
                break;
            }
        }





    }
}
