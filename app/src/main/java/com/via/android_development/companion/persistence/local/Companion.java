package com.via.android_development.companion.persistence.local;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Companion {

    public final static int DEATH_SAVING_THROWS = 3;
    public final static int MIN_LEVEL = 1;
    public final static int MAX_LEVEL = 20;
    public final static int BASE_ARMOUR_CLASS = 10;
    public final static int DEFAULT_ATTRIBUTE_VALUE = 10;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    //private List<String> classes;
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

    //armor class is based on armour, shield & dex modifier
    //private int armorClass;
    //initiative is equal to dex modifier
    //private int initiative;
    private int speed;

    private int proficiencyBonus;
    //private Set<String> savingThrowProficiencies;
    //private Set<String> skillProficiencies;

    private int hitpoints;
    private int maximalHitpoints;
    private int temporaryHitpoints;
    private int hitDieFacets; //number of die's facets rolled
    private int hitDiceMaximum;
    private int hitDiceRemaining; //number of hit dice available to roll

    private int deathSaveSuccesses;
    private int deathSaveFailures;

    private String personalityTraits;
    private String ideals;
    private String bonds;
    private String flaws;

    public Companion() {
    }

    @Ignore
    public Companion(String name) {
        this.name = name;
        //classes = new ArrayList<>();
        totalLevel = MIN_LEVEL;
        race = "Human"; //TODO change to value from enum
        alignment = "True neutral"; ///TODO change to value from enum
        xp = 0;

        strength = DEFAULT_ATTRIBUTE_VALUE;
        dexterity = DEFAULT_ATTRIBUTE_VALUE;
        constitution = DEFAULT_ATTRIBUTE_VALUE;
        intelligence = DEFAULT_ATTRIBUTE_VALUE;
        wisdom = DEFAULT_ATTRIBUTE_VALUE;
        charisma = DEFAULT_ATTRIBUTE_VALUE;

        speed = 30;

        proficiencyBonus = 2;
        //savingThrowProficiencies = new HashSet<>();
        //skillProficiencies = new HashSet<>();

        hitpoints = 0;
        maximalHitpoints = 0;
        temporaryHitpoints = 0;
        hitDieFacets = 0; //number of die's facets rolled
        hitDiceMaximum = 0;
        hitDiceRemaining = 0; //number of hit dice available to roll

        deathSaveSuccesses = 0;
        deathSaveFailures = 0;

        personalityTraits = "";
        ideals = "";
        bonds = "";
        flaws = "";

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

    /*public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }
*/
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

    /*  public Set<String> getSavingThrowProficiencies() {
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
  */
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
}
