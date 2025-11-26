package labs.aui.config;

import labs.aui.*;

import labs.aui.enums.Animal;
import labs.aui.enums.Gender;
import labs.aui.service.PetOwnerService;
import labs.aui.service.PetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PetOwnerService petOwnerService;
    private final PetService petService;

    public DataInitializer(PetOwnerService petOwnerService, PetService petService) {
        this.petOwnerService = petOwnerService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n~~~~~~~~~~~~~~Data Initialization~~~~~~~~~~~~~~");
        createInitialData();
        System.out.println("\n~~~~~~~~~~~~~~Data Initialized Correctly~~~~~~~~~~~~~~");
    }

    //creating data
    private void createInitialData() {
        PetOwner owner1 = PetOwner.builder()
                //.ownerId(UUID.randomUUID())
                .name("Alice")
                .surname("Johnson")
                .phoneNumber(123456789)
                .dateOfBirth(DateGenerator.generateRandomDate("human"))
                .address("123 Pet Street")
                .pets(new ArrayList<>())
                .build();

        petOwnerService.save(owner1);

        Pet pet1 = Pet.builder()
                //.petId(UUID.randomUUID())
                .name("Fido")
                .animal(Animal.DOG)
                .dateOfBirth(DateGenerator.generateRandomDate("pet"))
                .weight(10.5f)
                .breed("Labrador")
                .color("Golden")
                .gender(Gender.MALE)
                .owner(owner1)
                .build();

        petService.save(pet1);

        Pet pet2 = Pet.builder()
                //.petId(UUID.randomUUID())
                .name("Whiskers")
                .animal(Animal.CAT)
                .dateOfBirth(DateGenerator.generateRandomDate("pet"))
                .weight(4.2f)
                .breed("Siamese")
                .color("Cream")
                .gender(Gender.FEMALE)
                .owner(owner1)
                .build();

        petService.save(pet2);

        Pet pet4 = Pet.builder()
                //.petId(UUID.randomUUID())
                .name("Puma")
                .animal(Animal.CAT)
                .dateOfBirth(DateGenerator.generateRandomDate("pet"))
                .weight(2.2f)
                .breed("Mixed")
                .color("Black")
                .gender(Gender.FEMALE)
                .owner(owner1)
                .build();

        petService.save(pet4);

        PetOwner owner2 = PetOwner.builder()
                //.ownerId(UUID.randomUUID())
                .name("Bob")
                .surname("Smith")
                .phoneNumber(987654321)
                .dateOfBirth(DateGenerator.generateRandomDate("human"))
                .address("12 Bird Street")
                .pets(new ArrayList<>())
                .build();

        petOwnerService.save(owner2);

        Pet pet3 = Pet.builder()
                //.petId(UUID.randomUUID())
                .name("Squawk")
                .animal(Animal.BIRD)
                .dateOfBirth(DateGenerator.generateRandomDate("pet"))
                .weight(0.5f)
                .breed("Parrot")
                .color("Green")
                .gender(Gender.MALE)
                .owner(owner2)
                .build();

        petService.save(pet3);

        Pet pet5 = Pet.builder()
                //.petId(UUID.randomUUID())
                .name("Garfield")
                .animal(Animal.CAT)
                .dateOfBirth(DateGenerator.generateRandomDate("pet"))
                .weight(5.5f)
                .breed("Mixed")
                .color("Orange")
                .gender(Gender.MALE)
                .owner(owner2)
                .build();

        petService.save(pet5);
    }
}










