package udemy.crudwithdb.sport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.crudwithdb.sport.entity.coach.Coach;

@RestController
public class CoachController {

	// Define a private field for the dependency
	private Coach coach;
	private Coach anotherCoach;

	// For constructor injection
	// @Autowired
	// public CoachController(Coach coach) {
	// this.coach = coach;
	// }

	// For setter injection
	@Autowired
	public CoachController(@Qualifier("inning") Coach coach, @Qualifier("inning") Coach anotherCoach) {
		this.coach = coach;
		this.coach = anotherCoach;
		System.out.println("In constructor : " + getClass().getSimpleName());
		System.out.println("coach == anotherCoach? : " + (coach == anotherCoach));
	}

	@GetMapping("/daily-workout")
	public String getDailyWorkout() {
		return coach.getDailyWorkout();
	}
}