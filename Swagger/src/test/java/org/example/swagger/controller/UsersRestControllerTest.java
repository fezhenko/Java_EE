package org.example.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.swagger.converter.UserConverter;
import org.example.swagger.dto.CreateUserDto;
import org.example.swagger.dto.UpdateUserDto;
import org.example.swagger.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UsersRestController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Slf4j
public class UsersRestControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

    @Test
    @DisplayName("Successfully find all users")
    public void findUsersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        BDDMockito.then(userService)
                .should()
                .findUsers();
    }

    @Test
    @DisplayName("Find user by valid Id")
    public void finduserById() throws Exception {
        final Long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/{userId}", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
        BDDMockito.then(userService)
                .should()
                .getUserById(userId);
    }

    @Test
    @DisplayName("Create a user with valid data")
    public void createUser() throws Exception {
        final CreateUserDto createUserDto = CreateUserDto
                .builder()
                .username("testName")
                .password("testPassword")
                .role("ADMIN")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(createUserDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
        BDDMockito.then(userService)
                .should()
                .createUser(
                        createUserDto.getUsername(),
                        createUserDto.getPassword(),
                        createUserDto.getRole());
    }

    @Test
    @DisplayName("Create a user with invalid data")
    public void createUserWithInvalidData() throws Exception {
        final CreateUserDto createUserDto = CreateUserDto
                .builder()
                .username(null)
                .password("testPassword")
                .role("ADMIN")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(createUserDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
        BDDMockito.then(userService)
                .shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Update a user with valid data")
    public void updateUserWithValidData() throws Exception {
        final UpdateUserDto updateUserDto = UpdateUserDto.builder()
                .username("testUsername")
                .role("MANAGER")
                .build();
        final Long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/users/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(updateUserDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
        BDDMockito.then(userService)
                .should()
                .updateUser(
                        userId,
                        updateUserDto.getUsername(),
                        updateUserDto.getRole());
    }

    @ParameterizedTest
    @DisplayName("Update a user with invalid data")
    @CsvSource(value = {
            "invalid! {\"username\": \"null\", \"role\": \"14\"}! reason",
            "7! {\"username\": \"testUsername\", \"role\": \"\"}! reason",
            "1! {\"username\": \"testUsername\"}! reason"
        }, delimiter = '!')
    public void updateUserWithInvalidData(final String userId, String content) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
        BDDMockito.then(userService)
                .shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Delete a user with valid userId")
    public void deleteUserWithValidUserId() throws Exception {
        final Long userId = 2L;
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/users/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
        BDDMockito.then(userService)
                .should()
                .deleteUser(userId);
    }

    @Test
    @DisplayName("Delete a user with invalid userId")
    public void deleteUserWithInvalidUserId() throws Exception {
        final Boolean userId = true;
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
        BDDMockito.then(userService)
                .shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Verify bad request is returned if Id type is invalid")
    public void callFindUserWithInvalidTypeOfId() throws Exception {
        final String userId = "invalidId";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users/{userId}", userId)
                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
        BDDMockito.then(userService)
                .shouldHaveNoInteractions();
    }

}
