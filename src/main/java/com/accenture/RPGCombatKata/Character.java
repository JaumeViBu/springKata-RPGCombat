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

    /**
     * Character deals damageValue to a given target
     * if target is itself, do nothing
     * if target is not in range, do nothing
     * if target is 5 or more lvls above this character, the damage is reduced by 50%
     * if target is 5 or more lvls below this character, the damage is increased by 50%
     * @param target
     * @param damageValue
     */
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

    /**
     * Applies a damage amount to the character health
     * @param damage
     */
    public void damage(Long damage){

        this.health-=damage;

        if(this.health>0)return;

        this.health=0L;
        this.alive=false;
    }

    /**
     * Character activates health regen by healthToRegen amount, to a given target health
     * if target is not itself, do nothing
     * @param target
     * @param healingValue
     */
    public void heal(Character target,Long healingValue){
        Boolean targetIsItself=target==this;
        if(targetIsItself)target.regen(healingValue);
    }

    /**
     * Character regens a healthToRegen amount of life
     * if characer is not alive, do nothing
     * character cannot regen over 1000L
     * @param healthToRegen
     */
    public void regen(Long healthToRegen){

        if(this.alive)this.health+=healthToRegen;
        if(this.getHealth()>1000L)this.health=1000L;
    }

    /**
     * get characters max range based on the character range type,
     * 2m for RANGETYPES.MELEE
     * 20m for RANGETYPES.RANGED
     * @return
     */
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
