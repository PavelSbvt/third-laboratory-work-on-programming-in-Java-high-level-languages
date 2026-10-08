package ru.sablin.lab3;

import ru.sablin.lab3.model.Cameras;
import ru.sablin.lab3.model.CamerasManager;
import ru.sablin.lab3.util.outputFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Класс, автоматически созданный Swing Designer, для работы с
 * пользовательским интерфейсом и выводом данных на таблицу,
 * сохранения данных из таблицы в двумерный массив
 */
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
    private JButton buttonUpdateStatistic;
    private JLabel averagePrice;
    private JLabel labelForoutputAvaragePrice;
    private JPanel panelForGroupUpeerWidgets;

    private static final String[] COLUMNS = {
            "Модель", "Бренд", "Мп", "Цена", "Год",
            "Комплектация", "Объектив", "Серийный номер", "Пробег"
    };


    /**
     * Конструктор класса
     */
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
        buttonUpdateStatistic.addActionListener(event -> updateStatistic());

        this.updateStatistic();

        outputFunctions.debugLog("Конструктор класса GUI");
    }


    /**
     * Функция для поиска в таблице камеры с самой низкой ценой и индикации
     * найденной камеры в отдельный лебл блока результатов вычислительных функций
     *
     * @param dataMas - принимает двумерный массив с данными таблицы
     */
    public void setTextForLabelWithMostProfitableCameraFromPriceMileageAndLens(
            Object[][] dataMas){

        if (tableModel.getRowCount() == 0) {
            labelForOutputMostProfitableCameraFromMileagePriceAndLens.setText(
                    "Список камер пуст");
            outputFunctions.warningLog(
                    "Список камер пуст, невозможно найти самую выгодную камеру");
            return;
        }

        int requiredCameraPrice = Integer.parseInt(dataMas[0][3].toString().trim());
        int requiredCameraMileage = Integer.parseInt(dataMas[0][8].toString().trim());
        String requiredCameraEquipment = (String) dataMas[0][5];

        int requiredCameraIndex = 0;

        for (int i = 1; i < tableModel.getRowCount(); i++) {
            int currentCameraPrice = Integer.parseInt(dataMas[i][3].toString().trim());
            int currentCameraMileage = Integer.parseInt(dataMas[i][8].toString().trim());
            String currentCameraEquipment = (String) dataMas[i][5];
            if ((currentCameraMileage < requiredCameraMileage)
                && (currentCameraPrice <
                requiredCameraPrice)
                && (currentCameraEquipment.equals("kit")
                || currentCameraEquipment.equals("set"))) {
                    requiredCameraPrice = currentCameraPrice;
                    requiredCameraMileage = currentCameraMileage;
                    requiredCameraEquipment = currentCameraEquipment;
                    requiredCameraIndex = i;
            }
        }

        String resultText = String.format(
                "%s (%s), серийный номер: %d, пробег %d, цена %d, " +
                "комплектация %s, объектив %s",
                dataMas[requiredCameraIndex][0],
                dataMas[requiredCameraIndex][1],
                Integer.parseInt(dataMas[requiredCameraIndex][7].toString().trim()),
                Integer.parseInt(dataMas[requiredCameraIndex][8].toString().trim()),
                Integer.parseInt(dataMas[requiredCameraIndex][3].toString().trim()),
                dataMas[requiredCameraIndex][5],
                dataMas[requiredCameraIndex][6]);

        labelForOutputMostProfitableCameraFromMileagePriceAndLens.setText(resultText);
        outputFunctions.simpleLog("Найдена самая выгодная камера " +
                "(пробег, цена, объектив): \n" + resultText);
    }


    /**
     * Функция для нахождения самой "выгодной" камеры из списка (проверка на минимально
     * возможные пробег, цену и наличие объектива в комплекте) и показ найденного
     * экземпляра в лейбле в блоке результатов работы функций-обработчиков данных таблицы
     *
     * @param dataMas - принимает двумерный массив с данными из таблицы
     */
    public void setTextForLabelMostProfitableCamera(Object[][] dataMas){

        if (tableModel.getRowCount() == 0) {
            labelForOutputFindedMostProfitableCamera.setText("Список камер пуст");
            outputFunctions.warningLog(
                    "Список камер пуст, невозможно найти самую выгодную по цене камеру");
            return;
        }

        int requiredCameraPrice = Integer.parseInt(dataMas[0][3].toString().trim());

        int requiredCameraIndex = 0;

        for (int i = 1; i < tableModel.getRowCount(); i++) {
            int currentCameraPrice = Integer.parseInt(dataMas[i][3].toString().trim());
            if (currentCameraPrice < requiredCameraPrice) {
                requiredCameraPrice = currentCameraPrice;
                requiredCameraIndex = i;
            }
        }

        String resultText = String.format(
                "%s (%s), серийный номер: %d, пробег %d, цена %d, " +
                "комплектация %s, объектив %s",
                dataMas[requiredCameraIndex][0],
                dataMas[requiredCameraIndex][1],
                Integer.parseInt(dataMas[requiredCameraIndex][7].toString().trim()),
                Integer.parseInt(dataMas[requiredCameraIndex][8].toString().trim()),
                Integer.parseInt(dataMas[requiredCameraIndex][3].toString().trim()),
                dataMas[requiredCameraIndex][5],
                dataMas[requiredCameraIndex][6]);

        labelForOutputFindedMostProfitableCamera.setText(resultText);
        outputFunctions.simpleLog(
                "Найдена самая выгодная по цене камера: \n" + resultText);
    }


    /**
     * Функция для нахождения в таблице камеры с минимальным пробегом и вывод
     * информации о ней в лейбл в блоке результатов функций для работы с данными таблицы
     *
     * @param dataMas - принимает двумерный массив с данными таблицы
     */
    public void setTextForLabelNotMostWearCamera(Object[][] dataMas) {

        if (tableModel.getRowCount() == 0) {
            labelForOutputFindedNotMostWearedCamera.setText("Список камер пуст");
            outputFunctions.warningLog(
                    "Список камер пуст, невозможно найти минимальный пробег");
            return;
        }

        int cameraWithMinMileage = Integer.parseInt(dataMas[0][8].toString().trim());

        int indexCameraWithMinMileage = 0;

        for (int i = 1; i < tableModel.getRowCount(); i++) {
            int currentCameraMileage = Integer.parseInt(dataMas[i][8].toString().trim());
            if (currentCameraMileage <
                cameraWithMinMileage) {
                    cameraWithMinMileage = currentCameraMileage;
                    indexCameraWithMinMileage = i;
            }
        }

        String resultText = String.format(
                "%s (%s), серийный номер: %d, пробег %d, цена %d," +
                " комплектация %s, объектив %s",
                dataMas[indexCameraWithMinMileage][0],
                dataMas[indexCameraWithMinMileage][1],
                Integer.parseInt(dataMas[indexCameraWithMinMileage][7].toString().trim()),
                Integer.parseInt(dataMas[indexCameraWithMinMileage][8].toString().trim()),
                Integer.parseInt(dataMas[indexCameraWithMinMileage][3].toString().trim()),
                dataMas[indexCameraWithMinMileage][5],
                dataMas[indexCameraWithMinMileage][6]);

        labelForOutputFindedNotMostWearedCamera.setText(resultText);
        outputFunctions.simpleLog(
                "Найдена камера с минимальным пробегом: \n" + resultText);
    }


