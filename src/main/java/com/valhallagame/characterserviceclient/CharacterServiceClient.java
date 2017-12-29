package com.valhallagame.characterserviceclient;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.characterserviceclient.message.CharacterNameAndOwnerUsernameParameter;
import com.valhallagame.characterserviceclient.message.CharacterNameParameter;
import com.valhallagame.characterserviceclient.message.EqippedItemsParameter;
import com.valhallagame.characterserviceclient.message.UsernameParameter;
import com.valhallagame.characterserviceclient.model.CharacterData;
import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.common.RestCaller;
import com.valhallagame.common.RestResponse;

public class CharacterServiceClient {

	private static RestCaller restCaller = new RestCaller();

	private static CharacterServiceClient characterServiceClient;

	private String characterServiceServerUrl = "http://localhost:" + DefaultServicePortMappings.CHARACTER_SERVICE_PORT;

	private CharacterServiceClient() {
	}

	public static void init(String characterServiceServerUrl) {
		CharacterServiceClient client = get();
		client.characterServiceServerUrl = characterServiceServerUrl;
	}

	public static CharacterServiceClient get() {
		if (characterServiceClient == null) {
			characterServiceClient = new CharacterServiceClient();
		}
		return characterServiceClient;
	}

	public RestResponse<CharacterData> getCharacterWithoutOwnerValidation(String characterName) throws IOException {
		CharacterNameParameter character = new CharacterNameParameter(characterName);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/get-character-without-owner-validation", character, CharacterData.class);
	}
	
	public RestResponse<CharacterData> getCharacter(String username, String characterName) throws IOException {
		CharacterNameAndOwnerUsernameParameter characterAndOwner = new CharacterNameAndOwnerUsernameParameter(
				characterName, username);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/get-character", characterAndOwner, CharacterData.class);
	}

	public RestResponse<JsonNode> getAll(String username) throws IOException {
		UsernameParameter usernameParam = new UsernameParameter(username);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/get-all", usernameParam, JsonNode.class);
	}

	public RestResponse<String> create(String username, String characterName) throws IOException {
		CharacterNameAndOwnerUsernameParameter characterAndOwner = new CharacterNameAndOwnerUsernameParameter(
				characterName, username);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/create", characterAndOwner, String.class);
	}

	public RestResponse<String> delete(String username, String characterName) throws IOException {
		CharacterNameAndOwnerUsernameParameter characterAndOwner = new CharacterNameAndOwnerUsernameParameter(
				characterName, username);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/delete", characterAndOwner, String.class);
	}

	public RestResponse<String> characterAvailable(String characterName) throws IOException {
		CharacterNameParameter cnp = new CharacterNameParameter(characterName);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/character-available", cnp, String.class);
	}

	public RestResponse<String> selectCharacter(String username, String characterName) throws IOException {
		CharacterNameAndOwnerUsernameParameter characterAndOwner = new CharacterNameAndOwnerUsernameParameter(
				characterName, username);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/select-character", characterAndOwner,
				String.class);
	}

	public RestResponse<CharacterData> getSelectedCharacter(String username) throws IOException {
		UsernameParameter usernameParam = new UsernameParameter(username);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/get-selected-character", usernameParam,
				CharacterData.class);
	}

	public RestResponse<CharacterData> saveEquippedItems(EqippedItemsParameter input) throws IOException {
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/save-equipped-items", input,
				CharacterData.class);
	}

	public RestResponse<String> createDebugCharacter(String username, String characterName) throws IOException {
		CharacterNameAndOwnerUsernameParameter characterAndOwner = new CharacterNameAndOwnerUsernameParameter(
				characterName, username);
		return restCaller.postCall(characterServiceServerUrl + "/v1/character/create-debug-character", characterAndOwner, String.class);
	}
}