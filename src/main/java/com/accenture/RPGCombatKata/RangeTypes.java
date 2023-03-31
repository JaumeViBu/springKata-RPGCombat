package com.accenture.RPGCombatKata;

public enum RangeTypes {
    MELEE(2),
    RANGED(20);
    public final Integer range;

    RangeTypes(Integer range) {
        this.range = range;
    }
}
