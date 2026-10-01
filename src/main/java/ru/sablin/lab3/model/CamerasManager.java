package ru.sablin.lab3.model;

import ru.sablin.lab3.util.outputFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CamerasManager{

    public List<Cameras> camerasNikonD600 = new ArrayList<>();

    public void showCamerasArray(List<Cameras> cameraArray){
        outputFunctions.simpleLog("Показ списка с камерами");
        for (int i = 0; i < (cameraArray.size()); i++){
            Cameras camera = cameraArray.get(i);

            int cameraNumber = i + 1;

            outputFunctions.spacerPoints();

            outputFunctions.debugLog(String.format(
                    "Камера №%d:\n    " +
                            "----------------------------\n    " +
                            "модель:.................%s\n    " +
                            "производитель:..........%s\n    " +
                            "кол-во мегапикселей:....%.1f\n    " +
                            "стоимость:..............%d рублей\n    " +
                            "год выпуска:............%d\n    " +
                            "комплектация:...........%s\n    " +
                            "объектив:...............%s\n    " +
                            "серийный номер:.........%d\n    " +
                            "пробег затвора:.........%d\n    " +
                            "----------------------------",
                    cameraNumber, camera.getBrand(), camera.getModel(),
                    camera.getMegapixels(), camera.getPrice(), camera.getYear(),
                    camera.getEquipment(), camera.getCompleteLens(),
                    camera.getSerialNumber(), camera.getMileageOfCamera()));

            outputFunctions.spacerPoints();

        }
    }

    public void createNewCameraNikon(String model, String brand,
                                     double megapixels){

        Random rnd = new Random();

        String[] massiveRandomEquipment = {"body", "kit", "set"};

        String equipment =massiveRandomEquipment[rnd.nextInt(massiveRandomEquipment.length)];

        int price = rnd.nextInt(22000) + 48000;

        int year = rnd.nextInt(6) + 2012;

        String completeLens;
        if (equipment.equals("kit")){
            String[] masLens = {"Nikkor 50mm 1.8", "Nikon 24-120"};
            completeLens = masLens[rnd.nextInt(masLens.length)];
        } else if (equipment.equals("set")) {
            String[] masLens = {"Tamron SP macro 2.8 70-200", "Nikon 70-200 2.8"};
            completeLens = masLens[rnd.nextInt(masLens.length)];
        } else {
            completeLens = "None";
        }

        int serialNumber = rnd.nextInt(100000) + 100000;

        int mileageOfCamera = rnd.nextInt(280000);

        outputFunctions.simpleLog(String.format(
                "Создана новая камера:\n    " +
                        "----------------------------\n    " +
                        "модель:.................%s\n    " +
                        "производитель:..........%s\n    " +
                        "кол-во мегапикселей:....%.1f\n    " +
                        "стоимость:..............%d рублей\n    " +
                        "год выпуска:............%d\n    " +
                        "комплектация:...........%s\n    " +
                        "объектив:...............%s\n    " +
                        "серийный номер:.........%d\n    " +
                        "пробег затвора:.........%d\n    " +
                        "----------------------------",
                brand, model, megapixels, price, year, equipment,
                completeLens, serialNumber, mileageOfCamera));

        Cameras newCamera = new Cameras(model, brand, megapixels, price, year,
                equipment, completeLens, serialNumber, mileageOfCamera);

        this.camerasNikonD600.add(newCamera);
    }

    public void createCamerasNikonInQuantity(int quantity, String model, String brand,
                                             double megapixels){

        for (int counter = 0; counter < quantity; counter++){
            this.createNewCameraNikon(model, brand, megapixels);
        }

        outputFunctions.simpleLog(String.format("Создано камер: %d, модель - %s," +
                " марка - %s", quantity, model, brand));
    }

    public void createCamera(String model, String brand, double megapixels,
                                int price, int year, String equipment,
                                String completeLens, int serialNumber,
                                int mileageOfCamera) {
        Cameras camera = new Cameras(model, brand, megapixels, price, year,
                equipment, completeLens, serialNumber, mileageOfCamera);

        camerasNikonD600.add(camera);

        outputFunctions.simpleLog(String.format(
                "Создана новая камера:\n    " +
                        "----------------------------\n    " +
                        "модель:.................%s\n    " +
                        "производитель:..........%s\n    " +
                        "кол-во мегапикселей:....%.1f\n    " +
                        "стоимость:..............%d рублей\n    " +
                        "год выпуска:............%d\n    " +
                        "комплектация:...........%s\n    " +
                        "объектив:...............%s\n    " +
                        "серийный номер:.........%d\n    " +
                        "пробег затвора:.........%d\n    " +
                        "----------------------------",
                brand, model, megapixels, price, year, equipment,
                completeLens, serialNumber, mileageOfCamera));
    }

    public List<Cameras> getCameras() {
        return camerasNikonD600;
    }
}

