package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class GenericConfigsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GenericConfigs.class);
        GenericConfigs genericConfigs1 = new GenericConfigs();
        genericConfigs1.setId(1L);
        GenericConfigs genericConfigs2 = new GenericConfigs();
        genericConfigs2.setId(genericConfigs1.getId());
        assertThat(genericConfigs1).isEqualTo(genericConfigs2);
        genericConfigs2.setId(2L);
        assertThat(genericConfigs1).isNotEqualTo(genericConfigs2);
        genericConfigs1.setId(null);
        assertThat(genericConfigs1).isNotEqualTo(genericConfigs2);
    }
}
