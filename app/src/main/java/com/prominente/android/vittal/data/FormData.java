package com.prominente.android.vittal.data;

/**
 * Created by Pablo Poza on 12/4/2017.
 */

public class FormData {

    private final static String[] coverages = new String[] {
        "AP#100", "AP#200", "AP#300", "AP#400", "AP#500"
    };

    private final static String[] places = new String[] {
        "Tipo de aréa protegida", "Comercio Menores", "Micro Escolar", "Pub", "Hotel", "Otro"
    };

    private final static String[] locations = new String[] {
        "Localidad", "Avellaneda", "Lanus", "Capital Federal"
    };

    private final static String[] paymentModes = new String[] {
        "Tipo de pago",  "Efectivo", "Contado", "Cheque" ,"Tarjeta de credito/CBU"
    };
    private static String[] sellerTypes = new String[] {
            "Vendedor Logueado", "Otro vendedor"
    };
    private static String[] circuits = new String[] {
            "Circuito 1", "Circuito 2"
    };
    private static String[] radius = new String[] {
            "Radio 1", "Radio 2", "Radio 3"
    };
    private static String[] promos = new String[] {
            "Promo 1", "Promo 2","Promo 3"
    };

    private static String[] banks = new String[] {
            "Hipotecario", "Ciudad","Nacion" , "HSBC", "Santader"
    };

    public static String[] getBanks() {
        return banks;
    }

    public static String[] getPlaces() {
        return places;
    }

    public static String[] getCoverages() {
        return coverages;
    }

    public static String[] getLocations() {
        return locations;
    }

    public static String[] getPaymentModes() {
        return paymentModes;
    }

    public static String[] getSellerTypes() {
        return sellerTypes;
    }

    public static String[] getCircuits() {
        return circuits;
    }

    public static String[] getRadius() {
        return radius;
    }

    public static String[] getPromos() {
        return promos;
    }
}
