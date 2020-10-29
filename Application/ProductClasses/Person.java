

package com.company.Application.ProductClasses;


import com.company.Application.Exceptions.WrongArgumentException;

import java.io.Serializable;

/** uses to contain information about person */
public class    Person implements Serializable {
    /** not nullable, can't empty     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /** nullable, might be more then 0    */
    private Long height; //Поле может быть null, Значение поля должно быть больше 0
    /** nullable, might be more then 0   */
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0
    /** nullable  */
    private Color hairColor; //Поле может быть null


    public Person() {
    }
    /**
     * get name
     * @return String
     */

    public String getName() {
        return name;
    }
    /**
     * set name
     * @param name String
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setName(String name) throws WrongArgumentException {
        if (name.matches("( )*"))
            throw new WrongArgumentException("Введите правильное имя владельца (имя не может быть пустым): ");
        this.name = name;
    }
    /**
     * get height
     * @return Long
     */

    public Long getHeight() {
        return height;
    }
    /**
     * set height
     * @param height Long
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setHeight(Long height) throws WrongArgumentException {
        if(height != null)
            if (height <= 0)
                throw new WrongArgumentException("Введите значение больше нуля: ");
        this.height = height;
    }
    /**
     * get weight
     * @return Float
     * */

    public Float getWeight() {
        return weight;
    }
    /**
     * set weight
     * @param weight Float
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setWeight(Float weight) throws WrongArgumentException {
        if(weight != null)
            if (weight <= 0)
                throw new WrongArgumentException("Введите значение больше нуля: ");
        this.weight = weight;
    }
    /**
     * get hair color
     * @return Color
     */

    public Color getHairColor() {
        return hairColor;
    }
    /** set hair color
     * @param hairColor Color
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }
    /**
     * converts to String
     * @return String
     */
    @Override
    public String toString() {
        return name+" "+"hairColor is "+ hairColor+
                "\n height = "+height+" weight = "+weight;

    }
}