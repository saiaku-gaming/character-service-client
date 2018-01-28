package com.valhallagame.characterserviceclient.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.validation.CheckLowercase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectCharacterParameter {
	@NotNull
	@CheckLowercase
	private String characterName;
	@NotNull
	@CheckLowercase
	private String username;
}
