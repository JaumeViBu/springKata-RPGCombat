# RPG COMBAT KATA

---

## **Iteration One**

- [x] All Characters, when created, have:
    - [x] Health, starting at 1000
    - [x] Level, starting at 1
    - [x] May be Alive or Dead, starting Alive (Alive may be a true/false)
- [x] Characters can Deal Damage to Characters:
    - [x] Damage is subtracted from Health
    - [x] When damage received exceeds current Health, Health becomes 0 and the character dies
- [x] A Character can Heal a Character:
    - [x] Dead characters cannot be healed
    - [x] Healing cannot raise health above 1000

## **Iteration Two**

- [x] A Character cannot Deal Damage to itself.
- [x] A Character can only Heal itself.
- [x] When dealing damage:
    - [x] If the target is 5 or more Levels above the attacker, Damage is reduced by 50%
    - [x] If the target is 5 or more Levels below the attacker, Damage is increased by 50%

## **Iteration Three**

- [ ] Characters have an attack Max Range.
- [ ] *Melee* fighters have a range of 2 meters.
- [ ] *Ranged* fighters have a range of 20 meters.
- [ ] Characters must be in range to deal damage to a target.

## **Retrospective**

- Are you keeping up with the requirements? Has any iteration been a big challenge?
- Do you feel good about your design? Is it scalable and easily adapted to new requirements?
- Is everything tested? Are you confident in your code?

## **Iteration Four**

- [ ] Characters may belong to one or more Factions.
    - [ ] Newly created Characters belong to no Faction.
- [ ] A Character may Join or Leave one or more Factions.
- [ ] Players belonging to the same Faction are considered Allies.
- [ ] Allies cannot Deal Damage to one another.
- [ ] Allies can Heal one another.

## **Iteration Five**

- [ ] Characters can damage non-character *things* (props).
    - [ ] Anything that has Health may be a target.
    - [ ] These things cannot be Healed and they do not Deal Damage.
    - [ ] These things do not belong to Factions; they are neutral.
    - [ ] When reduced to 0 Health, things are *Destroyed.*
    - [ ] As an example, you may create a Tree with 2000 Health.