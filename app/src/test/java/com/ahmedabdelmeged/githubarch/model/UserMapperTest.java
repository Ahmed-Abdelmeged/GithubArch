package com.ahmedabdelmeged.githubarch.model;

import com.ahmedabdelmeged.githubarch.common.BaseTest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Ahmed Abd-Elmeged on 2/6/2018.
 */
public class UserMapperTest extends BaseTest {

    private UserMapper userMapper;

    @Before
    public void setupMapper() {
        userMapper = new UserMapper();
    }

    @Test
    public void loginIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .login("Ahmed")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.login()).isEqualTo("Ahmed");
    }

    @Test
    public void idIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .id(555)
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.id()).isEqualTo(555);
    }

    @Test
    public void avatarUrlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .avatarUrl("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.avatarUrl()).isEqualTo("www.google.com");
    }

    @Test
    public void urlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .url("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.url()).isEqualTo("www.google.com");
    }

    @Test
    public void followersUrlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .followersUrl("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.followersUrl()).isEqualTo("www.google.com");
    }

    @Test
    public void followingUrlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .followingUrl("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.followingUrl()).isEqualTo("www.google.com");
    }

    @Test
    public void gistsUrlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .gistsUrl("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.gistsUrl()).isEqualTo("www.google.com");
    }

    @Test
    public void starredUrlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .starredUrl("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.starredUrl()).isEqualTo("www.google.com");
    }

    @Test
    public void organizationsUrlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .organizationsUrl("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.organizationsUrl()).isEqualTo("www.google.com");
    }

    @Test
    public void reposUrlIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .reposUrl("www.google.com")
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.reposUrl()).isEqualTo("www.google.com");
    }

    @Test
    public void siteAdminIsMappedCorrectly() {
        UserRaw userRaw = UserDataTestUtils.userRawTestBuilder()
                .siteAdmin(true)
                .build();

        User user = userMapper.apply(userRaw);
        assertThat(user.siteAdmin()).isEqualTo(true);
    }

    @Test
    public void userAfterMappingEqualToUser() {
        User userAfter = userMapper.apply(UserDataTestUtils.userRawTestBuilder().build());
        User defaultUser = UserDataTestUtils.userTestBuilder().build();
        assertThat(userAfter).isEqualTo(defaultUser);
    }


}
