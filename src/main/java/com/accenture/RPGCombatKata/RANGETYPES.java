package com.accenture.RPGCombatKata;

public enum RANGETYPES {
    MELEE(2),
    RANGED(20);
    public final Integer range;

    RANGETYPES(Integer range) {
        this.range = range;
    }
}
