package co.org.karianov.transportmanagementapi.config;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class GlobalConfig {

	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper createDozerMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("dozer-mapping.xml"));
		return mapper;
	}

}
