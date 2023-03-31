package com.accenture.RPGCombatKata;

import java.util.HashSet;
import java.util.Set;

public class Character {

    private Long health=1000L;
    private Long level=1L;
    private Boolean alive=true;
    private RangeTypes rangetype= RangeTypes.MELEE;
    private Double position=0D;
    private Set<FACTIONS> factions=new HashSet<>();

    public Character() {
        this(1L, RangeTypes.MELEE,0D);
    }

    public Character(Long level){
        this(level, RangeTypes.MELEE,0D);
    }

    public Character(RangeTypes rangetype){
        this(1L,rangetype,0D);
    }

    public Character(Long level, RangeTypes rangetype, Double position) {
        this.level = level;
        this.rangetype = rangetype;
        this.position = position;
    }

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

        Boolean isTargetingAnAlly=this.isAlly(target);
        if(isTargetingAnAlly)return;

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
        Boolean targetIsAlly=this.isAlly(target);
        if(targetIsItself || targetIsAlly)target.regen(healingValue);
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
        return this.rangetype.range;
    }

    public void setRangeType(RangeTypes rangeType) {
        this.rangetype=rangeType;
    }

    public void setPosition(Double position) {
        this.position=position;
    }

    public FACTIONS[] getFactions() {
        FACTIONS[] res=new FACTIONS[this.factions.size()];
        this.factions.toArray(res);
        return res;
    }

    public void addFaction(FACTIONS faction) {
        this.factions.add(faction);
    }


    public void removeFaction(FACTIONS faction) {
        this.factions.remove(faction);
    }


    public Boolean isAlly(Character other) {

        Set<FACTIONS> aux = new HashSet<>(other.factions);
        aux.retainAll(this.factions);

        Boolean containsAtLeast1MatchFaction=aux.size()>0;

        return containsAtLeast1MatchFaction;
    }
}
