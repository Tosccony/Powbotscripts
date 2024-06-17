package org.Mining;


import org.Mining.Tasks.Banking;
import org.Mining.Tasks.Miningfirst;
import org.powbot.api.Notifications;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(name = "Woodcutting 1.0.0", description = "Aio Woodcutting Section of our account builder", author = "Tosccon", category = ScriptCategory.Woodcutting)

public class Mining extends AbstractScript {


    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("Woodcutting 1.0.0", "", "127.0.0.1:5715", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();
    Tile Ge = new Tile(3165, 3486, 0);



    @Override
    public void onStart(){
        taskList.add(new Miningfirst());
        taskList.add(new Banking());

    }


    @Override
    public void poll() {
        if (Skill.Mining.realLevel() == 55){
            Notifications.showNotification("Levels achieved going to Ge");
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