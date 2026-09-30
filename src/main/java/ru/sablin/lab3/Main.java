package ru.sablin.lab3;


import ru.sablin.lab3.model.Cameras;
import ru.sablin.lab3.util.outputFunctions;

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

    public static Cameras createNewCamera(String model, String brand, double megapixels,
                                double price, int year, String equipment,
                                String completeLens){
        outputFunctions.simpleLog("Создана новая камера: " + brand + " " + model);

        return new Cameras(model, brand, megapixels, price, year,
                equipment, completeLens);
    }

    static void main(String[] args) {
        outputFunctions.simpleLog("Запуск программы");
        outputFunctions.debugLog("Дебаг проверка");
        outputFunctions.warningLog("Проверка вывода ошибки");

        createWindowUI();

        Cameras camera = createNewCamera("D600", "Nikon", 24.1,
                58000, 2012, "body", "None");
    }
}
