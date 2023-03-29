package com.accenture.RPGCombatKata;

public class Character {

    private Long health=1000L;
    private Long level=1L;
    private Boolean alive=true;
    private RANGETYPES rangetype=RANGETYPES.MELEE;
    private Double position=0D;

    public Long getHealth() {
        return this.health;
    }

    public Long getLevel() {
        return this.level;
    }

    public Boolean isAlive(){
        return this.alive;
    }

    public void setLevel(Long level) {

        this.level = level;
    }

    public void dealsDamage(Character target, Long damageValue) {

        Boolean isTargetingItself=this==target;
        if(isTargetingItself)return;

        Boolean targetIsNotInRange=Math.abs(target.position-this.position)>this.getMaxRange();
        if(targetIsNotInRange)return;

        long realDamageDone=damageValue;

        Boolean targetIs5lvlStronger=target.getLevel()>=this.getLevel()+5;
        if(targetIs5lvlStronger)realDamageDone*=0.5;

        Boolean targetIs5lvlWeaker=target.getLevel()<=this.getLevel()-5;
        if(targetIs5lvlWeaker)realDamageDone*=1.5;

        target.damage(realDamageDone);
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

    public Integer getMaxRange() {
        switch (this.rangetype){
            case MELEE:
                return 2;
            case RANGED:
                return 20;
            default:
                return null;
        }
    }

    public void setRangeType(RANGETYPES rangeType) {
        this.rangetype=rangeType;
    }

    public void setPosition(Double position) {
        this.position=position;
    }
}
