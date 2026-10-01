package ru.sablin.lab3;

import ru.sablin.lab3.model.CamerasManager;
import ru.sablin.lab3.util.outputFunctions;
import ru.sablin.lab3.Gui;

import javax.swing.*;


public class Main {
    public static Gui createWindowUI(){
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

        return form;
    }

    static void main(String[] args) {
        outputFunctions.simpleLog("Запуск программы");

        createWindowUI();

        CamerasManager manager = new CamerasManager();

        manager.createCamerasNikonInQuantity(5, "D600", "Nikon", 24.1);

        Gui gui = createWindowUI();
        gui.addCameras(manager);

        manager.showCamerasArray(manager.camerasNikonD600);
    }

}
