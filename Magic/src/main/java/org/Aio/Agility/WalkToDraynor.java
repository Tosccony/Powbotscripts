package org.Aio.Agility;

import org.Aio.Fletching.FletchBanking;
import org.Aio.Herblore.Crafting;
import org.Aio.Task;
import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.dax.api.models.RunescapeBank;

public class WalkToDraynor extends Task {

    public WalkToDraynor(){
        super();
        this.name = "WalkToDraynor";
    }
    @Override
    public boolean shouldExecute() {
        return Skill.Agility.realLevel()<44 && new Agility1to30().shouldExecute();
    }
    Tile Start = new Tile(3103, 3279, 0);
    static Tile coursetop = new Tile (3104, 3286, 0);
    static Tile coursebot = new Tile (3082, 3249, 0);
    public static Area course = new Area (coursetop, coursebot);
    static Tile coursetop1 = new Tile (3104, 3286, 1);
    static Tile coursebot1 = new Tile (3082, 3249, 1);
    public static Area coursetopfloor = new Area (coursetop1, coursebot1);
    static Tile coursetop2 = new Tile (3104, 3286, 2);
    static Tile coursebot2 = new Tile (3082, 3249, 2);
    public static Area coursetopfloor2 = new Area (coursetop2, coursebot2);
    static Tile coursetop3 = new Tile (3104, 3286, 3);
    static Tile coursebot3 = new Tile (3082, 3249, 3);
    public static Area coursetopfloor3 = new Area (coursetop3, coursebot3);
    @Override
    public void execute() {
        if (!course.contains(Players.local()) && !coursetopfloor.contains(Players.local())
                && !coursetopfloor2.contains(Players.local()) && !coursetopfloor3.contains(Players.local())) {
            if (Start.distance()>5) {
                Movement.moveTo(Start);
            }
        }
    }
}