package com.zendemo.zenclinic.config;

import com.zendemo.zenclinic.model.*;
import com.zendemo.zenclinic.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    public CommandLineRunner initData(VetRepository vetRepository,
                                     SpecialtyRepository specialtyRepository,
                                     PetTypeRepository petTypeRepository,
                                     OwnerRepository ownerRepository,
                                     PetRepository petRepository,
                                     VisitRepository visitRepository) {
        return args -> {
            // Only initialize data if the database is empty
            if (vetRepository.count() > 0) {
                System.out.println("Database already contains data, skipping initialization");
                return;
            }

            try {
                System.out.println("Initializing database with sample data...");

                // Create specialties
                Specialty radiology = new Specialty();
                radiology.setName("Radiology");
                specialtyRepository.save(radiology);

                Specialty surgery = new Specialty();
                surgery.setName("Surgery");
                specialtyRepository.save(surgery);

                Specialty dentistry = new Specialty();
                dentistry.setName("Dentistry");
                specialtyRepository.save(dentistry);

                Specialty cardiology = new Specialty();
                cardiology.setName("Cardiology");
                specialtyRepository.save(cardiology);

                Specialty dermatology = new Specialty();
                dermatology.setName("Dermatology");
                specialtyRepository.save(dermatology);

                // Create vets
                Vet jamesCarter = new Vet();
                jamesCarter.setFirstName("James");
                jamesCarter.setLastName("Carter");
                jamesCarter.setSpecialties(new HashSet<>(Arrays.asList(cardiology)));
                vetRepository.save(jamesCarter);

                Vet helenLeary = new Vet();
                helenLeary.setFirstName("Helen");
                helenLeary.setLastName("Leary");
                helenLeary.setSpecialties(new HashSet<>(Arrays.asList(radiology, dermatology)));
                vetRepository.save(helenLeary);

                Vet lindaDouglas = new Vet();
                lindaDouglas.setFirstName("Linda");
                lindaDouglas.setLastName("Douglas");
                lindaDouglas.setSpecialties(new HashSet<>(Arrays.asList(surgery, dentistry)));
                vetRepository.save(lindaDouglas);

                Vet rafaelOrtega = new Vet();
                rafaelOrtega.setFirstName("Rafael");
                rafaelOrtega.setLastName("Ortega");
                rafaelOrtega.setSpecialties(new HashSet<>(Arrays.asList(surgery)));
                vetRepository.save(rafaelOrtega);

                Vet henryStevens = new Vet();
                henryStevens.setFirstName("Henry");
                henryStevens.setLastName("Stevens");
                henryStevens.setSpecialties(new HashSet<>(Arrays.asList(radiology)));
                vetRepository.save(henryStevens);

                // Create pet types
                PetType dog = new PetType();
                dog.setName("Dog");
                petTypeRepository.save(dog);

                PetType cat = new PetType();
                cat.setName("Cat");
                petTypeRepository.save(cat);

                PetType bird = new PetType();
                bird.setName("Bird");
                petTypeRepository.save(bird);

                PetType rabbit = new PetType();
                rabbit.setName("Rabbit");
                petTypeRepository.save(rabbit);

                PetType fish = new PetType();
                fish.setName("Fish");
                petTypeRepository.save(fish);

                PetType reptile = new PetType();
                reptile.setName("Reptile");
                petTypeRepository.save(reptile);

                // Create owners
                Owner georgeFranklin = new Owner();
                georgeFranklin.setFirstName("George");
                georgeFranklin.setLastName("Franklin");
                georgeFranklin.setAddress("110 W. Liberty St.");
                georgeFranklin.setCity("Madison");
                georgeFranklin.setTelephone("6085551023");
                georgeFranklin.setEmail("george.franklin@email.com");
                ownerRepository.save(georgeFranklin);

                Owner bettyDavis = new Owner();
                bettyDavis.setFirstName("Betty");
                bettyDavis.setLastName("Davis");
                bettyDavis.setAddress("638 Cardinal Ave.");
                bettyDavis.setCity("Sun Prairie");
                bettyDavis.setTelephone("6085551749");
                bettyDavis.setEmail("betty.davis@email.com");
                ownerRepository.save(bettyDavis);

                Owner eduardoRodriquez = new Owner();
                eduardoRodriquez.setFirstName("Eduardo");
                eduardoRodriquez.setLastName("Rodriquez");
                eduardoRodriquez.setAddress("2693 Commerce St.");
                eduardoRodriquez.setCity("McFarland");
                eduardoRodriquez.setTelephone("6085558763");
                eduardoRodriquez.setEmail("eduardo.rodriquez@email.com");
                ownerRepository.save(eduardoRodriquez);

                // Create pets
                Pet leo = new Pet();
                leo.setName("Leo");
                leo.setBirthDate(LocalDate.of(2020, 9, 7));
                leo.setBreed("Golden Retriever");
                leo.setType(dog);
                leo.setOwner(georgeFranklin);
                petRepository.save(leo);

                Pet basil = new Pet();
                basil.setName("Basil");
                basil.setBirthDate(LocalDate.of(2021, 8, 6));
                basil.setBreed("Tabby");
                basil.setType(cat);
                basil.setOwner(bettyDavis);
                petRepository.save(basil);

                Pet rosy = new Pet();
                rosy.setName("Rosy");
                rosy.setBirthDate(LocalDate.of(2021, 4, 17));
                rosy.setBreed("Cockatiel");
                rosy.setType(bird);
                rosy.setOwner(eduardoRodriquez);
                petRepository.save(rosy);

                Pet jewel = new Pet();
                jewel.setName("Jewel");
                jewel.setBirthDate(LocalDate.of(2022, 3, 7));
                jewel.setBreed("Dutch");
                jewel.setType(rabbit);
                jewel.setOwner(eduardoRodriquez);
                petRepository.save(jewel);

                // Create visits
                Visit leoCheckup = new Visit();
                leoCheckup.setPet(leo);
                leoCheckup.setDate(LocalDate.of(2023, 1, 5));
                leoCheckup.setDescription("Annual checkup");
                leoCheckup.setDiagnosis("Healthy");
                leoCheckup.setTreatment("No treatment needed");
                visitRepository.save(leoCheckup);

                Visit basilLimping = new Visit();
                basilLimping.setPet(basil);
                basilLimping.setDate(LocalDate.of(2023, 2, 15));
                basilLimping.setDescription("Limping");
                basilLimping.setDiagnosis("Sprained leg");
                basilLimping.setTreatment("Rest and anti-inflammatory medication");
                visitRepository.save(basilLimping);

                Visit rosyNotEating = new Visit();
                rosyNotEating.setPet(rosy);
                rosyNotEating.setDate(LocalDate.of(2023, 3, 10));
                rosyNotEating.setDescription("Not eating");
                rosyNotEating.setDiagnosis("Digestive issue");
                rosyNotEating.setTreatment("Special diet and probiotics");
                visitRepository.save(rosyNotEating);

                Visit jewelDental = new Visit();
                jewelDental.setPet(jewel);
                jewelDental.setDate(LocalDate.of(2023, 4, 22));
                jewelDental.setDescription("Dental check");
                jewelDental.setDiagnosis("Overgrown teeth");
                jewelDental.setTreatment("Teeth trimming");
                visitRepository.save(jewelDental);

                System.out.println("Sample data has been loaded successfully!");
            } catch (Exception e) {
                System.err.println("Error initializing data: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}