package com.example.adapter.output.security;

import com.example.adapter.output.persistence.user.UserPersistenceAdapter;
import com.example.adapter.output.security.mapper.UserDetailsOutputMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class UserDetailsServiceAdapterTest {

    private UserPersistenceAdapter adapter;
    private UserDetailsOutputMapper mapper;
    private UserDetailsServiceAdapter userDetailsService;

    @BeforeEach
    void setUp() {
        adapter = mock(UserPersistenceAdapter.class);
        mapper = mock(UserDetailsOutputMapper.class);
        userDetailsService = new UserDetailsServiceAdapter(mapper, adapter);
    }

    @Test
    @DisplayName("Deve retornar UserDetails quando usuário existir")
    void shouldLoadUserDetailsWhenUserExists() {
        var user = Fixtures.validUser();
        var customUserDetails = Fixtures.customUserDetails();

        when(adapter.findByUsername(Fixtures.VALID_USERNAME))
                .thenReturn(Optional.of(user));
        when(mapper.toCustomUserDetails(user))
                .thenReturn(customUserDetails);

        var result = userDetailsService.loadUserByUsername(Fixtures.VALID_USERNAME);

        assertThat(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", Fixtures.VALID_ID)
                .hasFieldOrPropertyWithValue("username", Fixtures.VALID_USERNAME);

        verify(adapter).findByUsername(Fixtures.VALID_USERNAME);
        verify(mapper).toCustomUserDetails(user);
    }


    @Test
    @DisplayName("Deve lançar UsernameNotFoundException quando usuário não existir")
    void shouldThrowExceptionWhenUserDoesNotExist() {
        when(adapter.findByUsername(Fixtures.VALID_USERNAME))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername(Fixtures.VALID_USERNAME))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(Fixtures.VALID_USERNAME);

        verify(adapter).findByUsername(Fixtures.VALID_USERNAME);
        verifyNoInteractions(mapper);
    }
}
