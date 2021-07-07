package zed.eureka.cli.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("zed.eureka.cli.mapper")
public class MyBatisConfig {
}
