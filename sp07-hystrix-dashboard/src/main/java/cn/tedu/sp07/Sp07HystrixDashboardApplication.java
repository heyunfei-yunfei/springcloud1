package cn.tedu.sp07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Sp07HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp07HystrixDashboardApplication.class, args);
    }

}
