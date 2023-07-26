package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class AppsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Apps.class);
        Apps apps1 = new Apps();
        apps1.setId(1L);
        Apps apps2 = new Apps();
        apps2.setId(apps1.getId());
        assertThat(apps1).isEqualTo(apps2);
        apps2.setId(2L);
        assertThat(apps1).isNotEqualTo(apps2);
        apps1.setId(null);
        assertThat(apps1).isNotEqualTo(apps2);
    }
}
