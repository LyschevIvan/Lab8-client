
package com.company.Application.ProductClasses;
/**
 * enum units of measure
 */
public enum UnitOfMeasure {
    KILOGRAMS("Kg"),
    PCS("Pcs"),
    GRAMS("Gr"),
    MILLIGRAMS("Mg");
    private String name;

    UnitOfMeasure(String name) {
        this.name = name;
    }

    /**
     * converts UnitOfMeasure to String
     * @return String
     */
    @Override
    public String toString() {
        return name;
    }
}