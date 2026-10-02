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

        int price;

        int year = rnd.nextInt(6) + 2012;

        String completeLens;
        if (equipment.equals("kit")){
            price = rnd.nextInt(22000) + 80000;
            String[] masLens = {
                    "AF-S NIKKOR 24-85mm f/3.5-4.5G ED VR",
                    "AF-S NIKKOR 24-120mm f/4G ED VR", "AF-S NIKKOR 24-70mm f/2.8G ED",
                    "AF-S NIKKOR 24-70mm f/2.8E ED VR", "AF-S NIKKOR 24-50mm f/4-5.6"};
            completeLens = masLens[rnd.nextInt(masLens.length)];
        } else if (equipment.equals("set")) {
            price = rnd.nextInt(22000) + 100000;
            String[] masLens = {
                    "Tamron SP 70-200mm f/2.8 Di VC USD G2",
                    "Sigma 28mm f/1.4 DG HSM Art",
                    "Nikon AF-S 50mm f/1.4G", "Nikon AF-S 85mm f/1.4G",
                    "Nikon AF-S Micro 105mm f/2.8G IF-ED VR",
                    "Sigma 85mm f/1.4 DG HSM Art", "Tamron SP 70-200mm f/2.8 a001 Macro",
                    "Nikon AF-S 200-500mm f/5.6E ED VR",
                    "Tamron SP AF 150-600mm f/5-6.3 Di VC USD Nikon F (A022)"};
            completeLens = masLens[rnd.nextInt(masLens.length)];
        } else {
            price = rnd.nextInt(22000) + 48000;
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

