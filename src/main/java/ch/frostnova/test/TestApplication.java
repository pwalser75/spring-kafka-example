package ch.frostnova.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {

    @Value("${bootstrapAddress}")
    private String bootstrapAddress;

    @Autowired
    private MessagingService messagingService;

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(TestApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("BootstrapAddress: " + bootstrapAddress);
        messagingService.send("Hello @ " + LocalDateTime.now());

        Thread.sleep(1000);

        System.out.println("Messages:");
        messagingService.getMessages().forEach(m -> System.out.println(">> " + m));

        System.exit(0);
    }
}
