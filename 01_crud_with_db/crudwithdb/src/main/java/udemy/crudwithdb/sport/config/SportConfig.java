package udemy.crudwithdb.sport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import udemy.crudwithdb.sport.entity.coach.BaseballCoach;
import udemy.crudwithdb.sport.entity.coach.Coach;

@Configuration
public class SportConfig {

	@Bean("inning")
	public Coach swimCoach() {
		
		return new BaseballCoach();
	}
}
