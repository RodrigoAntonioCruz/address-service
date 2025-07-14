package com.example.domain.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

 class AddressTest {
    @Test
    void shouldCreateAddressWithAllFields() {
        Address address = Fixtures.validAddress();

        assertThat(address.cep()).isEqualTo(Fixtures.VALID_CEP);
        assertThat(address.street()).isEqualTo(Fixtures.VALID_STREET);
        assertThat(address.neighborhood()).isEqualTo(Fixtures.VALID_NEIGHBORHOOD);
        assertThat(address.city()).isEqualTo(Fixtures.VALID_CITY);
        assertThat(address.state()).isEqualTo(Fixtures.VALID_STATE);
        assertThat(address.stateFullName()).isEqualTo(Fixtures.VALID_STATE_FULL_NAME);
        assertThat(address.region()).isEqualTo(Fixtures.VALID_REGION);
        assertThat(address.ibgeCode()).isEqualTo(Fixtures.VALID_IBGE_CODE);
        assertThat(address.areaCode()).isEqualTo(Fixtures.VALID_AREA_CODE);
        assertThat(address.siafiCode()).isEqualTo(Fixtures.VALID_SIAFI_CODE);
    }
}
