import Enums.ProductType;
import Models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<VendingMachine> vendingMachines = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        VendingMachine vm1=new VendingMachine("Tonomat 1 ","Acasa",new HotProductsCompartment(10));
        VendingMachine vm2=new VendingMachine("Tonomat 2 ","Acasa",new HotProductsCompartment(10));
        VendingMachine vm3=new VendingMachine("Tonomat 3 ","Acasa",new HotProductsCompartment(10));

        Product p1=new Product("Lays",12.55,ProductType.COLD);
        Product p2=new Product("Lasagna",52.22,ProductType.WARM);
        Product p3=new Product("Soup",12.55,ProductType.WARM);

        vm2.getCompartment().addProduct(p1);
        vm1.getCompartment().addProduct(p2);
        vm1.getCompartment().addProduct(p3);

        vendingMachines.add(vm1);
        vendingMachines.add(vm2);
        vendingMachines.add(vm3);



        while (true) {
            System.out.println("\n=== Meniu ===");
            System.out.println("1. Create a vending machine");
            System.out.println("2. Select a vending machine");
            System.out.println("0. Close the application");
            System.out.print("Choose: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: createVendingMachine(); break;
                case 2: selectVendingMachine();break;
                case 0: System.out.println("Closing the application..."); return;
                default: System.out.println("Invalid option! Please choose again.");
            }
        }
    }

    private static void createVendingMachine(){
        System.out.print("Insert vending machine name: ");
        String name = scanner.nextLine();

        System.out.print("Insert the location of the vending machine: ");
        String location = scanner.nextLine();

        System.out.println("Choose the maximum capacity of the compartment: ");
        System.out.print("Insert capacity: ");
        int compartmentCapacity=scanner.nextInt();

        System.out.println("Choose compartment type:");
        System.out.println("1. Hot Products Compartment");
        System.out.println("2. Cold Products Compartment");
        System.out.print("Choose: ");

        int compartmentOption = scanner.nextInt();

        Compartment compartment = null;

        switch (compartmentOption) {
            case 1: compartment = new HotProductsCompartment(compartmentCapacity);break;
            case 2: compartment = new ColdProductsCompartment(compartmentCapacity);break;
            default:
                System.out.println("Opțiune invalidă pentru compartiment!");
                return;
        }

        VendingMachine vendingMachine = new VendingMachine(name, location, compartment);
        vendingMachines.add(vendingMachine);
        System.out.println("Tonomatul a fost creat cu succes!");
    }

    private static void selectVendingMachine(){

        if (vendingMachines.isEmpty()) {
            System.out.println("There are no vending machines");
        } else {
            System.out.println("\n=== All vending machines ===");
            for (VendingMachine vendingMachine : vendingMachines) {
                System.out.println(vendingMachine.toString());
            }

            System.out.print("Choose vending machine: ");
            int vendingMachineId = scanner.nextInt();

            VendingMachine vendingMachine=vendingMachines.stream()
                    .filter(vm -> vm.getId() == vendingMachineId)
                    .findFirst()
                    .orElse(null);

            if (vendingMachine!=null){
                vendingMachineMenu(vendingMachine);
            }
            else {
                System.out.println("There is no vending machine with the id "+vendingMachineId);
            }
        }
    }

    private static void vendingMachineMenu(VendingMachine vendingMachine){
        while (true) {
            System.out.println("\n=== "+vendingMachine.getName()+" ===");
            System.out.println("1. Add product");
            System.out.println("2. Show all products");
            System.out.println("3. Show most expensive product");
            System.out.println("4. Delete a product");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: createProduct(vendingMachine); break;
                case 2: showAllProducts(vendingMachine);break;
                case 3: showMostExpensiveProduct(vendingMachine);break;
                case 4: deleteProduct(vendingMachine);break;
                case 0: System.out.println("Closing the application..."); return;
                default: System.out.println("Invalid option! Please choose again.");
            }
        }
    }

    private static void createProduct(VendingMachine vendingMachine) {

        System.out.print("Introduceți numele produsului: ");
        String name = scanner.nextLine();

        System.out.println("Alegeți tipul produsului:");
        System.out.println("1. WARM");
        System.out.println("2. COLD");
        System.out.println("3. IDK (altele)");

        System.out.print("Alegeți tipul: ");
        int typeOption = scanner.nextInt();
        scanner.nextLine();

        ProductType type = null;
        switch (typeOption) {
            case 1:
                type = ProductType.WARM;
                break;
            case 2:
                type = ProductType.COLD;
                break;
            case 3:
                type = ProductType.IDK;
                break;
            default:
                System.out.println("Opțiune invalidă pentru tipul produsului.");
                return;
        }

        System.out.print("Introduceți prețul produsului: ");
        double price = scanner.nextDouble();

        Product product = new Product(name, price, type);

        vendingMachine.getCompartment().addProduct(product);
    }

    private static void showAllProducts(VendingMachine vendingMachine){
        List<Product> products=vendingMachine.getCompartment().getProducts();
        System.out.println("\n=== All products from vending machine "+vendingMachine.getName()+" ===");

        if (products.isEmpty()){
            System.out.println("There are no available products");
        }
        else {
            for (Product product : products) {
                System.out.println(product.toString());
            }
        }

    }

    private static void showMostExpensiveProduct(VendingMachine vendingMachine){
        List<Product> products=vendingMachine.getCompartment().getProducts();
        System.out.println("\n=== MOST EXPENSIVE PRODUCT from "+vendingMachine.getName()+" ===");

        if (products.isEmpty()){
            System.out.println("There are no available products");
        }
        else {
            Product mostExpensive = products.stream()
                    .max(Comparator.comparingDouble(Product::getPrice))
                    .orElse(null);
            System.out.println(mostExpensive.toString());
        }
    }

    private static void deleteProduct(VendingMachine vendingMachine){
        showAllProducts(vendingMachine);

        System.out.print("Choose a product by ID: ");
        int productId = scanner.nextInt();

        vendingMachine.getCompartment().removeProduct(productId);
    }
}