package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class DefaultSettingsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DefaultSettings.class);
        DefaultSettings defaultSettings1 = new DefaultSettings();
        defaultSettings1.setId(1L);
        DefaultSettings defaultSettings2 = new DefaultSettings();
        defaultSettings2.setId(defaultSettings1.getId());
        assertThat(defaultSettings1).isEqualTo(defaultSettings2);
        defaultSettings2.setId(2L);
        assertThat(defaultSettings1).isNotEqualTo(defaultSettings2);
        defaultSettings1.setId(null);
        assertThat(defaultSettings1).isNotEqualTo(defaultSettings2);
    }
}
