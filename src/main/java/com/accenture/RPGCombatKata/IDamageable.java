package com.accenture.RPGCombatKata;

public interface IDamageable {

    public Long getHealth();
    public void damage(Long damage);
    public Double getPosition();

    public Factions[] getFactions();

}
