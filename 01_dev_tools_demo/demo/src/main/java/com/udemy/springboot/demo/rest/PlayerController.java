package com.udemy.springboot.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springboot.demo.classes.Coach;
import com.udemy.springboot.temp.Player;

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
