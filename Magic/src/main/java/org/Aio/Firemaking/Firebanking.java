package org.Aio.Firemaking;

import org.Aio.Task;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.walking.model.Skill;

public class Firebanking extends Task {

    public Firebanking(){
        super();
        this.name = "Firebanking";
    }
    public Tile fm = new Tile(3253,3419,0);
    @Override
    public boolean shouldExecute() {
        return fm.distance() < 5 && !new Fm1to15().shouldExecute();
    }

    @Override
    public void execute() {
        if(!Bank.opened()) {
            Bank.open();
        }else {
            if(Bank.opened()) {
                if (Inventory.stream().name("Tinderbox").isEmpty()) {
                    Bank.withdraw("Tinderbox", 1);
                }else {
                    if (Skill.Firemaking.realLevel() < 15) {
                        if (Bank.withdraw("Logs", Bank.Amount.ALL)) {
                            System.out.println("Withdrawing logs");
                        }else {
                            if (Skill.Firemaking.realLevel() > 15 && Skill.Firemaking.realLevel() < 30) {
                                if (Bank.withdraw("Oak log", Bank.Amount.ALL)){
                                    System.out.println("Withdrawing Oak logs");
                                }else {
                                    if (Skill.Firemaking.realLevel() > 29 && Skill.Firemaking.realLevel() < 49) {
                                        if (Bank.withdraw("Willow log", Bank.Amount.ALL)){
                                            System.out.println("Withdrawing Willow logs");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (Bank.opened()){
                        Bank.close();
                    }
                }
            }
        }

    }
}
