package com.laraferrer.carsapi.exception;

public class CarNotFoundException extends Exception {
    public CarNotFoundException(String message) {
        super(message);
    }

    public CarNotFoundException(long carId) {
        super("No se ha encontrado un coche con la ID " + carId);
    }

    public CarNotFoundException() {
        super("Coche no encontrado. Vuelve a hacer una nueva búsqueda.");
    }
}