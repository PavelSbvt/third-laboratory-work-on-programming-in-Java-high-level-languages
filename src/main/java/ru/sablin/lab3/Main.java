package ru.sablin.lab3;

import ru.sablin.lab3.model.Cameras;
import ru.sablin.lab3.util.outputFunctions;

import java.util.Random;

import javax.swing.*;

public class Main {
    public static void createWindowUI(){
        JFrame frame = new JFrame("Таблица/фотоаппараты");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Gui form = new Gui();

        frame.setContentPane(form.getContentPane());
        JPanel content = form.getContentPane();

        JScrollPane scroll = new JScrollPane(content);

        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);

        frame.setContentPane(scroll);

        frame.setSize(900, 700);
        frame.setMinimumSize(new java.awt.Dimension(500, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static Cameras createNewCameraNikon(){
        String model = "D600";
        String brand = "Nikon";
        double megapixels = 24.1;

        Random rnd = new Random();

        String[] massiveRandomEquipment = {"body", "kit", "set"};

        String equipment =massiveRandomEquipment[rnd.nextInt(massiveRandomEquipment.length)];

        int price = rnd.nextInt(22000) + 48000;

        int year = rnd.nextInt(6) + 2012;

        String completeLens;
        if (equipment.equals("kit")){
            String[] masLens = {"Nikkor 50mm 1.8", "Nikon 24-120"};
            completeLens = masLens[rnd.nextInt(masLens.length)];
        } else {
            completeLens = "None";
        }

        outputFunctions.simpleLog("Создана новая камера: " + brand + " " + model);

        return new Cameras(model, brand, megapixels, price, year,
                equipment, completeLens, 12, 123);
    }

    static void main(String[] args) {
        outputFunctions.simpleLog("Запуск программы");
        outputFunctions.debugLog("Дебаг проверка");
        outputFunctions.warningLog("Проверка вывода ошибки");

        createWindowUI();

//        Cameras camera = createNewCamera("D600", "Nikon", 24.1,
//                58000, 2012, "body", "None");
    }
}
