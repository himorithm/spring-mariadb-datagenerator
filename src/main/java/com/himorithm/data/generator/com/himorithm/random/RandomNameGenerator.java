package com.himorithm.data.generator.com.himorithm.random;

import com.github.javafaker.Faker;
import com.himorithm.data.generator.com.himorithm.data.Customer;
import com.himorithm.data.generator.com.himorithm.data.Seller;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Locale;

@Component
public class RandomNameGenerator {
    private final Faker faker;

    public RandomNameGenerator() {
        this.faker = new Faker(Locale.ENGLISH, new SecureRandom());
    }

    public Customer getCustomer(Long id) {
        return new Customer(id, faker.name().firstName(), faker.name().lastName());
    }

    public Seller getSeller(Integer id) {
        return new Seller(id, faker.name().firstName(), faker.name().lastName());
    }
}
