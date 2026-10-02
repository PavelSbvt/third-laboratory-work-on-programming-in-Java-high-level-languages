package ru.sablin.lab3;

import ru.sablin.lab3.model.Cameras;
import ru.sablin.lab3.model.CamerasManager;
import ru.sablin.lab3.util.outputFunctions;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class Gui {
    private DefaultTableModel tableModel;
    private JTable tableForCameras;
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
    private JLabel labelForInputCameraPrice;
    private JTextField textFieldForInputCameraPrice;
    private JLabel labelForInputCameraYearBuilding;
    private JTextField textFieldForInputCameraYearBuilding;
    private JLabel labelForInputCameraEquipment;
    private JTextField textFieldForInputCameraEquipment;
    private JLabel labelInputCameraLens;
    private JTextField textFieldForInputCameraLens;
    private JLabel labelForInputCameraSerialNumber;
    private JTextField textFieldForInputCameraSerialNumber;
    private JLabel labelForInputCameraMileage;
    private JTextField textFieldForInputCameraMileage;
    private JButton buttonForAddNewCameraToTable;
    private JButton buttonForCreateTableByInputCountStrings;
    private JPanel panelForOutputResultsFunctions;
    private JLabel labelForTitleResultsBlock;
    private JPanel lightBackgroundPanelForOutputResultsBlock;
    private JLabel labelCalculateMostNotWearCamera;
    private JLabel labelForOutputFindedNotMostWearedCamera;
    private JLabel labelForTitleMostProfitableCamera;
    private JLabel labelForOutputFindedMostProfitableCamera;
    private JLabel labelForMostProfitableCameraFromMileagePriceAndLens;
    private JLabel labelForOutputMostProfitableCameraFromMileagePriceAndLens;
    private JPanel panelForWindowTitle;

    private static final String[] COLUMNS = {
            "Модель", "Бренд", "Мп", "Цена", "Год",
            "Комплектация", "Объектив", "Серийный номер", "Пробег"
    };

    public Gui() {
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        CamerasManager manager = new CamerasManager();

        tableForCameras.setModel(tableModel);

        tableForCameras.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableForCameras.setDefaultRenderer(Object.class, centerRenderer);

        inputCountStrings.setBorder(BorderFactory.createEmptyBorder(
                3, 3, 3, 3));
        lightPanelIntoPanelForAddCamera.setBorder(BorderFactory.createEmptyBorder(
                10, 10, 10, 10));

        buttonForAddNewCameraToTable.addActionListener(e -> onAddCameraClicked());
        buttonForCreateTableByInputCountStrings.addActionListener(
                e -> createTableByCountStrings(manager));

        this.setTextForLabelNotMostWearCamera(manager);
        this.setTextForLabelMostProfitableCamera(manager);
        this.setTextForLabelWithMostProfitableCameraFromPriceMileageAndLens(manager);

        outputFunctions.debugLog("Конструктор класса GUI");
    }

    public void setTextForLabelWithMostProfitableCameraFromPriceMileageAndLens(
            CamerasManager manager){
        List<Cameras> cameras = manager.getCameras();

        if (cameras == null || cameras.isEmpty()) {
            labelForOutputMostProfitableCameraFromMileagePriceAndLens.setText(
                    "Список камер пуст");
            outputFunctions.warningLog(
                    "Список камер пуст, невозможно найти самую выгодную камеру");
            return;
        }

        Cameras requiredCamera = cameras.get(0);
        for (int i = 1; i < cameras.size(); i++) {
            Cameras currentCamera = cameras.get(i);
            if ((currentCamera.getPrice() < requiredCamera.getPrice())
                    && (currentCamera.getMileageOfCamera() <
                    requiredCamera.getMileageOfCamera())
                    && (currentCamera.getEquipment().equals("kit")
                    || currentCamera.getEquipment().equals("set"))) {
                requiredCamera = currentCamera;
            }
        }

        String resultText = String.format(
                "%s (%s), серийный номер: %d, пробег %d, цена %d, " +
                "комплектация %s, объектив %s",
                requiredCamera.getModel(),
                requiredCamera.getBrand(),
                requiredCamera.getSerialNumber(),
                requiredCamera.getMileageOfCamera(),
                requiredCamera.getPrice(),
                requiredCamera.getEquipment(),
                requiredCamera.getCompleteLens());

        labelForOutputMostProfitableCameraFromMileagePriceAndLens.setText(resultText);
        outputFunctions.simpleLog("Найдена самая выгодная камера " +
                "(пробег, цена, объектив): \n" + resultText);
    }

    public void setTextForLabelMostProfitableCamera(CamerasManager manager){
        List<Cameras> cameras = manager.getCameras();
        if (cameras == null || cameras.isEmpty()) {
            labelForOutputFindedMostProfitableCamera.setText("Список камер пуст");
            outputFunctions.warningLog(
                    "Список камер пуст, невозможно найти самую выгодную по цене камеру");
            return;
        }

        Cameras requiredCamera = cameras.get(0);
        for (int i = 1; i < cameras.size(); i++) {
            Cameras currentCamera = cameras.get(i);
            if (currentCamera.getPrice() < requiredCamera.getPrice()) {
                requiredCamera = currentCamera;
            }
        }

        String resultText = String.format(
                "%s (%s), серийный номер: %d, пробег %d, цена %d, " +
                "комплектация %s, объектив %s",
                requiredCamera.getModel(),
                requiredCamera.getBrand(),
                requiredCamera.getSerialNumber(),
                requiredCamera.getMileageOfCamera(),
                requiredCamera.getPrice(),
                requiredCamera.getEquipment(),
                requiredCamera.getCompleteLens());

        labelForOutputFindedMostProfitableCamera.setText(resultText);
        outputFunctions.simpleLog(
                "Найдена самая выгодная по цене камера: \n" + resultText);
    }

    public void setTextForLabelNotMostWearCamera(CamerasManager manager) {
        List<Cameras> cameras = manager.getCameras();

        if (cameras == null || cameras.isEmpty()) {
            labelForOutputFindedNotMostWearedCamera.setText("Список камер пуст");
            outputFunctions.warningLog(
                    "Список камер пуст, невозможно найти минимальный пробег");
            return;
        }

        Cameras cameraWithMinMileage = cameras.get(0);

        for (int i = 1; i < cameras.size(); i++) {
            Cameras currentCamera = cameras.get(i);
            if (currentCamera.getMileageOfCamera() <
                cameraWithMinMileage.getMileageOfCamera()) {
                    cameraWithMinMileage = currentCamera;
            }
        }

        String resultText = String.format(
                "%s (%s), серийный номер: %d, пробег %d, цена %d," +
                " комплектация %s, объектив %s",
                cameraWithMinMileage.getModel(),
                cameraWithMinMileage.getBrand(),
                cameraWithMinMileage.getSerialNumber(),
                cameraWithMinMileage.getMileageOfCamera(),
                cameraWithMinMileage.getPrice(),
                cameraWithMinMileage.getEquipment(),
                cameraWithMinMileage.getCompleteLens());

        labelForOutputFindedNotMostWearedCamera.setText(resultText);
        outputFunctions.simpleLog(
                "Найдена камера с минимальным пробегом: \n" + resultText);
    }


    public void createTableByCountStrings(CamerasManager manager){
        try{
            int camerasQuantity = Integer.parseInt(inputCountStrings.getText().trim());

            if (camerasQuantity < 10){
                manager.createCamerasNikonInQuantity(
                        10, "D600", "Nikon", 24.1);
            } else {
                manager.createCamerasNikonInQuantity(
                        camerasQuantity, "D600", "Nikon", 24.1);
            }
        } catch (NumberFormatException ex){
            outputFunctions.warningLog("Некорректный ввод: " + ex.getMessage());
            JOptionPane.showMessageDialog(contentPane,
                    "Проверьте поле ввода количества строк талицы",
                    "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            manager.createCamerasNikonInQuantity(
                    10, "D600", "Nikon", 24.1);
        }
        this.addCameras(manager);

        this.setTextForLabelNotMostWearCamera(manager);
        this.setTextForLabelMostProfitableCamera(manager);
        this.setTextForLabelWithMostProfitableCameraFromPriceMileageAndLens(manager);
    }

    public void onAddCameraClicked() {
        try {
            String model = textFieldInputCameraModel.getText().trim();
            String brand = textFieldForInputCameraBrand.getText().trim();
            double megapixels = Double.parseDouble(
                    textFieldForInputCountMegapixels.getText().trim());
            int price = Integer.parseInt(
                    textFieldForInputCameraPrice.getText().trim());
            int year = Integer.parseInt(
                    textFieldForInputCameraYearBuilding.getText().trim());
            String equipment = textFieldForInputCameraEquipment.getText().trim();
            String lens = textFieldForInputCameraLens.getText().trim();
            int serial = Integer.parseInt(
                    textFieldForInputCameraSerialNumber.getText().trim());
            int mileage = Integer.parseInt(
                    textFieldForInputCameraMileage.getText().trim());

            if (model.isEmpty() || brand.isEmpty()) {
                outputFunctions.warningLog("Модель и бренд не могут быть пустыми");
                JOptionPane.showMessageDialog(contentPane,
                        "Заполните модель и бренд", "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Cameras camera = new Cameras(model, brand, megapixels, price, year,
                    equipment, lens, serial, mileage);

            addCamera(camera);

            outputFunctions.simpleLog(
                    "Камера добавлена вручную: " + brand + " " + model);

            clearInputFields();

        } catch (NumberFormatException ex) {
            outputFunctions.warningLog("Некорректный ввод: " + ex.getMessage());
            JOptionPane.showMessageDialog(contentPane,
                    "Проверьте числовые поля (Мп, цена, год, серийный номер, пробег)",
                    "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearInputFields() {
        textFieldInputCameraModel.setText("");
        textFieldForInputCameraBrand.setText("");
        textFieldForInputCountMegapixels.setText("");
        textFieldForInputCameraPrice.setText("");
        textFieldForInputCameraYearBuilding.setText("");
        textFieldForInputCameraEquipment.setText("");
        textFieldForInputCameraLens.setText("");
        textFieldForInputCameraSerialNumber.setText("");
        textFieldForInputCameraMileage.setText("");
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

        CamerasManager manager = new CamerasManager();

        this.setTextForLabelNotMostWearCamera(manager);
        this.setTextForLabelMostProfitableCamera(manager);
        this.setTextForLabelWithMostProfitableCameraFromPriceMileageAndLens(manager);
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
