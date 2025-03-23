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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCompartment(Compartment compartment) {
        this.compartment = compartment;
    }

    public void displayProducts() {
        for (Product p : compartment.getProducts()) {
            System.out.println(p);
        }
    }

    public Compartment getCompartment() {
        return compartment;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Location: " + location;
    }
}
