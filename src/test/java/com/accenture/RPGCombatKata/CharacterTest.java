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
}