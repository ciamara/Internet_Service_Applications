package labs.aui;

import labs.aui.config.DateGenerator;
import labs.aui.enums.Animal;
import labs.aui.enums.Gender;
import labs.aui.service.PetOwnerService;
import labs.aui.service.PetService;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//@Component
public class PetsCLI implements CommandLineRunner {

    private final PetService petService;
    private final PetOwnerService petOwnerService;

    public PetsCLI(PetService petService, PetOwnerService petOwnerService) {
        this.petService = petService;
        this.petOwnerService = petOwnerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nAvailable commands:");
            System.out.println("1 - List all owners");
            System.out.println("2 - List all pets");
            System.out.println("3 - Add new pet");
            System.out.println("4 - Delete pet");
            System.out.println("0 - Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    List<PetOwner> owners = petOwnerService.findAll();
                    if (owners.isEmpty()) {
                        System.out.println("no owners found");
                        break;
                    }
                    for (PetOwner o : owners) {
                        System.out.println(o.getOwnerId() + " - " + o.getName() + " " + o.getSurname() +
                                " - " + o.getPhoneNumber() +
                                " - " + o.getDateOfBirth() +
                                " - " + o.getAddress());
                        if (o.getPets() != null && !o.getPets().isEmpty()) {
                            for (Pet p : o.getPets()) {
                                System.out.println("\t" + p.getPetId() + " - " + p.getName() +
                                        " - " + p.getAnimal() +
                                        " - " + p.getBreed() +
                                        " - " + p.getWeight() +
                                        " - " + p.getGender() +
                                        " - " + p.getDateOfBirth());
                            }
                        } else {
                            System.out.println("\towner has no pets");
                        }
                    }
                    break;

                case "2":
                    List<Pet> pets = petService.findAll();
                    if (pets.isEmpty()) {
                        System.out.println("no pets found");
                        break;
                    }
                    for (Pet p : pets) {
                        System.out.println(p.getPetId() + " - " + p.getName() +
                                " - " + p.getAnimal() +
                                " - " + p.getBreed() +
                                " - " + p.getWeight() +
                                " - " + p.getGender() +
                                " - " + p.getDateOfBirth() +
                                " - " + (p.getOwner() != null ? p.getOwner().getName() + " " + p.getOwner().getSurname() : "no owner"));
                    }
                    break;

                case "3":
                    System.out.print("Enter pet name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter animal type (CAT/DOG/BIRD/HAMSTER/SNAKE/HORSE): ");
                    Animal animal = Animal.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Enter date of birth(yyyy-MM-dd): ");
                    String dobInput = scanner.nextLine();
                    Date dateOfBirth;

                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        formatter.setLenient(false);
                        dateOfBirth = formatter.parse(dobInput);
                    } catch (ParseException e) {
                        System.out.println("invalid format, using random date");
                        dateOfBirth = DateGenerator.generateRandomDate("pet");
                    }

                    System.out.print("Enter pet weight: ");
                    Float weight = Float.valueOf(scanner.nextLine());
                    if(weight<=0){
                        System.out.println("weight cannot be less or equal to 0, inputting null");
                        weight = null;
                    }

                    System.out.print("Enter pet breed: ");
                    String breed = scanner.nextLine();

                    System.out.print("Enter pet color: ");
                    String color = scanner.nextLine();

                    System.out.print("Enter pet gender: ");
                    Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Enter owner ID: ");
                    PetOwner owner;
                    try{
                        UUID ownerId = UUID.fromString(scanner.nextLine());
                        owner = petOwnerService.findById(ownerId);
                        if (owner == null) {
                            System.out.println("owner with chosen id doesn't exist");
                            break;
                        }
                    }catch(IllegalArgumentException exception){
                        System.out.println("UUID is an invalid format");
                        break;
                    }


                    Pet pet = Pet.builder()
                            .petId(UUID.randomUUID())
                            .name(name)
                            .animal(animal)
                            .dateOfBirth(dateOfBirth)
                            .weight(weight)
                            .breed(breed)
                            .color(color)
                            .gender(gender)
                            .owner(owner)
                            .build();
                    petService.save(pet);
                    System.out.println("pet added successfully");
                    break;

                case "4":
                    System.out.print("Enter pet ID to delete: ");
                    Pet petToDelete;
                    try{
                        UUID petId = UUID.fromString(scanner.nextLine());
                        petToDelete = petService.findById(petId);
                        if (petToDelete != null) {
                            petService.delete(petId);
                            System.out.println("pet deleted successfully");
                        }else{
                            System.out.println("pet with chosen id doesn't exist");
                        }
                    }catch(IllegalArgumentException exception){
                        System.out.println("UUID is an invalid format");
                        break;
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("exiting. . . . . .");
                    break;

                default:
                    System.out.println("unknown command");
            }
        }

        scanner.close();
    }
}
