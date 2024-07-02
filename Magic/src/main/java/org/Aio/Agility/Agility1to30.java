package org.Aio.Agility;

import org.Aio.Task;
import org.powbot.api.*;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.waiter.SkillExpGainedWaiter;



public class Agility1to30 extends Task {

    public Agility1to30() {
        super();
        this.name = "Agility1to30";
    }

    static Tile Ob1Top = new Tile (3098, 3286, 0);
    static Tile Ob1Bot = new Tile (3106, 3259, 0);
    public static Area area1 = new Area (Ob1Top, Ob1Bot);
    static Tile Ob2Top = new Tile (3097, 3281, 3);
    static Tile Ob2Bot = new Tile (3102, 3277, 3);
    public static Area area2 = new Area (Ob2Top, Ob2Bot);
    static Tile Ob3Top = new Tile (3088, 3274, 3);
    static Tile Ob3Bot = new Tile (3091, 3276, 3);
    public static Area area3 = new Area (Ob3Top, Ob3Bot);
    static Tile Ob4Top = new Tile (3094, 3267, 3);
    static Tile Ob4Bot = new Tile (3089, 3265, 3);
    public static Area area4 = new Area (Ob4Top, Ob4Bot);
    static Tile Ob5Top = new Tile (3087, 3261, 3);
    static Tile Ob5Bot = new Tile (3088, 3257, 3);
    public static Area area5 = new Area (Ob5Top, Ob5Bot);
    static Tile Ob6Top = new Tile (3087, 3255, 3);
    static Tile Ob6Bot = new Tile (3096, 3254, 3);
    public static Area area6 = new Area (Ob6Top, Ob6Bot);
    static Tile Ob7Top = new Tile (3096, 3256, 3);
    static Tile Ob7Bot = new Tile (3101, 3261, 3);
    public static Area area7 = new Area (Ob7Top, Ob7Bot);
    Tile Start = new Tile(3103, 3279, 0);
    @Override
    public boolean shouldExecute() {
        return Skill.Agility.realLevel()<44;
    }

    @Override
    public void execute() {
        GameObject D1 = Objects.stream().within(30).id(11404).nearest().first();
        GameObject D2 = Objects.stream().within(10).id(11405).nearest().first();
        GameObject D3 = Objects.stream().within(10).id(11406).nearest().first();
        GameObject D4 = Objects.stream().within(10).id(11430).nearest().first();
        GameObject D5 = Objects.stream().within(10).id(11630).nearest().first();
        GameObject D6 = Objects.stream().within(10).id(11631).nearest().first();
        GameObject D7 = Objects.stream().within(10).id(11632).nearest().first();
        if (!Camera.angle(100)) {
            Camera.angle(100);
        }else {
            if (area1.contains(Players.local())) {
                Movement.moveTo(Start);
                D1.interact("Climb", new SkillExpGainedWaiter(Skill.Agility));
                Condition.wait(() -> !Players.local().inMotion() && area2.contains(Players.local()), 150, 10);
            } else {
                if (area2.contains(Players.local())) {
                    D2.interact("Cross", new SkillExpGainedWaiter(Skill.Agility));
                    Condition.wait(() -> !Players.local().inMotion() && area3.contains(Players.local()), 150, 10);
                } else {
                    if (area3.contains(Players.local())) {
                        D3.interact("Cross", new SkillExpGainedWaiter(Skill.Agility));
                        Condition.wait(() -> !Players.local().inMotion() && area4.contains(Players.local()), 150, 10);
                    } else {
                        if (area4.contains(Players.local())) {
                            D4.interact("Balance", new SkillExpGainedWaiter(Skill.Agility));
                            Condition.wait(() -> !Players.local().inMotion() && area5.contains(Players.local()), 150, 10);
                        } else {
                            if (area5.contains(Players.local())) {
                                D5.interact("Jump-up", new SkillExpGainedWaiter(Skill.Agility));
                                Condition.wait(() -> !Players.local().inMotion() && area6.contains(Players.local()), 150, 10);
                            } else {
                                if (area6.contains(Players.local())) {
                                    D6.interact("Jump", new SkillExpGainedWaiter(Skill.Agility));
                                    Condition.wait(() -> !Players.local().inMotion() && area7.contains(Players.local()), 150, 10);
                                } else {
                                    if (area7.contains(Players.local())) {
                                        D7.interact("Climb-down");
                                        Condition.wait(() -> area1.contains(Players.local()), 150, 20);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




