package com.accenture.RPGCombatKata;

public class Character {

    private Long health=1000L;
    private Long level=1L;
    private Boolean alive=true;

    public Long getHealth() {
        return this.health;
    }

    public Long getLevel() {
        return this.level;
    }

    public Boolean isAlive(){
        return this.alive;
    }

}
