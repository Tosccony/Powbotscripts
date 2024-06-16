package org.TestLibrary.TestScript1.Tasks;

import org.TestLibrary.TestScript1.Task;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;

public class CombatStyle extends Task {
    public CombatStyle() {
        super();
        this.name = "CombatStyle";
    }


    @Override
    public boolean shouldExecute() {
        return !Players.local().interacting().inCombat();
    }

    @Override
    public void execute() {
        System.out.println("Checking styles");
        Players.local().interacting().inCombat();
        {
            if (Skill.Attack.realLevel() < 20) {
                Combat.style(Combat.Style.ACCURATE);
            }
            if (Skill.Attack.realLevel() > 20) {
                Combat.style(Combat.Style.AGGRESSIVE);
            }
            if (Skill.Strength.realLevel() == 20) {
                Combat.style(Combat.Style.DEFENSIVE);
            }
        }
    }
}
