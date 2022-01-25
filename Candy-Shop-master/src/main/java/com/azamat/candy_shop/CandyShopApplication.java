package com.azamat.candy_shop;

import com.azamat.candy_shop.entities.Product;
import com.azamat.candy_shop.entities.User;
import com.azamat.candy_shop.repos.ProductRepo;
import com.azamat.candy_shop.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CandyShopApplication {
    @Autowired
    PasswordEncoder encoder;

    @Bean
    public CommandLineRunner dataLoader(UserRepo userRepo, ProductRepo productRepo) {
        return args -> {

            if (((List) userRepo.findAll()).isEmpty()) {
                User user = new User();
                user.setUsername("user1");
                user.setPassword(encoder.encode("pass"));
                user.setMoneyBalance(200);
                userRepo.save(user);
            }

            if (((List) productRepo.findAll()).isEmpty()) {
                Product sneakers = new Product("rafaello", 1.1, 10);
                Product twins = new Product("twix", 0.75, 0);
                Product kidcut = new Product("bayan-sulu", 0.8, 5);
                Product bouncy = new Product("bounty", 0.9, 59);
                Product earth = new Product("albeni", 0.95, 7);

                productRepo.save(sneakers);
                productRepo.save(twins);
                productRepo.save(kidcut);
                productRepo.save(bouncy);
                productRepo.save(earth);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CandyShopApplication.class, args);
    }
}
