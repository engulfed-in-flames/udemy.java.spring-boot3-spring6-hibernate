package udemy.crudwithdb.sport.entity.player;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CricketPlayer implements Player {
	
	private String name = "Louis Vuitton";
	
	@Override
	public String getName() {
		return name;
	}
}
