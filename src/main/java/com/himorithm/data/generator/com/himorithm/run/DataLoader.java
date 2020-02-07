package com.himorithm.data.generator.com.himorithm.run;

import com.himorithm.data.generator.com.himorithm.data.Customer;
import com.himorithm.data.generator.com.himorithm.data.Seller;
import com.himorithm.data.generator.com.himorithm.random.RandomNameGenerator;
import com.himorithm.data.generator.com.himorithm.repository.CustomerRepository;
import com.himorithm.data.generator.com.himorithm.repository.SellerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Slf4j
@Component
public class DataLoader implements ApplicationRunner {

    final int TOTAL_SIZE = 1000000;
    final int BATCH_SIZE = 20000;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final RandomNameGenerator randomNameGenerator;

    @Autowired
    public DataLoader(CustomerRepository customerRepository, SellerRepository sellerRepository, RandomNameGenerator randomNameGenerator) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.randomNameGenerator = randomNameGenerator;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int start = 1, end = start + BATCH_SIZE; start < TOTAL_SIZE; start += (BATCH_SIZE), end = start + BATCH_SIZE) {
            log.info("Persist Batch Started Range From: " + start + " To " + end);
            List<Customer> customers = generateCustomers(start, end).collect(Collectors.toList());
            List<Seller> sellers = generatePerson(start, end).collect(Collectors.toList());
            customerRepository.saveAll(customers);
            sellerRepository.saveAll(sellers);
            log.info("Persist Batch Completed Range From: " + start + " To " + end);
        }
    }

    Stream<Seller> generatePerson(int start, int end) {
        return IntStream.range(start, end).mapToObj(randomNameGenerator::getSeller);
    }

    Stream<Customer> generateCustomers(int start, int end) {
        return LongStream.range(start, end).mapToObj(randomNameGenerator::getCustomer);
    }
}
