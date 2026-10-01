package ru.sablin.lab3;

import ru.sablin.lab3.model.Cameras;
import ru.sablin.lab3.model.CamerasManager;
import ru.sablin.lab3.util.outputFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Gui {
    private DefaultTableModel tableModel;
    private JTable table1;
    private JPanel contentPane;
    private JLabel titleForInputCountColumnsUserWants;
    private JTextField inputCountColumns;
    private JLabel windowTitle;

    private static final String[] COLUMNS = {
            "Модель", "Бренд", "Мп", "Цена", "Год", "Комплектация", "Объектив", "Серийный номер", "Пробег"
    };

    public Gui() {
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        table1.setModel(tableModel);

        outputFunctions.debugLog("Конструктор класса GUI");
    }

    public void addCamera(Cameras camera) {
        tableModel.addRow(new Object[]{
                camera.getModel(),
                camera.getBrand(),
                camera.getMegapixels(),
                camera.getPrice(),
                camera.getYear(),
                camera.getEquipment(),
                camera.getCompleteLens(),
                camera.getSerialNumber(),
                camera.getMileageOfCamera()
        });
    }

    public void addCameras(CamerasManager manager) {
        for (Cameras camera : manager.getCameras()) {
            addCamera(camera);
        }
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
