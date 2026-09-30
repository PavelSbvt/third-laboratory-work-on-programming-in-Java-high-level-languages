package ru.sablin.lab3.model;


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
    private double price;
    // год производства
    private int year;
    // комплектация (например, kit или body)
    private String equipment;
    // Комплектный детектив (если комлект без объектива, "нет")
    public String completeLens;

    public Cameras(String model, String brand, double megapixels,
                   double price, int year, String equipment,
                   String completeLens) {
        this.model = model;
        this.brand = brand;
        this.megapixels = megapixels;
        this.price = price;
        this.year = year;
        this.equipment = equipment;
        this.completeLens = completeLens;
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
    public double getPrice() {
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
    public void setPrice(double price) {
        this.price = price;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setEquipment(String equipment){
        this.equipment = equipment;
    }

    public void setCompleteLens(String completeLens) {
        this.completeLens = completeLens;
    }
}
