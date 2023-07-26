package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class DevicesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Devices.class);
        Devices devices1 = new Devices();
        devices1.setId(1L);
        Devices devices2 = new Devices();
        devices2.setId(devices1.getId());
        assertThat(devices1).isEqualTo(devices2);
        devices2.setId(2L);
        assertThat(devices1).isNotEqualTo(devices2);
        devices1.setId(null);
        assertThat(devices1).isNotEqualTo(devices2);
    }
}
