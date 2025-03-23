package Models;

public class VendingMachine {
    private String id;
    private String name;
    private String location;
    private Compartment compartment;

    public VendingMachine(String id, String name, String location, Compartment compartment) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.compartment = compartment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public boolean addProduct(Product product) {
        return compartment.addProduct(product);
    }


    public void displayProducts() {
        for (Product p : compartment.getProducts()) {
            System.out.println(p);
        }
    }


}
