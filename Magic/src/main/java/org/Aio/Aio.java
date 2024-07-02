package org.Aio;

import org.Aio.Agility.Agility1to30;
import org.Aio.Agility.WalkToDraynor;
import org.Aio.Crafting.*;
import org.Aio.Fletching.FletchBanking;
import org.Aio.Fletching.FletchWalkToBank;
import org.Aio.Fletching.Fletching1to25;
import org.Aio.Herblore.Cleaning;
import org.Aio.Herblore.Crafting;
import org.Aio.Herblore.HerbBanking;
import org.Aio.Hunter.*;
import org.Aio.Magic.MagicBanking;
import org.Aio.Magic.WalkToBank;
import org.Aio.Magic.lvls1to7;
import org.Aio.Magic.lvls7to25;
import org.Aio.Thieving.ThievBanking;
import org.Aio.Thieving.Thieving1to5;
import org.Aio.Thieving.Thieving25to55;
import org.Aio.Thieving.Thieving5to25;
import org.powbot.api.Tile;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptCategory;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;


@ScriptManifest(name = "AIO Account Builder 1.0.0", description = "Aio account builder", author = "Tosccon", category = ScriptCategory.Other)

public class Aio extends AbstractScript {


    public static void main(String[] args){
        new ScriptUploader().uploadAndStart("Aio Account Builder 1.0.0", "", "127.0.0.1:5715", true, false);
    }
    ArrayList<Task> taskList = new ArrayList<>();


    @Override
    public void onStart(){
        Paint paint = PaintBuilder.newBuilder()
                .x(40)
                .y(45)
                .trackSkill(Skill.Magic)
                .trackSkill(Skill.Thieving)
                .trackSkill(Skill.Herblore)
                .trackSkill(Skill.Crafting)
                .trackSkill(Skill.Agility)
                .trackSkill(Skill.Fletching)
                .trackSkill(Skill.Hunter)
                .build();
        addPaint(paint);
        if (Skill.Magic.realLevel() <=54) {
            taskList.add(new lvls1to7());
            taskList.add(new lvls7to25());
            taskList.add(new MagicBanking());
            taskList.add(new WalkToBank());
        }else {
            if (Skill.Magic.realLevel() >=54 && Skill.Thieving.realLevel() <= 54){
                taskList.add(new Thieving1to5());
                taskList.add(new Thieving5to25());
                taskList.add(new Thieving25to55());
                taskList.add(new ThievBanking());
            }else {
                if (Skill.Thieving.realLevel() >= 55 && Skill.Herblore.realLevel() <=39) {
                    taskList.add(new Cleaning());
                    taskList.add(new Crafting());
                    taskList.add(new HerbBanking());
                    taskList.add(new org.Aio.Herblore.WalkToBank());
                }else {
                    if (Skill.Herblore.realLevel() >= 40 && Skill.Crafting.realLevel() < 61) {
                        taskList.add(new Craftinglvls1to33());
                        taskList.add(new Craftinglvls33to42());
                        taskList.add(new Craftinglvls42to46());
                        taskList.add(new Craftinglvls46to61());
                        taskList.add(new Craftingbank());
                    }else {
                        if (Skill.Crafting.realLevel() >=61 && Skill.Agility.realLevel() < 44) {
                            taskList.add(new Agility1to30());
                            taskList.add(new WalkToDraynor());
                        }else {
                            if (Skill.Agility.realLevel() >=44 && Skill.Fletching.realLevel() < 25) {
                                taskList.add(new Fletching1to25());
                                taskList.add(new FletchBanking());
                                taskList.add(new FletchWalkToBank());
                            }else {
                                if (Skill.Fletching.realLevel() >=25 && Skill.Hunter.realLevel() < 60) {
                                    taskList.add(new Hunting9to19());
                                    taskList.add(new Hunting19to29());
                                    taskList.add(new Hunting29to43());
                                    taskList.add(new Hunting43to60());
                                    taskList.add(new HunterBanking());
                                    taskList.add(new HuntingWalktoBank());
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void poll() {
        for (Task task: taskList){
            System.out.println("Checking task: "+task.name);
            if (task.shouldExecute()) {
                System.out.println("Running task: " + task.name);
                task.execute();
            }
            if (ScriptManager.INSTANCE.isStopping() || ScriptManager.INSTANCE.isPaused()){
                break;
            }
        }
    }
}