//    public void calculate


    /**
     * Функция для создания и доавления в таблицу строк, число которых указывает сам
     * пользователь в отдельном блоке, с случайными полями (часть - случайно заполненные)
     *
     * @param manager - принимает объект класса CamerasManager
     */
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

        this.updateStatistic();
    }


    /**
     * Функция для обновления данных статистики, отображыющейся в блоке результатов,
     * которую подсчитывают вычислительные функции. Вызывает эти самые вычислительные
     * функции для нового расчёта. Также обновляет таблицу с данными таблицы.
     */
    public void updateStatistic(){
        Object[][] tableData = this.getDataFromTable();

        this.setTextForLabelNotMostWearCamera(
                tableData);
        this.setTextForLabelMostProfitableCamera(
                tableData);
        this.setTextForLabelWithMostProfitableCameraFromPriceMileageAndLens(
                tableData);
    }


    /**
     * Функция, которая вызывается при нажатии кнопки добавления в таблицу новой строки
     * (её создаёт сам пользователь, заполняя поля в блоке создания строки).
     * Собирает данные из полей ввода и добавляет данные в таблицу.
     */
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


    /**
     * Функция очистки полей ввода для создания камеры.
     */
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


    /**
     * Функция для добавления камеры в таблицу.
      */
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

        this.updateStatistic();
    }


    /**
     * Функция для добавления в таблицу всех камер, которые есть в листе камер
     * из класса CamerasManager
     * @param manager - принимает объект класса CamerasManager
     */
    public void addCameras(CamerasManager manager) {
        for (Cameras camera : manager.getCameras()) {
            addCamera(camera);
        }
    }


    /**
     * Функция для получения данных таблицы.
     * @return Object[][] - возвращает двумерный массив с данными из ячеек таблицы
     */
    public Object[][] getDataFromTable(){
        Object[][] data = new Object[tableModel.getRowCount()][
                tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                data[i][j] = tableModel.getValueAt(i, j);
            }
        }

        return data;
    }


    /**
     * Функция для получения панели с Gui-элементами
     * @return возвражает объект JPanel, который содержит все виджеты окна,
     * созданные в Swing Designer
     */
    public JPanel getContentPane() {
        return contentPane;
    }
}
