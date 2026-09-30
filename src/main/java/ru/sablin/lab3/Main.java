package ru.sablin.lab3;


import ru.sablin.lab3.util.outputFunctions;

import javax.swing.*;

public class Main {
    static void main(String[] args) {
        outputFunctions.simpleLog("Запуск программы");
        outputFunctions.debugLog("Дебаг проверка");
        outputFunctions.warningLog("Проверка вывода ошибки");

        JFrame frame = new JFrame("Таблица/фотоаппараты");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui form = new gui();

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
}
