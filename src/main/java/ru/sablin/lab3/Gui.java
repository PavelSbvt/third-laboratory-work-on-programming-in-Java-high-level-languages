package ru.sablin.lab3;

import ru.sablin.lab3.model.Cameras;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Gui {
    private DefaultTableModel tableModel;
    private JTable table1;
    private JPanel contentPane;

    private static final String[] COLUMNS = {
            "Модель", "Бренд", "Мп", "Цена", "Год", "Комплектация", "Объектив"
    };

    public Gui() {
        // Создаём модель таблицы
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // ручной ввод разрешён
            }
        };
        table1 = new JTable(tableModel);

        // Оборачиваем таблицу в скролл
        JScrollPane scroll = new JScrollPane(table1);

        // Собираем панель
        contentPane = new JPanel(new java.awt.BorderLayout());
        contentPane.add(scroll, java.awt.BorderLayout.CENTER);
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void addCamera(Cameras camera) {
        tableModel.addRow(new Object[]{
                camera.getModel(),
                camera.getBrand(),
                camera.getMegapixels(),
                camera.getPrice(),
                camera.getYear(),
                camera.getEquipment(),
                camera.getCompleteLens()
        });
    }
}
