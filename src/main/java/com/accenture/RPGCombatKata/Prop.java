package com.accenture.RPGCombatKata;

public class Prop implements IDamageable {

    private Long health;
    private String label;
    private Double position;

    public Prop(String label, long health, Double position) {
        this.label=label;
        this.health=health;
        this.position=position;
    }

    public Prop(String label, long health) {
        this(label, health,0D);
    }

    public Prop(){
        this("prop",100L);
    }

    @Override
    public Long getHealth() {
        return this.health;
    }

    @Override
    public void damage(Long damage) {
        this.health-=damage;

        if(this.health>0)return;

        this.health=0L;
    }

    @Override
    public Double getPosition() {
        return this.position;
    }

    @Override
    public Factions[] getFactions() {
        return new Factions[0];
    }

    public Boolean isDestroyed() {
        return false;
    }
}
