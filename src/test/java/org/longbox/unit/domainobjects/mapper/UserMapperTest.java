package org.longbox.unit.domainobjects.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.entity.User;
import org.longbox.domainobjects.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {
    UserDto dto, dto1;
    User user1, user2;
    @BeforeEach
    void setup(){
        dto = new UserDto();
        dto.setUserName("user1");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setDob(new Date());
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");
        dto.setCountry("USA");
        dto.setAboutMe("About me.");
        dto.setJoinDate(new Date());
        dto.setDefaults();

        dto1 = new UserDto();
        dto1.setUserName("user2");
        dto1.setFirstName("Jane");
        dto1.setLastName("Doe");
        dto1.setDob(new Date());
        dto1.setEmail("jane.doe@example.com");
        dto1.setPassword("password");
        dto1.setCountry("Canada");
        dto1.setAboutMe("About me.");
        dto1.setJoinDate(new Date());
        dto1.setDefaults();

        user1 = new User();
        user1.setUserName("user1");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setDob(new Date());
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password");
        user1.setCountry("USA");
        user1.setJoinDate(new Date());
        user1.setDefaults();

        user2 = new User();
        user2.setUserName("user2");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setDob(new Date());
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("password");
        user2.setCountry("Canada");
        user2.setJoinDate(new Date());
        user2.setDefaults();
    }
    @Test
    public void testToDto() {
        // Create a User entity
        User user = new User();
        user.setUserName("user1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDob(new Date());
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setCountry("USA");
        user.setJoinDate(new Date());
        user.setId(1L);
        user.setJoinDate(new Date());
        user.setComicsReading(5);
        user.setComicsFinished(10);
        user.setAboutMe("About me");
        user.setPreferredGenre("Genre1, Genre2, Genre3");

        // Map the entity to DTO
        UserDto dto = UserMapper.toDto(user);
        String[] expectedGenres = {"Genre1", "Genre2", "Genre3"};
        // Verify that the DTO attributes match the entity
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUserName(), dto.getUserName());
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());
        assertEquals(user.getDob(), dto.getDob());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getCountry(), dto.getCountry());
        assertEquals(user.getJoinDate(), dto.getJoinDate());
        assertEquals(user.getComicsReading(), dto.getComicsReading());
        assertEquals(user.getComicsFinished(), dto.getComicsFinished());
        assertEquals(user.getAboutMe(), dto.getAboutMe());
        assertArrayEquals(expectedGenres,dto.getPreferredGenre());
    }

    @Test
    public void testToEntity() {
        dto.setComicsReading(5);
        dto.setComicsFinished(10);
        dto.setPreferredGenre(new String[]{"Genre1","Genre2"});

        // Map the DTO to entity
        User user = UserMapper.toEntity(dto);

        // Verify that the entity attributes match the DTO
        assertEquals(0L, user.getId());
        assertEquals(dto.getUserName(), user.getUserName());
        assertEquals(dto.getFirstName(), user.getFirstName());
        assertEquals(dto.getLastName(), user.getLastName());
        assertEquals(dto.getDob(), user.getDob());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getCountry(), user.getCountry());
        assertEquals(dto.getJoinDate(), user.getJoinDate());
        assertEquals(dto.getComicsReading(), user.getComicsReading());
        assertEquals(dto.getComicsFinished(), user.getComicsFinished());
        assertEquals(dto.getAboutMe(), user.getAboutMe());
        assertEquals("Genre1, Genre2",user.getPreferredGenre());
    }

    @Test
    public void testToDtoList() {
        // Create a list of User entities
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.get(0).setPreferredGenre("Genre1, Genre2");
        userList.add(user2);
        userList.get(1).setPreferredGenre("Genre1, Genre2");

        // Map the list of entities to DTOs
        List<UserDto> dtoList = UserMapper.toDtoList(userList);

        // Verify that the size of the DTO list matches the size of the entity list
        assertEquals(userList.size(), dtoList.size());
        String[] expectedGenres = {"Genre1", "Genre2"};
        // Verify that each DTO in the list matches its corresponding entity
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserDto dto = dtoList.get(i);
            assertEquals(user.getId(), dto.getId());
            assertEquals(user.getUserName(), dto.getUserName());
            assertEquals(user.getFirstName(), dto.getFirstName());
            assertEquals(user.getLastName(), dto.getLastName());
            assertEquals(user.getDob(), dto.getDob());
            assertEquals(user.getEmail(), dto.getEmail());
            assertEquals(user.getPassword(), dto.getPassword());
            assertEquals(user.getCountry(), dto.getCountry());
            assertEquals(user.getJoinDate(), dto.getJoinDate());
            assertEquals(user.getComicsReading(), dto.getComicsReading());
            assertEquals(user.getComicsFinished(), dto.getComicsFinished());
            assertEquals(user.getAboutMe(), dto.getAboutMe());
            assertArrayEquals(expectedGenres,dto.getPreferredGenre());
        }
    }

    @Test
    public void testToEntityList() {
        // Create a list of UserDto objects
        List<UserDto> dtoList = new ArrayList<>();
        dtoList.add(dto);
        dtoList.get(0).setPreferredGenre(new String[]{"Genre1", "Genre2"});
        dtoList.add(dto1);
        dtoList.get(1).setPreferredGenre(new String[]{"Genre1", "Genre2"});

        // Map the list of DTOs to entities
        List<User> userList = UserMapper.toEntityList(dtoList);

        // Verify that the size of the entity list matches the size of the DTO list
        assertEquals(dtoList.size(), userList.size());

        // Verify that each entity in the list matches its corresponding DTO
        for (int i = 0; i < dtoList.size(); i++) {
            UserDto dto = dtoList.get(i);
            User user = userList.get(i);
            assertEquals(0L, user.getId());
            assertEquals(dto.getUserName(), user.getUserName());
            assertEquals(dto.getFirstName(), user.getFirstName());
            assertEquals(dto.getLastName(), user.getLastName());
            assertEquals(dto.getDob(), user.getDob());
            assertEquals(dto.getEmail(), user.getEmail());
            assertEquals(dto.getPassword(), user.getPassword());
            assertEquals(dto.getCountry(), user.getCountry());
            assertEquals(dto.getJoinDate(), user.getJoinDate());
            assertEquals(dto.getComicsReading(), user.getComicsReading());
            assertEquals(dto.getComicsFinished(), user.getComicsFinished());
            assertEquals(dto.getAboutMe(), user.getAboutMe());
            assertEquals("Genre1, Genre2", user.getPreferredGenre());
        }
    }
}

