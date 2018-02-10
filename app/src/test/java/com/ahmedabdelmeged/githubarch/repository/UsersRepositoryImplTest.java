package com.ahmedabdelmeged.githubarch.repository;

import com.ahmedabdelmeged.githubarch.api.GithubService;
import com.ahmedabdelmeged.githubarch.common.BaseTest;
import com.ahmedabdelmeged.githubarch.model.UserMapper;
import com.ahmedabdelmeged.githubarch.model.UserRaw;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ahmed Abd-Elmeged on 2/6/2018.
 */

public class UsersRepositoryImplTest extends BaseTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private GithubService githubService;

    @Mock
    private Throwable throwable;

    @InjectMocks
    private UsersRepositoryImpl usersRepositoryImpl;

    @Test
    public void fetchUsersEmitsErrorWhenNetworkServiceErrors() {
        when(githubService.getUsers()).thenReturn(Single.error(throwable));
        //usersRepositoryImpl.fetchUsers().test().assertError(throwable);
    }

    @Test
    public void usersRawItemsFromServiceAreMapped() throws Exception {
        List<UserRaw> userRawList = createUserRawList();
        when(githubService.getUsers()).thenReturn(Single.just(userRawList));

      //  usersRepositoryImpl.fetchUsers().subscribe();

        verify(userMapper).apply(userRawList.get(0));
        verify(userMapper).apply(userRawList.get(1));
        verify(userMapper).apply(userRawList.get(2));
    }

    private static List<UserRaw> createUserRawList() {
        return new ArrayList<UserRaw>() {
            {
                add(mock(UserRaw.class));
                add(mock(UserRaw.class));
                add(mock(UserRaw.class));
            }
        };
    }

}
