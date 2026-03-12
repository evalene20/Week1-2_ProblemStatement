class ParkingLot {

    String[] table = new String[500];

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % 500;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null) {
            index = (index + 1) % 500;
        }

        table[index] = plate;

        return index;
    }

    public void exitVehicle(String plate) {

        for (int i = 0; i < table.length; i++) {

            if (plate.equals(table[i])) {
                table[i] = null;
                break;
            }
        }
    }
}