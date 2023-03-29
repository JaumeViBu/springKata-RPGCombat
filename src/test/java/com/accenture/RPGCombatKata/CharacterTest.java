package com.accenture.RPGCombatKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void characterStartsWithCorrectsProps(){
        //given
        var pc=new Character();
        //when
        Long initialHealth=pc.getHealth();
        Long initialLevel=pc.getLevel();
        Boolean isAlive=pc.isAlive();
        //then
        assertEquals(1000L,initialHealth);
        assertEquals(1L,initialLevel);
        assertEquals(true,isAlive);
    }

    @Test
    void charactersCanDealDamageToCharacters(){
        //given
        var pc=new Character();
        var enemy=new Character();
        //when
        pc.dealsDamage(enemy,50L);
        //then
        assertEquals(950L,enemy.getHealth());
    }

    @Test
    void whenDamageDealtExceedsTargetCurrentHealthBecomes0AndDies(){
        //given
        var pc=new Character();
        var enemy=new Character();
        //when
        pc.dealsDamage(enemy,5000L);
        //then
        assertEquals(0L,enemy.getHealth());
        assertEquals(false,enemy.isAlive());
    }

    @Test
    void characterCanHealCharacters(){
        //given
        var pc=new Character();
        pc.damage(500L);
        //when
        pc.heal(pc,100L);
        //then
        assertEquals(600L,pc.getHealth());
    }

    @Test
    void characterCannotHealDeadCharacters(){
        //given
        var pc=new Character();
        var ally=new Character();
        ally.damage(5000L);
        //when
        pc.heal(ally,100000L);
        //then
        assertEquals(0L,ally.getHealth());
        assertEquals(false,ally.isAlive());

    }

    @Test
    void characterHealingCannotRaiseHealthAbove1000(){
        //given
        var pc=new Character();
        pc.damage(500L);
        //when
        pc.heal(pc,100000L);
        //then
        assertEquals(1000L,pc.getHealth());
    }

    @Test
    void characterCannotDealDamageToItself(){
        //given
        var pc=new Character();
        //when
        pc.dealsDamage(pc,500L);
        //then
        assertEquals(1000L,pc.getHealth());
    }

    @Test
    void characterCanOnlyHealItself(){
        //given
        var pc=new Character();
        var ally=new Character();
        pc.damage(500L);
        ally.damage(500L);
        //when
        pc.heal(pc,50L);
        pc.heal(ally,50L);
        //then
        assertEquals(550L,pc.getHealth());
        assertEquals(500L,ally.getHealth());
    }

    @Test
    void ifTheTargetIs5OrMoreLevelsAboveTheAttackerDamageIsReducedBy50pc(){
        //given
        var lvl1=new Character();
        var lvl10=new Character();
        lvl10.setLevel(10L);
        var enemy1=new Character();
        var enemy2=new Character();
        enemy1.setLevel(6L);
        enemy2.setLevel(6L);
        //when
        lvl1.dealsDamage(enemy1,100L);
        lvl10.dealsDamage(enemy2,100L);
        //then
        assertEquals(950L,enemy1.getHealth());
        assertEquals(900L,enemy2.getHealth());
    }

    @Test
    void ifTheTargetIs5OrMoreLevelsBelowTheAttackerDamageIsIncreasedBy50pc(){
        //given
        var lvl1=new Character();
        var lvl6=new Character();
        lvl6.setLevel(6L);
        var enemy1=new Character();
        var enemy2=new Character();
        //when
        lvl1.dealsDamage(enemy1,100L);
        lvl6.dealsDamage(enemy2,100L);
        //then
        assertEquals(900L,enemy1.getHealth());
        assertEquals(850L,enemy2.getHealth());
    }
}