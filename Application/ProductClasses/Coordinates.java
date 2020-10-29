/**
 * contains pair of x and y
 */
package com.company.Application.ProductClasses;



import com.company.Application.Exceptions.InfiniteCoordinateException;
import com.company.Application.Exceptions.WrongArgumentException;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * contains pair of x and y
 */
public class Coordinates implements Serializable {
    /** not nullable     */
    private Integer x; //Поле не может быть null
    /** not nullable, can't be greater then 890*/
    private Float y; //Максимальное значение поля: 890, Поле не может быть null


    public Coordinates() {
    }
    /**
    get x
    @return Integer
     */
    @XmlAttribute
    public Integer getX() {
        return x;
    }

    /**
     * set X
     * @param x Integer
     */
    public void setX(Integer x) {

        this.x = x;
    }

    /**
     * get Y
     * @return Float
     */
    @XmlAttribute
    public Float getY() {
        return y;
    }

    /**
     * set Y
     * @param y Float
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setY(Float y) throws WrongArgumentException, InfiniteCoordinateException {
        if (y > 890)
            throw new WrongArgumentException("Введите значение не больше 890: ");
        if (y.isInfinite())
            throw new InfiniteCoordinateException("Введите значение не может быть бесконечным: ");
        this.y = y;
    }

    /**
     * convert to String
     * @return String
     */
    @Override
    public String toString() {
        return String.format("x = %d; y = %f", x,y);
    }
}