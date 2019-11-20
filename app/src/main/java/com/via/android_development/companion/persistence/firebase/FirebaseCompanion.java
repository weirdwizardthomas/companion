package com.via.android_development.companion.persistence.firebase;

import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.utility.StatCalculator;

import java.util.ArrayList;
import java.util.List;

public class FirebaseCompanion {
    private int id;

    private String name;
    private String profession;
    private int totalLevel;
    private String race;
    private String alignment;
    private int xp;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int speed;

    private int proficiencyBonus;
    private List<String> savingThrowProficiencies;
    private List<String> skillProficiencies;

    private int hitpoints;
    private int maximalHitpoints;
    private int temporaryHitpoints;
    private int hitDieFacets; //number of die's facets rolled
    private int hitDiceMaximum; //maximum dice count
    private int hitDiceRemaining; //number of hit dice available to roll

    private int deathSaveSuccesses;
    private int deathSaveFailures;

    private String background;
    private String personalityTraits;
    private String ideals;
    private String bonds;
    private String flaws;

    public FirebaseCompanion() {

    }

    public FirebaseCompanion(Companion companion) {
        id = companion.getId();
        name = companion.getName();
        profession = companion.getProfession();
        totalLevel = companion.getTotalLevel();
        race = companion.getRace();
        alignment = companion.getAlignment();
        xp = companion.getXp();

        strength = companion.getStrength();
        dexterity = companion.getDexterity();
        constitution = companion.getConstitution();
        intelligence = companion.getIntelligence();
        wisdom = companion.getWisdom();
        charisma = companion.getCharisma();

        speed = companion.getSpeed();

        proficiencyBonus = companion.getProficiencyBonus();
        savingThrowProficiencies = new ArrayList<>(companion.getSavingThrowProficiencies());
        skillProficiencies = new ArrayList<>(companion.getSkillProficiencies());

        hitpoints = companion.getHitpoints();
        maximalHitpoints = companion.getMaximalHitpoints();
        temporaryHitpoints = companion.getTemporaryHitpoints();
        hitDieFacets = companion.getHitDieFacets();
        hitDiceMaximum = companion.getHitDiceMaximum();
        hitDiceRemaining = companion.getHitDiceRemaining();

        deathSaveSuccesses = companion.getDeathSaveSuccesses();
        deathSaveFailures = companion.getDeathSaveFailures();

        background = companion.getBackground();
        personalityTraits = companion.getPersonalityTraits();
        ideals = companion.getIdeals();
        bonds = companion.getBonds();
        flaws = companion.getFlaws();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public void setTotalLevel(int totalLevel) {
        this.totalLevel = totalLevel;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public List<String> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(List<String> savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }

    public List<String> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(List<String> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getMaximalHitpoints() {
        return maximalHitpoints;
    }

    public void setMaximalHitpoints(int maximalHitpoints) {
        this.maximalHitpoints = maximalHitpoints;
    }

    public int getTemporaryHitpoints() {
        return temporaryHitpoints;
    }

    public void setTemporaryHitpoints(int temporaryHitpoints) {
        this.temporaryHitpoints = temporaryHitpoints;
    }

    public int getHitDieFacets() {
        return hitDieFacets;
    }

    public void setHitDieFacets(int hitDieFacets) {
        this.hitDieFacets = hitDieFacets;
    }

    public int getHitDiceMaximum() {
        return hitDiceMaximum;
    }

    public void setHitDiceMaximum(int hitDiceMaximum) {
        this.hitDiceMaximum = hitDiceMaximum;
    }

    public int getHitDiceRemaining() {
        return hitDiceRemaining;
    }

    public void setHitDiceRemaining(int hitDiceRemaining) {
        this.hitDiceRemaining = hitDiceRemaining;
    }

    public int getDeathSaveSuccesses() {
        return deathSaveSuccesses;
    }

    public void setDeathSaveSuccesses(int deathSaveSuccesses) {
        this.deathSaveSuccesses = deathSaveSuccesses;
    }

    public int getDeathSaveFailures() {
        return deathSaveFailures;
    }

    public void setDeathSaveFailures(int deathSaveFailures) {
        this.deathSaveFailures = deathSaveFailures;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPersonalityTraits() {
        return personalityTraits;
    }

    public void setPersonalityTraits(String personalityTraits) {
        this.personalityTraits = personalityTraits;
    }

    public String getIdeals() {
        return ideals;
    }

    public void setIdeals(String ideals) {
        this.ideals = ideals;
    }

    public String getBonds() {
        return bonds;
    }

    public void setBonds(String bonds) {
        this.bonds = bonds;
    }

    public String getFlaws() {
        return flaws;
    }

    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }

    public int getArmourClass() {
        return StatCalculator.abilityModifier(dexterity) + Companion.BASE_ARMOUR_CLASS;
    }

    public int getInitiative() {
        return StatCalculator.abilityModifier(dexterity);
    }

}
