package Models;

public class VendingMachine {
    private static int counter=0;
    private int id;
    private String name;
    private String location;
    private Compartment compartment;

    public VendingMachine(String name, String location, Compartment compartment) {
        this.id = ++counter;
        this.name = name;
        this.location = location;
        this.compartment = compartment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Compartment getCompartment() {
        return compartment;
    }

    @Override
    public String toString() {
        String compartmentType;

        if (compartment instanceof HotProductsCompartment) {
            compartmentType = "Hot";
        } else if (compartment instanceof ColdProductsCompartment) {
            compartmentType = "Cold";
        } else {
            compartmentType = "Unknown";
        }

        return "Vending Machine: id: "+ id + " nume: " + name + ", location: " + location + ", compartment type: " + compartmentType;
    }

}
