package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class CustomAuditTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomAudit.class);
        CustomAudit customAudit1 = new CustomAudit();
        customAudit1.setId(1L);
        CustomAudit customAudit2 = new CustomAudit();
        customAudit2.setId(customAudit1.getId());
        assertThat(customAudit1).isEqualTo(customAudit2);
        customAudit2.setId(2L);
        assertThat(customAudit1).isNotEqualTo(customAudit2);
        customAudit1.setId(null);
        assertThat(customAudit1).isNotEqualTo(customAudit2);
    }
}
