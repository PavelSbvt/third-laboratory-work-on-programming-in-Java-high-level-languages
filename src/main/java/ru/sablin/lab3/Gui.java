package ru.sablin.lab3;

import ru.sablin.lab3.model.Cameras;
import ru.sablin.lab3.model.CamerasManager;
import ru.sablin.lab3.util.outputFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Gui {
    private DefaultTableModel tableModel;
    private JTable table1;
    private JPanel contentPane;
    private JLabel titleForInputCountStringsUserWants;
    private JTextField inputCountStrings;
    private JLabel windowTitle;
    private JPanel panelForInputCountStrings;
    private JPanel addCameraPanel;
    private JLabel labelAddCameraToTable;
    private JScrollPane scrollPaneForTable;
    private JPanel lightPanelIntoPanelForAddCamera;
    private JLabel labelInputModel;
    private JTextField textFieldInputCameraModel;
    private JTextField textFieldForInputCameraBrand;
    private JLabel labelCameraBrandInput;
    private JTextField textFieldForInputCountMegapixels;
    private JLabel labelForInputMegapixels;

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

        table1.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class, centerRenderer);

        inputCountStrings.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        lightPanelIntoPanelForAddCamera.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
