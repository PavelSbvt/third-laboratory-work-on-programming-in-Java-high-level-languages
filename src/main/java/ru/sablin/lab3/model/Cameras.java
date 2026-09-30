package ru.sablin.lab3.model;


public class Cameras {
    private String model;
    private String brand;
    private int megapixels;
    private double price;
    private int year;
    private String equipment;

    public Cameras(String model, String brand, int megapixels,
                   double price, int year, String equipment) {
        this.model = model;
        this.brand = brand;
        this.megapixels = megapixels;
        this.price = price;
        this.year = year;
        this.equipment = equipment;
    }

    public String getModel() {
        return model; }
    public String getBrand() {
        return brand; }
    public int getMegapixels() {
        return megapixels; }
    public double getPrice() {
        return price; }
    public int getYear() {
        return year; }
    public String isEquipment() {
        return equipment; }

    public void setModel(String model) {
        this.model = model; }
    public void setBrand(String brand) {
        this.brand = brand; }
    public void setMegapixels(int megapixels) {
        this.megapixels = megapixels; }
    public void setPrice(double price) {
        this.price = price; }
    public void setYear(int year) {
        this.year = year; }
    public void setEquipment(String equipment){
        this.equipment = equipment; }
}
