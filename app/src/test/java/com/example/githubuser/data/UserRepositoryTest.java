package com.example.githubuser.data;

import com.example.githubuser.data.entity.User;
import com.example.githubuser.network.GithubApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.subscribers.TestSubscriber;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class UserRepositoryTest {

    private GithubApi githubApi;
    private UserRepository userRepository;
    @Before
    public void setup() {
        githubApi = Mockito.mock(GithubApi.class);
        userRepository = new UserRepository(githubApi);
    }

    @Test
    public void getUsers() {
        TestSubscriber<List<User>> subscriber = TestSubscriber.create();
        userRepository.users().subscribe(subscriber);

        Mockito.when(githubApi.getUsers()).thenReturn(new Call<List<User>>() {
            @Override
            public Response<List<User>> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<List<User>> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<User>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        });

        userRepository.getUsers();

        subscriber.assertSubscribed();
    }

    @Test
    public void isValidUsers() {
        List<User> testUsers = null;
        assertFalse(userRepository.isValidUsers(testUsers));

        testUsers = new ArrayList<>();
        assertTrue(userRepository.isValidUsers(testUsers));

        User fakeUser = new User();
        testUsers.add(fakeUser);
        assertFalse(userRepository.isValidUsers(testUsers));
    }
}