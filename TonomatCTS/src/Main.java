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
        VendingMachine vm2=new VendingMachine("Tonomat 2 ","Acasa",new ColdProductsCompartment(10));
        VendingMachine vm3=new VendingMachine("Tonomat 3 ","Acasa",new HotProductsCompartment(10));
        VendingMachine vm4=new VendingMachine("Tonomat 4 ","Acasa",new ColdProductsCompartment(10));

        Product p1 = new Product("Lasagna", 52.22, ProductType.WARM);
        Product p2 = new Product("Pizza", 42.28, ProductType.WARM);
        Product p3 = new Product("Soup", 12.55, ProductType.WARM);

        Product p4 = new Product("Fanta", 5.85, ProductType.COLD);
        Product p5 = new Product("Pepsi", 7.96, ProductType.COLD);
        Product p6 = new Product("Lays", 12.55, ProductType.COLD);

        Product p7 = new Product("Covrigi", 10.28, ProductType.IDK);
        Product p8 = new Product("Ciocolata", 6.50, ProductType.IDK);
        Product p9 = new Product("Baton", 3.75, ProductType.IDK);

        vm1.getCompartment().addProduct(p1);
        vm1.getCompartment().addProduct(p2);
        vm1.getCompartment().addProduct(p3);
        vm1.getCompartment().addProduct(p7);

        vm2.getCompartment().addProduct(p4);
        vm2.getCompartment().addProduct(p5);
        vm2.getCompartment().addProduct(p6);
        vm2.getCompartment().addProduct(p8);

        vm3.getCompartment().addProduct(new Product("Omleta", 18.90, ProductType.WARM));
        vm3.getCompartment().addProduct(p9);

        vendingMachines.add(vm1);
        vendingMachines.add(vm2);
        vendingMachines.add(vm3);
        vendingMachines.add(vm4);

        while (true) {
            System.out.println("\n=== Meniu ===");
            System.out.println("1. Create a vending machine");
            System.out.println("2. Select a vending machine");
            System.out.println("0. Close the application");
            System.out.print("Choose (0,1,2): ");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        createVendingMachine();
                        break;
                    case 2:
                        selectVendingMachine();
                        break;
                    case 0:
                        System.out.println("Closing the application...");
                        return;
                    default:
                        System.out.println("Invalid option! Please choose again.");
                }
            } else {
                String invalidInput = scanner.next();
                System.out.println("Invalid input! Please choose again.");
            }
        }
    }

    private static void createVendingMachine(){
        System.out.print("Insert vending machine name: ");
        String name = scanner.nextLine();

        System.out.print("Insert the location of the vending machine: ");
        String location = scanner.nextLine();

        System.out.println("Choose the maximum capacity of the compartment.");
        System.out.print("Insert capacity: ");
        int compartmentCapacity=scanner.nextInt();
        scanner.nextLine();

        Compartment compartment = null;

        while (true) {
            System.out.println("Choose compartment type:");
            System.out.println("1. Hot Products Compartment");
            System.out.println("2. Cold Products Compartment");
            System.out.print("Choose (1/2): ");

            if (scanner.hasNextInt()) {
                int compartmentOption = scanner.nextInt();
                scanner.nextLine();
                if (compartmentOption == 1) {
                    compartment = new HotProductsCompartment(compartmentCapacity);
                    break;
                } else if (compartmentOption == 2) {
                    compartment = new ColdProductsCompartment(compartmentCapacity);
                    break;
                } else {
                    System.out.println("Invalid input! Please choose 1 or 2.");
                }
            } else {
                scanner.nextLine();
                System.out.println("Invalid input! Please choose 1 or 2.");
            }
        }


        VendingMachine vendingMachine = new VendingMachine(name, location, compartment);
        vendingMachines.add(vendingMachine);
        System.out.println("The vending machine is created!");
    }

    private static void showAllVendingMachines(){
        if (vendingMachines.isEmpty()) {
            System.out.println("There are no vending machines!");
        }
        else {
            System.out.println("\n=== All vending machines ===");
            for (VendingMachine vendingMachine : vendingMachines) {
                System.out.println(vendingMachine.toString());
            }
        }
    }

    private static void selectVendingMachine() {
        showAllVendingMachines();

        if (!vendingMachines.isEmpty()) {
            while (true) {
                System.out.print("Choose vending machine (write id): ");

                if (scanner.hasNextInt()) {
                    int vendingMachineId = scanner.nextInt();
                    scanner.nextLine();

                    VendingMachine vendingMachine = vendingMachines.stream()
                            .filter(vm -> vm.getId() == vendingMachineId)
                            .findFirst()
                            .orElse(null);

                    if (vendingMachine != null) {
                        vendingMachineMenu(vendingMachine);
                        break;
                    } else {
                        System.out.println("There is no vending machine with the id " + vendingMachineId);
                    }
                } else {
                    String invalidInput = scanner.next();
                    System.out.println("There is no vending machine with the id " + invalidInput);
                }
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
            System.out.println("5. Move a product to a different vending machine");
            System.out.println("6. Buy a product");
            System.out.println("0. Exit");
            System.out.print("Choose (0-6): ");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        createProduct(vendingMachine);
                        break;
                    case 2:
                        showAllProducts(vendingMachine);
                        break;
                    case 3:
                        showMostExpensiveProduct(vendingMachine);
                        break;
                    case 4:
                        deleteProduct(vendingMachine);
                        break;
                    case 5:
                        moveProduct(vendingMachine);
                        break;
                    case 6:
                        buyProduct(vendingMachine);
                        break;
                    case 0:
                        System.out.println("Closing the application...");
                        return;
                    default:
                        System.out.println("Invalid option! Please choose again.");
                }

            } else {
                scanner.nextLine();
                System.out.println("Invalid input! Please choose again.");
            }
        }
    }

    private static void createProduct(VendingMachine vendingMachine) {
        System.out.print("Insert product name: ");
        String name = scanner.nextLine();

        ProductType type = null;
        Compartment comp = vendingMachine.getCompartment();
        boolean isHotCompartment = comp instanceof HotProductsCompartment;

        while (true) {
            System.out.println("Choose product type:");
            System.out.println("1. WARM");
            System.out.println("2. COLD");
            System.out.println("3. IDK");
            System.out.print("Choose type (1,2,3): ");

            if (scanner.hasNextInt()) {
                int typeOption = scanner.nextInt();
                scanner.nextLine();

                switch (typeOption) {
                    case 1:
                        if (!isHotCompartment) {
                            System.out.println("This vending machine only accepts COLD or IDK products.");
                            continue;
                        }
                        type = ProductType.WARM;
                        break;

                    case 2:
                        if (isHotCompartment) {
                            System.out.println("This vending machine only accepts WARM or IDK products.");
                            continue;
                        }
                        type = ProductType.COLD;
                        break;

                    case 3:
                        type = ProductType.IDK;
                        break;

                    default:
                        System.out.println("Invalid type. Please choose 1, 2, or 3.");
                        continue;
                }
                break;
            } else {
                scanner.nextLine();
                System.out.println("Invalid input. Please choose 1, 2, or 3.");
            }
        }

        double price = 0;
        while (true) {
            System.out.print("Insert product price: ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                scanner.nextLine();
                System.out.println("Invalid price! Please insert a numeric value like 2.5");
            }
        }

        Product product = new Product(name, price, type);
        comp.addProduct(product);
        System.out.println("Product added successfully!");
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

    private static void deleteProduct(VendingMachine vendingMachine) {
        showAllProducts(vendingMachine);

        if (vendingMachine.getCompartment().getProducts().isEmpty()) {
            System.out.println("There are no products to delete.");
            return;
        }

        while (true) {
            System.out.print("Choose a product by ID: ");

            if (scanner.hasNextInt()) {
                int productId = scanner.nextInt();
                scanner.nextLine();

                boolean wasDeleted = vendingMachine.getCompartment().getProducts()
                        .removeIf(product -> product.getId() == productId);

                if (wasDeleted) {
                    System.out.println("Product deleted successfully.");
                    break;
                } else {
                    System.out.println("No product found with ID " + productId + ". Try again.");
                }
            } else {
                String invalidInput = scanner.next();
                System.out.println("Invalid input: " + invalidInput + ". Please enter a numeric ID.");
            }
        }
    }



    private static void buyProduct(VendingMachine vendingMachine) {
        if (vendingMachine.getCompartment().getProducts().isEmpty()) {
            System.out.println("There are no products to buy.");
            return;
        }

        while (true) {
            System.out.println("\nChoose a payment method:");
            System.out.println("1. VISA");
            System.out.println("2. MASTERCARD");
            System.out.print("Enter option (1 or 2): ");

            if (scanner.hasNextInt()) {
                int paymentOption = scanner.nextInt();
                scanner.nextLine();

                if (paymentOption == 1 || paymentOption == 2) {
                    showAllProducts(vendingMachine);

                    while (true) {
                        System.out.print("Choose a product by ID: ");
                        if (scanner.hasNextInt()) {
                            int productId = scanner.nextInt();
                            scanner.nextLine();

                            Product product = vendingMachine.getCompartment().extractProduct(productId);
                            if (product != null) {
                                SharedBankAccount sharedBankAccount = SharedBankAccount.getInstance();
                                sharedBankAccount.processPayment(product.getPrice());

                                System.out.printf("You bought %s for %.2f RON. Thank you!%n", product.getName(), product.getPrice());
                                return;
                            } else {
                                System.out.println("No product found with ID " + productId + ". Try again.");
                            }
                        } else {
                            String invalid = scanner.next();
                            System.out.println("Invalid input: " + invalid + ". Please enter a numeric ID.");
                        }
                    }

                } else {
                    System.out.println("Invalid payment method. Please choose 1 for VISA or 2 for MASTERCARD.");
                }
            } else {
                String invalid = scanner.next();
                System.out.println("Invalid input: " + invalid + ". Please enter 1 or 2.");
            }
        }
    }


    private static void moveProduct(VendingMachine vendingMachine) {

        if (vendingMachine.getCompartment().getProducts().isEmpty()) {
            System.out.println("There are no products to move.");
            return;
        }

        Product chosenProduct = null;

        while (chosenProduct == null) {
            showAllProducts(vendingMachine);
            System.out.print("Choose a product by ID: ");
            if (scanner.hasNextInt()) {
                int productId = scanner.nextInt();
                scanner.nextLine();
                chosenProduct = vendingMachine.getCompartment().extractProduct(productId);
                if (chosenProduct == null) {
                    System.out.println("No product found with ID " + productId);
                }
            } else {
                System.out.println("Please enter a valid numeric product ID.");
                scanner.next();
            }
        }

        VendingMachine chosenVendingMachine = null;
        while (chosenVendingMachine == null) {
            showAllVendingMachines();
            System.out.print("Choose the destination vending machine (write ID): ");
            if (scanner.hasNextInt()) {
                int vendingMachineId = scanner.nextInt();
                scanner.nextLine();
                chosenVendingMachine = vendingMachines.stream()
                        .filter(vm -> vm.getId() == vendingMachineId)
                        .findFirst()
                        .orElse(null);

                if (chosenVendingMachine == null) {
                    System.out.println("No vending machine found with ID " + vendingMachineId);
                }
            } else {
                System.out.println("Please enter a valid numeric vending machine ID.");
                scanner.next();
            }
        }

        Compartment destCompartment = chosenVendingMachine.getCompartment();
        boolean compatible = false;

        if (destCompartment instanceof HotProductsCompartment &&
                (chosenProduct.getType() == ProductType.WARM || chosenProduct.getType() == ProductType.IDK)) {
            compatible = true;
        } else if (destCompartment instanceof ColdProductsCompartment &&
                (chosenProduct.getType() == ProductType.COLD || chosenProduct.getType() == ProductType.IDK)) {
            compatible = true;
        }

        if (compatible) {
            destCompartment.addProduct(chosenProduct);
            System.out.println("Product moved successfully!");
        } else {
            System.out.println("Incompatible product type. Product was not moved.");
            vendingMachine.getCompartment().addProduct(chosenProduct);
        }
    }

}


