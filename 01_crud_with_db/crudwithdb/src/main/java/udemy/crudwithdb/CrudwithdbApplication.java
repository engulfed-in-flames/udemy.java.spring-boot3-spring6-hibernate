package udemy.crudwithdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
// You can scan packages located in parent folders by using 'scanBasePackages' property
@SpringBootApplication
public class CrudwithdbApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(CrudwithdbApplication.class, args);
		checkBeansPresence("cricketCoach", "cricketPlayer");
	}

	// Check if the bean exists
	private static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println("Is " + beanName + " in ApplicationContext: " + context.containsBean(beanName));
		}
	}
}
