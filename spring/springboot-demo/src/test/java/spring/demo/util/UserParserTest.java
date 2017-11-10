package spring.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import spring.demo.dto.UserDto;
import spring.demo.persistence.primary.domain.User;

/**
 * Created by wangfacheng on 2017-11-10.
 */
public class UserParserTest {

    private List<User> mockUsers;

    @Before
    public void setup() {
        mockUsers = new ArrayList<User>() {
            {
                add(new User("a", "4"));
                add(new User("b", "4"));
                add(new User("c", "4"));
                add(new User("d", "4"));
            }
        };
    }

    @Test
    public void toSimpleUserDtoTest() {
        List<UserDto> userDtos = UserParser.toSimpleUserDto(mockUsers);

        Assert.assertNotNull(userDtos);
        Assert.assertEquals(mockUsers.size(), userDtos.size());
    }
}
