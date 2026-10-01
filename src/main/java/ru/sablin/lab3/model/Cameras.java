package ru.sablin.lab3.model;

import ru.sablin.lab3.util.outputFunctions;


/**
 * Класс для создания объекта камеры, информация о котором будет помещаться в таблицу
 */
public class Cameras {
    // Имя модели камеры
    private String model;
    // Марка, бренд, производитлеь
    private String brand;
    // количество мегапикселей матрицы
    private double megapixels;
    // цена камеры
    private int price;
    // год производства
    private int year;
    // комплектация (например, kit или body)
    private String equipment;
    // Комплектный детектив (если комлект без объектива, "нет")
    private String completeLens;
    // Серийный номер
    private int serialNumber;
    // Пробег
    private int mileageOfCamera;

    public Cameras(String model, String brand, double megapixels,
                   int price, int year, String equipment,
                   String completeLens, int serialNumber,
                   int mileageOfCamera) {
        this.model = model;
        this.brand = brand;
        this.megapixels = megapixels;
        this.price = price;
        this.year = year;
        this.equipment = equipment;
        this.completeLens = completeLens;
        this.serialNumber = serialNumber;
        this.mileageOfCamera = mileageOfCamera;
    }

    // Геттеры
    public String getModel() {
        return model;
    }
    public String getBrand() {
        return brand;
    }
    public double getMegapixels() {
        return megapixels;
    }
    public int getPrice() {
        return price;
    }
    public int getYear() {
        return year;
    }
    public String getEquipment() {
        return equipment;
    }
    public String getCompleteLens() {
        return completeLens;
    }
    public int getSerialNumber() {
        return serialNumber;
    }
    public int getMileageOfCamera() {
        return mileageOfCamera;
    }


    // Сеттеры
    public void setModel(String model) {
        this.model = model;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setMegapixels(double megapixels) {
        this.megapixels = megapixels;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setEquipment(String equipment){
        this.equipment = equipment;
    }
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
    public void setCompleteLens(String completeLens) {
        this.completeLens = completeLens;
    }
    public void setMileageOfCamera(int mileageOfCamera){
        this.mileageOfCamera = mileageOfCamera;
    }
}
