

package com.company.Application.ProductClasses;


import com.company.Application.Exceptions.WrongArgumentException;

import java.io.Serializable;
import java.util.Date;

/**Uses to contain information about product */

public class Product implements Comparable<Product>, Serializable {
    /**unique value, might be more then 0   */
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**can't be empty, might be not null    */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /** unique value, might be more then 0  */
    private Coordinates coordinates; //Поле не может быть null
    /** not nullable, generates automatically  */
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /** might be more then 0   */
    private float price; //Значение поля должно быть больше 0
    /** can't be empty. might be longer then 23 symbols  */
    private String partNumber; //Длина строки должна быть не меньше 23, Строка не может быть пустой, Поле не может быть null
    /** might be more then 0   */
    private long manufactureCost;
    /** not nullable  */
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    /** not nullable  */
    private Person owner; //Поле не может быть null

    /** set Id
     * @param id long */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * set Name
     * @param name String
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setName(String name) throws WrongArgumentException {
        if (name.matches("( )*"))
            throw new WrongArgumentException("Введите правильное название продукта (значение не может быть пустым): ");
        this.name = name;
    }
    /**
     * set Coordinates
     * @param coordinates Coordinates

     */

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    /**
     * set Creation Date
     * @param creationDate Date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * set Price
     * @param price float
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setPrice(float price) throws WrongArgumentException {
        if (price <= 0)
            throw new WrongArgumentException("Введите значение больше нуля: ");
        this.price = price;
    }
    /**
     * set Part number
     * @param partNumber String
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setPartNumber(String partNumber) throws WrongArgumentException {
        if (partNumber.matches("( )*"))
            throw new WrongArgumentException("Введите номер детали правильно(строка не может быть пустой): ");
        if (partNumber.length()<23)
            throw new WrongArgumentException("Введите номер детали правильно(минимум 23 символа): ");
        this.partNumber = partNumber;
    }
    /**
     * set Manufacture cost
     * @param manufactureCost long
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setManufactureCost(long manufactureCost) throws WrongArgumentException {
        if (manufactureCost <= 0)
            throw new WrongArgumentException("Введите значение больше нуля: ");
        this.manufactureCost = manufactureCost;
    }
    /**
     * set Unit of measure
     * @param unitOfMeasure UnitOfMeasure
     */
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
    /**
     * set Name
     * @param owner Person
     * @throws WrongArgumentException if doesn't stick to conditions
     */
    public void setOwner(Person owner) throws WrongArgumentException {
        if (owner == null)
            throw new WrongArgumentException("Владелец должен существоавть");
        this.owner = owner;
    }

    /**
     * get Unit of measure
     * @return UnitOfMeasure
     */

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
    /**
     * get Manufacture cost
     * @return long
     */

    public long getManufactureCost() {
        return manufactureCost;
    }
    /**
     * get Part number
     * @return String
     */

    public String getPartNumber() {
        return partNumber;
    }
    /**
     * get Id
     * @return long
     * */

    public Long getId() {
        return id;
    }
    /**
     * get Name
     * @return String
     * */

    public String getName() {
        return name;
    }
    /**
     * get Coordinates
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    /**
     * get Creation date
     * @return java.util.Date
     */
    public Date getCreationDate() {
        return creationDate;
    }
    /**
     * get Price
     * @return float
     */
    public float getPrice() {
        return price;
    }
    /**
     * get Owner
     * @return Person
     * */

    public Person getOwner() {
        return owner;
    }


    /**
     * converts to String
     * @return String
     */
    @Override
    public String toString() {
        return
                "Product" +
                "\n id = " + id +
                "\n name = '" + name + '\'' +
                "\n coordinates : " + coordinates.toString() +
                "\n creationDate = " + creationDate +
                "\n price=" + price +
                "\n partNumber = '" + partNumber + '\'' +
                "\n manufactureCost = " + manufactureCost +
                "\n unitOfMeasure = " + unitOfMeasure +
                "\n owner is " + owner.toString() +
                '\n';
    }
    /**
     * compares one Product to other
     * @return int
     */
    @Override
    public int compareTo(Product o) {
        return this.id.compareTo(o.id);
    }
}







