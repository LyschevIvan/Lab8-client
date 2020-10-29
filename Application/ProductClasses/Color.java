
package com.company.Application.ProductClasses;
/**
 * enum Colors
 */
public enum Color {
    GREEN("Green"),
    RED("Red"),
    BLACK("Black"),
    BLUE("Blue"),
    ORANGE("Orange");
    String name;

    Color(String name) {
        this.name = name;
    }
    /**
     * convert Color to String
     * @return String
     */
    @Override
    public String toString() {
        return name;
    }
}