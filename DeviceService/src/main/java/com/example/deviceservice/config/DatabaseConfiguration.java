package com.example.deviceservice.config;

import com.example.deviceservice.Producers.DeviceProducer;
import com.example.deviceservice.entities.Device;
import com.example.deviceservice.entities.DeviceStatus;
import com.example.deviceservice.entities.User;
import com.example.deviceservice.repositories.DeviceRepository;
import com.example.deviceservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class DatabaseConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(DeviceRepository deviceRepository, UserRepository userRepository, DeviceProducer deviceProducer) {
        if (deviceRepository.count() != 0) {
            return args -> {
            };
        }
        return args -> {
            //Users config
            User defaultAdmin = User.builder().
                    id(1L).
                    username("admin").
                    email("admin@gmail.com").
                    build();
            User defaultUser = User.builder()
                    .id(2L)
                    .username("user")
                    .email("user@user.com")
                    .build();
            userRepository.save(defaultUser);
            userRepository.save(defaultAdmin);
            //Devices config
            Device defaultUserDevice1 = Device.builder()
//                    .serialNumber(UUID.fromString("55558888-b5ca-42fb-b5b3-d926c943113e"))
                    .name("device1")
                    .description("device to measure consumption")
                    .status(DeviceStatus.UNKNOWN)
                    .address("address1")
                    .maxConsumption(100f)
                    .userId(2L)
                    .build();
            Device defaultUserDevice2 = Device.builder()
//                    .serialNumber(UUID.fromString("bc5351a4-d5ea-4ba2-8881-d1a1b022838e"))
                    .name("device2")
                    .description("device to measure consumption")
                    .status(DeviceStatus.ACTIVE)
                    .address("address2")
                    .maxConsumption(200f)
                    .userId(2L)
                    .build();
            System.out.println(defaultUserDevice1);

            deviceRepository.save(defaultUserDevice1);
            deviceRepository.save(defaultUserDevice2);
            //print config
            System.out.println("This is default admin");
            System.out.println(defaultAdmin);
            System.out.println("This is default user");
            System.out.println(defaultUser);
            System.out.println("This is default device 1");
            System.out.println(defaultUserDevice1);
            System.out.println("This is default device 2");
            System.out.println(defaultUserDevice2);
            deviceProducer.send(defaultUserDevice1, "insert");
            deviceProducer.send(defaultUserDevice2, "insert");
        };
    }
}
