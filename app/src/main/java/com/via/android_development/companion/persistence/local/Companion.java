package com.via.android_development.companion.persistence.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.utility.enums.Alignment;
import com.via.android_development.companion.utility.enums.Profession;
import com.via.android_development.companion.utility.enums.Race;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Companion {
    public final static int DEATH_SAVING_THROWS = 3;
    public final static int MIN_LEVEL = 1;
    public final static int MAX_LEVEL = 20;
    public final static int MIN_ATTRIBUTE_LEVEL = 0;
    public final static int MAX_ATTRIBUTE_LEVEL = 20;
    public final static int BASE_ARMOUR_CLASS = 10;
    public final static int DEFAULT_ATTRIBUTE_VALUE = 10;

    @PrimaryKey(autoGenerate = true)
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
    private Set<String> savingThrowProficiencies;
    private Set<String> skillProficiencies;

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

    public Companion() {
        this.name = "";
        totalLevel = MIN_LEVEL;
        race = String.valueOf(Race.HUMAN);
        profession = String.valueOf(Profession.FIGHTER);
        alignment = String.valueOf(Alignment.TRUE_NEUTRAL);
        xp = 0;

        strength = DEFAULT_ATTRIBUTE_VALUE;
        dexterity = DEFAULT_ATTRIBUTE_VALUE;
        constitution = DEFAULT_ATTRIBUTE_VALUE;
        intelligence = DEFAULT_ATTRIBUTE_VALUE;
        wisdom = DEFAULT_ATTRIBUTE_VALUE;
        charisma = DEFAULT_ATTRIBUTE_VALUE;

        speed = 30;

        proficiencyBonus = 2;
        savingThrowProficiencies = new HashSet<>();
        skillProficiencies = new HashSet<>();

        hitpoints = 0;
        maximalHitpoints = 0;
        temporaryHitpoints = 0;
        hitDieFacets = 0; //number of die's facets rolled
        hitDiceMaximum = 0;
        hitDiceRemaining = 0; //number of hit dice available to roll

        deathSaveSuccesses = 0;
        deathSaveFailures = 0;

        background = "";
        personalityTraits = "";
        ideals = "";
        bonds = "";
        flaws = "";
    }

    public Companion(FirebaseCompanion firebaseCompanion) {
        name = firebaseCompanion.getName();
        profession = firebaseCompanion.getProfession();
        totalLevel = firebaseCompanion.getTotalLevel();
        race = firebaseCompanion.getRace();
        alignment = firebaseCompanion.getAlignment();
        xp = firebaseCompanion.getXp();

        strength = firebaseCompanion.getStrength();
        dexterity = firebaseCompanion.getDexterity();
        constitution = firebaseCompanion.getConstitution();
        intelligence = firebaseCompanion.getIntelligence();
        wisdom = firebaseCompanion.getWisdom();
        charisma = firebaseCompanion.getCharisma();

        speed = firebaseCompanion.getSpeed();

        proficiencyBonus = firebaseCompanion.getProficiencyBonus();
        savingThrowProficiencies = new HashSet<>(firebaseCompanion.getSavingThrowProficiencies());
        skillProficiencies = new HashSet<>(firebaseCompanion.getSkillProficiencies());

        hitpoints = firebaseCompanion.getHitpoints();
        maximalHitpoints = firebaseCompanion.getMaximalHitpoints();
        temporaryHitpoints = firebaseCompanion.getTemporaryHitpoints();
        hitDieFacets = firebaseCompanion.getHitDieFacets();
        hitDiceMaximum = firebaseCompanion.getHitDiceMaximum();
        hitDiceRemaining = firebaseCompanion.getHitDiceRemaining();

        deathSaveSuccesses = firebaseCompanion.getDeathSaveSuccesses();
        deathSaveFailures = firebaseCompanion.getDeathSaveFailures();

        background = firebaseCompanion.getBackground();
        personalityTraits = firebaseCompanion.getPersonalityTraits();
        ideals = firebaseCompanion.getIdeals();
        bonds = firebaseCompanion.getBonds();
        flaws = firebaseCompanion.getFlaws();
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

    public Set<String> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(Set<String> savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }

    public Set<String> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(Set<String> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

}
