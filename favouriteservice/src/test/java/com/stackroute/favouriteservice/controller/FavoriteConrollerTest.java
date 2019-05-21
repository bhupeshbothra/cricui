package com.stackroute.favouriteservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.CricPlayer;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.service.UserCricPlayerService;

@WebMvcTest(FavoriteController.class)
@RunWith(SpringRunner.class)
public class FavoriteConrollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserCricPlayerService usercricPlayerService;

	@InjectMocks
	private FavoriteController favoriteController;

	private User user;

	private CricPlayer cricPlayer;

	private List list;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(favoriteController).build();
		cricPlayer = new CricPlayer();
		cricPlayer.setFullName("demoplayer");
		cricPlayer.setName("demoplayer");
		cricPlayer.setPid("12");
		list = new ArrayList();
		list.add(cricPlayer);

		user = new User();
		user.setUsername("jaindemo");

		user.setPlayerList(list);
	}

	@After
	public void delete() {
		user = null;
	}

	@Test
	public void TestAddUserPlayerToFavoriteList() throws Exception {
		when(usercricPlayerService.saveUserPlayerToFavoriteList(any(), eq(user.getUsername()))).thenReturn(user);
		mockMvc.perform(post("/api/v1/favoriteservice/user/{username}/player", user.getUsername())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(cricPlayer)))
				.andExpect(status().isCreated()).andDo(print());

		verify(usercricPlayerService, times(1)).saveUserPlayerToFavoriteList(any(), eq(user.getUsername()));
	}

	@Test
	public void testSavePlayerToFavoriteListFailure() throws Exception {
		when(usercricPlayerService.saveUserPlayerToFavoriteList(any(), eq(user.getUsername())))
				.thenThrow(PlayerAlreadyExistsException.class);
		mockMvc.perform(post("/api/v1/favoriteservice/user/{username}/player", user.getUsername())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(cricPlayer)))
				.andExpect(status().isConflict()).andDo(print());

		verify(usercricPlayerService, times(1)).saveUserPlayerToFavoriteList(any(), eq(user.getUsername()));

	}

	/*@Test
	public void testDeletePlayerToFavoriteList() throws Exception {
		when(usercricPlayerService.deleteUserPlayerToFavoriteList(any(), user.getUsername())).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/favoriteservice/user/{username}/player", user.getUsername())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(cricPlayer)))
				.andExpect(status().isOk()).andDo(print());

		verify(usercricPlayerService, times(1)).deleteUserPlayerToFavoriteList(cricPlayer.getPid(), user.getUsername());

	}*/

	@Test
	public void testGetAllTrackFromWishList() throws Exception {
		when(usercricPlayerService.getAllUserPlayerList((user.getUsername()))).thenReturn(list);
		mockMvc.perform(get("/api/v1/favoriteservice/user/{username}/player", user.getUsername())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(cricPlayer))).andExpect(status().isOk())
				.andDo(print());

		verify(usercricPlayerService, times(1)).getAllUserPlayerList(user.getUsername());

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
