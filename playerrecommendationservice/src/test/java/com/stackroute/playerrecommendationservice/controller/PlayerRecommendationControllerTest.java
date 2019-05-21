package com.stackroute.playerrecommendationservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.service.PlayerRecommendationService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PlayerRecommendationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerRecommendationService playerRecommendationService;

	@InjectMocks
	private PlayerRecommendationController playerRecommendationController;

	public Player player;

	List<Player> players;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(playerRecommendationController).build();
		players = new ArrayList();
		player = new Player();
		player.setFullName("Bhupesh");
		player.setName("Bhupesh");
		player.setPid("114");
		player.setTotalcount(1);
	}

	@After
	public void delete() {
		player = null;
	}

	@Test
	public void testGetAllTrackFromWishList() throws Exception {
		when(playerRecommendationService.getAllUserPlayerList()).thenReturn(players);
		mockMvc.perform(get("/api/v1/playerservice/list").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(players))).andExpect(status().isOk()).andDo(print());

		verify(playerRecommendationService, times(1)).getAllUserPlayerList();

	}

	private static String jsonToString(final Object obj) throws Exception {
		String result;

		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (Exception e) {
			result = "error in Json processing";
		}
		return result;
	}

}
