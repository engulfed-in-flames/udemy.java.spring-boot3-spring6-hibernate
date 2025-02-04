package udemy.crudwithdb.sport.entity.coach;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class CricketCoach implements Coach {

	public CricketCoach() {
		System.out.println("In constructor : "+ getClass().getSimpleName());
	}
	
	// Define init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println("In doMyStartupStuff(): "+getClass().getSimpleName());
	}
	
	// Define destroy method
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println("In doMyCleanupStuff(): "+getClass().getSimpleName());
	}
	
	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes!";
	}
}
