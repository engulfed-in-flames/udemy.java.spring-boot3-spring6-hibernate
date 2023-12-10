package udemy.crudwithdb.sport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.crudwithdb.sport.entity.player.CricketPlayer;
import udemy.crudwithdb.sport.entity.player.Player;

@RestController
public class PlayerController {

	private Player player;

	@Autowired
	public PlayerController(Player player) {
		this.player = player;
	}

	@GetMapping("/cricket-player-name")
	public String getCricketPlayerName() {

		return player.getName();
	}
}
