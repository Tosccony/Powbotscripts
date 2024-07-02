package org.Mining;


import org.Mining.Tasks.*;
import org.Mining.Tasks.Miningsecond;
import org.powbot.api.Notifications;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.dax.api.DaxWalker;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(name = "Mining 1.0.0", description = "Aio Mining Section of our account builder", author = "Tosccon", category = ScriptCategory.Mining)

public class Mining extends AbstractScript {
//127.0.0.1:5715

    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("Mining 1.0.0", "", "127.0.0.1:5705", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();
    Tile Ge = new Tile(3165, 3486, 0);
    public int Ore = 440;


    @Override
    public void onStart(){
        Paint paint = PaintBuilder.newBuilder()
                .x(40)
                .y(45)
                .trackSkill(Skill.Mining)
                .trackInventoryItem(Ore)
                .build();
        addPaint(paint);
        taskList.add(new Banking());
        taskList.add(new WalkToBank());
        taskList.add(new Miningfirst());
        taskList.add(new Miningsecond());
        taskList.add(new Miningthird());
        taskList.add(new Miningfourth());

    }


    @Override
    public void poll() {
        if (Skill.Mining.realLevel() == 60){
            Notifications.showNotification("Levels achieved going to Ge");
            Movement.moveTo(Ge);
            if (Bank.opened()){
                Bank.depositInventory();
            } else if (!Bank.opened()) {
                Bank.open();
                Bank.depositInventory();
            }
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