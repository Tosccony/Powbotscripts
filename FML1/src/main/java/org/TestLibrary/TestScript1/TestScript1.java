package org.TestLibrary.TestScript1;

import org.TestLibrary.TestScript1.Tasks.AttackNpc;
import org.TestLibrary.TestScript1.Tasks.EatFood;
import org.powbot.api.Condition;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.sql.SQLOutput;
import java.util.ArrayList;

@ScriptManifest(name = "FML", description = "Idfk bruh", author = "Tosccon", category = ScriptCategory.Combat)

public class TestScript1 extends AbstractScript {


    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("FML", "", "127.0.0.1:5695", true, false);
    }

    ArrayList<Task> taskList = new ArrayList<>();


    @Override
    public void onStart(){
        taskList.add(new EatFood());
        taskList.add(new AttackNpc());

    }


    @Override
    public void poll() {
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
