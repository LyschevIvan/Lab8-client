package com.company.Application.ProductClasses;

import com.company.Application.Exceptions.InfiniteCoordinateException;
import com.company.Application.Exceptions.WrongArgumentException;

import java.util.Date;

public class ProductForRow{
    private Integer key;
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**can't be empty, might be not null    */
    private String name; //Поле не может быть null, Строка не может быть пустой

    private Integer x; //Поле не может быть null
    /** not nullable, can't be greater then 890*/
    private Float y; //Максимальное значение поля: 890, Поле не может быть null
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
    private String personName; //Поле не может быть null, Строка не может быть пустой
    /** nullable, might be more then 0    */
    private Long personHeight; //Поле может быть null, Значение поля должно быть больше 0
    /** nullable, might be more then 0   */
    private Float personWeight; //Поле может быть null, Значение поля должно быть больше 0
    /** nullable  */
    private Color personHairColor; //Поле может быть null

    public ProductForRow() {
    }

    public ProductForRow(Integer key , Product product) {
        this.key = key;
        this.id = product.getId();
        this.name = product.getName();
        this.x = product.getCoordinates().getX();
        this.y = product.getCoordinates().getY();
        this.creationDate = product.getCreationDate();
        this.price = product.getPrice();
        this.partNumber = product.getPartNumber();
        this.manufactureCost = product.getManufactureCost();
        this.unitOfMeasure = product.getUnitOfMeasure();
        this.personName = product.getOwner().getName();
        this.personHeight = product.getOwner().getHeight();
        this.personWeight = product.getOwner().getWeight();
        this.personHairColor = product.getOwner().getHairColor();
    }

    public static ProductForRow getProductForRow(Integer key ,Product product){
        return new ProductForRow(key, product);
    }
    public Product getProduct(){
        try{
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            Coordinates coordinates = new Coordinates();
            coordinates.setX(x);
            coordinates.setY(y);
            product.setCoordinates(coordinates);
            product.setCreationDate(creationDate);
            product.setPrice(price);
            product.setPartNumber(partNumber);
            product.setManufactureCost(manufactureCost);
            product.setUnitOfMeasure(unitOfMeasure);
            Person owner = new Person();
            owner.setName(personName);
            owner.setHeight(personHeight);
            owner.setWeight(personWeight);
            owner.setHairColor(personHairColor);
            product.setOwner(owner);
            return product;
        } catch (WrongArgumentException | InfiniteCoordinateException e) {
            return null;
        }


    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public long getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(long manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public String getUnitOfMeasure() {
        if (unitOfMeasure == null)
            return "NULL";
        return unitOfMeasure.toString();
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getPersonHeight() {
        return personHeight;
    }

    public void setPersonHeight(Long personHeight) {
        this.personHeight = personHeight;
    }

    public Float getPersonWeight() {
        return personWeight;
    }

    public void setPersonWeight(Float personWeight) {
        this.personWeight = personWeight;
    }

    public String getPersonHairColor() {
        if (personHairColor == null)
            return "NULL";
        return personHairColor.toString();
    }

    public void setPersonHairColor(Color personHairColor) {
        this.personHairColor = personHairColor;
    }

    @Override
    public String toString() {
        return "ProductForRow{" +
                "key=" + key +
                '}';
    }
}
