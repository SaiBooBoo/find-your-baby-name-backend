package com.sszm.findyourbabynamebackend.seeder;

import com.sszm.findyourbabynamebackend.models.BabyName;
import com.sszm.findyourbabynamebackend.repositories.BabyNameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final BabyNameRepository repository;

    public DatabaseSeeder(BabyNameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            System.out.println("Initializing database with sample baby names...");
            BabyName name1 = createBabyName("Liam", "Boy", "Strong-willed warrior and protector");
            BabyName name2 = createBabyName("Olivia", "Girl", "Olive tree, symbolizing peace");
            BabyName name3 = createBabyName("Noah", "Boy", "Rest, comfort, and peace");
            BabyName name4 = createBabyName("Emma", "Girl", "Universal, whole, and complete");
            BabyName name5 = createBabyName("Alex", "Unisex", "Defender of mankind");
            BabyName name6 = createBabyName("Sophia", "Girl", "Wisdom and deep understanding");
            BabyName name7 = createBabyName("Ethan", "Boy", "Firm, strong, and long-lived");

            repository.saveAll(List.of(name1, name2, name3, name4, name5, name6, name7));

            System.out.println("Database successfully populated with " + repository.count() + " names!");
        } else {
            System.out.println("Database already contains data. Skipping initial seeding process.");
        }
    }

    private BabyName createBabyName(String name, String gender, String meaning) {
        BabyName entity = new BabyName();
        entity.setName(name);
        entity.setGender(gender);
        entity.setMeaning(meaning);
        return entity;
    }
}
