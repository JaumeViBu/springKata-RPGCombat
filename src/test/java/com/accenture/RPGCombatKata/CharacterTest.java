package com.accenture.RPGCombatKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        var lvl10=new Character(10L);
        var enemy1=new Character(6L);
        var enemy2=new Character(6L);
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
        var lvl6=new Character(6L);
        var lvl10=new Character(6L);
        var enemy1=new Character();
        var enemy2=new Character();
        var enemy3=new Character();
        //when
        lvl1.dealsDamage(enemy1,100L);
        lvl6.dealsDamage(enemy2,100L);
        lvl10.dealsDamage(enemy3,100L);
        //then
        assertEquals(900L,enemy1.getHealth());
        assertEquals(850L,enemy2.getHealth());
        assertEquals(850L,enemy3.getHealth());
    }

    @Test
    void charactersMustHaveMaxRange(){
        //given
        var pc=new Character();
        //when
        var sut =pc.getMaxRange();
        //then
        assertEquals(2,sut);
    }

    @Test
    void meleeCharactersMustHave2mMaxRange(){
        //given
        var melee=new Character(RangeTypes.MELEE);
        //when
        var sut =melee.getMaxRange();
        //then
        assertEquals(2,sut);
    }

    @Test
    void rangeCharactersMustHave20mMaxRange(){
        //given
        var ranged=new Character(RangeTypes.RANGED);
        //when
        var sut =ranged.getMaxRange();
        //then
        assertEquals(20,sut);
    }

    @Test
    void charactersMustBeInRangeToDealDamageToTarget(){
        //given
        var melee=new Character(RangeTypes.MELEE);
        var ranged=new Character(RangeTypes.RANGED);

        var enemy2m=new Character(1L, RangeTypes.MELEE,2D);
        var enemy20m=new Character(1L, RangeTypes.MELEE,20D);

        //when
        melee.dealsDamage(enemy2m,100L);
        melee.dealsDamage(enemy20m,100L);
        ranged.dealsDamage(enemy2m,100L);
        ranged.dealsDamage(enemy20m,100L);

        var sut2m =enemy2m.getHealth();
        var sut20m =enemy20m.getHealth();

        //then
        assertEquals(800L,sut2m);
        assertEquals(900L,sut20m);
    }

    @Test
    void newlyCreatedCharactersBelongToNoFaction(){
        //given
        var pc=new Character();
        //when
        var factions=pc.getFactions();
        //then
        assertEquals(0,factions.length);
    }

    @Test
    void charactersMayBelongToOneOrMoreFactions(){
        //given
        var pc=new Character();
        //when
        pc.addFaction(Factions.LIGHT_BEARERS);
        pc.addFaction(Factions.LIGHT_BEARERS);
        pc.addFaction(Factions.LIGHT_BEARERS);
        pc.addFaction(Factions.DARK_RISERS);
        var factions=pc.getFactions();
        //then
        assertEquals(2,factions.length);
    }

    @Test
    void aCharacterMayJoinOrLeaveOneOrMoreFactions(){
        //given
        var pc=new Character();
        //when
        pc.addFaction(Factions.LIGHT_BEARERS);
        pc.addFaction(Factions.DARK_RISERS);
        pc.removeFaction(Factions.LIGHT_BEARERS);
        pc.removeFaction(Factions.LIGHT_BEARERS);
        var factions=pc.getFactions();
        //then
        assertEquals(1,factions.length);
    }

    @Test
    void playersBelongingToTheSameFactionAreConsideredAllies(){
        //given
        var darkRiser1=new Character();
        darkRiser1.addFaction(Factions.DARK_RISERS);
        var lightBearer1=new Character();
        lightBearer1.addFaction(Factions.LIGHT_BEARERS);
        var lightBearer2=new Character();
        lightBearer2.addFaction(Factions.LIGHT_BEARERS);
        var all1=new Character();
        all1.addFaction(Factions.LIGHT_BEARERS);
        all1.addFaction(Factions.DARK_RISERS);
        //when
        var allies1=darkRiser1.isAlly(lightBearer1);
        var allies2=lightBearer1.isAlly(lightBearer2);
        var allies3=all1.isAlly(darkRiser1);
        var allies4=all1.isAlly(lightBearer1);
        //then
        assertEquals(false,allies1);
        assertEquals(true,allies2);
        assertEquals(true,allies3);
        assertEquals(true,allies4);
    }

    @Test
    void alliesCannotDealDamageToOneAnother(){
        //given
        var darkRiser1=new Character();
        darkRiser1.addFaction(Factions.DARK_RISERS);
        var darkRiser2=new Character();
        darkRiser2.addFaction(Factions.DARK_RISERS);
        var lightBearer1=new Character();
        lightBearer1.addFaction(Factions.LIGHT_BEARERS);
        var all1=new Character();
        all1.addFaction(Factions.LIGHT_BEARERS);
        all1.addFaction(Factions.DARK_RISERS);
        //when
        darkRiser1.dealsDamage(lightBearer1,100L);
        darkRiser1.dealsDamage(darkRiser2,100L);
        darkRiser1.dealsDamage(all1,100L);

        var darkRiser2Health=darkRiser2.getHealth();
        var lightBearer1Health=lightBearer1.getHealth();
        var all1Health=all1.getHealth();
        //then
        assertEquals(1000L,darkRiser2Health);
        assertEquals(900L,lightBearer1Health);
        assertEquals(1000L,all1Health);
    }

    @Test
    void alliesCanHealOneAnother(){
        //given
        var darkRiser1=new Character();
        darkRiser1.addFaction(Factions.DARK_RISERS);
        var darkRiser2=new Character();
        darkRiser2.addFaction(Factions.DARK_RISERS);
        var lightBearer1=new Character();
        lightBearer1.addFaction(Factions.LIGHT_BEARERS);
        var all1=new Character();
        all1.addFaction(Factions.LIGHT_BEARERS);
        all1.addFaction(Factions.DARK_RISERS);

        darkRiser1.damage(500L);
        darkRiser2.damage(500L);
        lightBearer1.damage(500L);
        all1.damage(500L);

        //when
        darkRiser1.heal(darkRiser1,100L);
        darkRiser1.heal(darkRiser2,100L);
        darkRiser1.heal(lightBearer1,100L);
        darkRiser1.heal(all1,100L);

        var darkRiser1Health=darkRiser1.getHealth();
        var darkRiser2Health=darkRiser2.getHealth();
        var lightBearer1Health=lightBearer1.getHealth();
        var all1Health=all1.getHealth();
        //then
        assertEquals(600L,darkRiser1Health);
        assertEquals(600L,darkRiser2Health);
        assertEquals(500L,lightBearer1Health);
        assertEquals(600L,all1Health);
    }

    @Test
    void anythingThatHasHealthMayBeATarget() {
        //given
        var pc=new Character();
        var enemy=new Character();
        var woodenDoor=new Prop("wooden door",200L);
        var woodenCrate=new Prop("wooden crate",50L);
        //when
        pc.dealsDamage(enemy,100L);
        pc.dealsDamage(woodenDoor,100L);
        pc.dealsDamage(woodenCrate,100L);

        var enemyHealth=enemy.getHealth();
        var woodenDoorHealth=woodenDoor.getHealth();
        var woodenCrateHealth=woodenCrate.getHealth();
        //then
        assertEquals(900L,enemyHealth);
        assertEquals(100L,woodenDoorHealth);
        assertEquals(0L,woodenCrateHealth);
    }


    @Test
    void theseThingsCanNotBeHealedAndTheyDoNotDealDamage() {
        //given
        var door=new Prop("door",200L);
        door.damage(100L);
        var pc=new Character();
        //when
        pc.heal(door,100L);
        var doorHealth=door.getHealth();
        //then
        assertEquals(100L,doorHealth);
    }


    @Test
    void theseThingsDoNotBelongToFactionsTheyAreNeutral() {
        //given
        ArrayList<Character> factionCharacters=new ArrayList<>();
        for(Factions faction:Factions.values()){
            Character newFactionCharacter=new Character();
            newFactionCharacter.addFaction(faction);
            factionCharacters.add(newFactionCharacter);
        }
        //when
        Long propsFactions=0L;
        Long remainingHealth=0L;
        for(Character factionCharacter:factionCharacters){
            IDamageable dummy=new Prop("dummy",50L);
            factionCharacter.dealsDamage(dummy,100L);
            remainingHealth+=dummy.getHealth();
            propsFactions+=dummy.getFactions().length;
        }
        //then
        assertEquals(0L,remainingHealth);
        assertEquals(0,propsFactions);
    }


    @Test
    void whenReducedTo0HealthThingsAreDestroyed() {
        //given
        var pc=new Character();
        var dummy=new Prop("dummy",50L);
        //when
        pc.dealsDamage(dummy,100L);
        boolean isDummyDestroyed=dummy.isDestroyed();
        long dummyHealth= dummy.getHealth();
        //then
        assertEquals(0L,dummyHealth);
        assertTrue(isDummyDestroyed);
    }


}