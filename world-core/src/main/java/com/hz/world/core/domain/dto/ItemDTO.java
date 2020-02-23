package com.hz.world.core.domain.dto;

import java.io.Serializable;

import com.sun.tools.javac.tree.DCTree.DCDocRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItemDTO  implements Serializable,Comparable<ItemDTO> {

	private static final long serialVersionUID = -3766693016354347824L;
	
	private Integer id;
	
	private String name;
	
	private Integer type;
	
	private Integer itemType;
	
	private Integer num;
	
	private Integer compoundNum;
	
	private Integer sort;
	
	
	@Override
	public int compareTo(ItemDTO item) {          
		return this.sort - item.sort  ;
	}


	public ItemDTO(Integer id, String name, Integer type, Integer itemType, Integer num, Integer sort,Integer compoundNum) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.itemType = itemType;
		this.num = num;
		if (sort == null) {
			sort = 0;
		}
		this.sort = sort;
		this.compoundNum = compoundNum;
	}	
	
}
