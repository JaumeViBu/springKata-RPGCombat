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

    public void dealsDamage(Character target, long damageValue) {
        if(this!=target)target.damage(damageValue);
    }

    public void damage(Long damage){
        this.health-=damage;

        if(this.health<0){

            this.health=0L;
            this.alive=false;
        }
    }

    public void heal(Character target,Long healingValue){
        if(target==this)target.regen(healingValue);
    }

    public void regen(Long healthToRegen){
        if(this.alive)this.health+=healthToRegen;
        if(this.getHealth()>1000L)this.health=1000L;
    }
}
