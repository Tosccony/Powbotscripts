package org.Aio.Thieving;

import org.Aio.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.walking.model.Skill;

public class ThievBanking extends Task {
    public ThievBanking() {
        super();
        this.name = "Banking";
    }

    @Override
    public boolean shouldExecute() {
        return !new Thieving1to5().shouldExecute() || !new Thieving5to25().shouldExecute() || !new Thieving25to55().shouldExecute();
    }

    @Override
    public void execute() {
        if (!Bank.opened()){
            Bank.open();
        }else {
        if (Bank.opened()) {
            if (Skill.Thieving.realLevel() < 5) {
                System.out.println("Withdrawing food to go get lvl 5 thieving");
                Bank.withdraw("Lobster", 20);
            }
        }
        if (Bank.close(false)) {
            System.out.println("Closing Bank and Starting our task");
            Bank.close();
        }
        }

    }
}
