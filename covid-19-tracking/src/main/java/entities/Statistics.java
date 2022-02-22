/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Matija
 */
public class Statistics {
    private int numberTested = 0;
    private int numberPositiveOnTest = 0;
    private int numberNewHospitalized = 0;
    private int numberNewDeath = 0;

    public int getNumberTested() {
        return numberTested;
    }

    public void setNumberTested(int numberTested) {
        this.numberTested = numberTested;
    }

    public int getNumberPositiveOnTest() {
        return numberPositiveOnTest;
    }

    public void setNumberPositiveOnTest(int numberPositiveOnTest) {
        this.numberPositiveOnTest = numberPositiveOnTest;
    }

    public int getNumberNewHospitalized() {
        return numberNewHospitalized;
    }

    public void setNumberNewHospitalized(int numberNewHospitalized) {
        this.numberNewHospitalized = numberNewHospitalized;
    }

    public int getNumberNewDeath() {
        return numberNewDeath;
    }

    public void setNumberNewDeath(int numberNewDeath) {
        this.numberNewDeath = numberNewDeath;
    }
    
    
}
