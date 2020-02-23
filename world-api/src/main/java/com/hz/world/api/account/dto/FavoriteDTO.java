package com.hz.world.api.account.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class FavoriteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Set<String> favoriteMusic; 
	
	private Set<String> favoriteSport; 
	
	private Set<String> favoriteMoive; 
	
	private Set<String> favoriteBook; 
	
	private Set<String> favoriteTraval; 
	
	private Set<String> favoriteFood; 


}
