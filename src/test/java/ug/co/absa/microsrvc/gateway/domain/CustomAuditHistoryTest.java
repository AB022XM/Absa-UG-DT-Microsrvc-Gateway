package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class CustomAuditHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomAuditHistory.class);
        CustomAuditHistory customAuditHistory1 = new CustomAuditHistory();
        customAuditHistory1.setId(UUID.randomUUID());
        CustomAuditHistory customAuditHistory2 = new CustomAuditHistory();
        customAuditHistory2.setId(customAuditHistory1.getId());
        assertThat(customAuditHistory1).isEqualTo(customAuditHistory2);
        customAuditHistory2.setId(UUID.randomUUID());
        assertThat(customAuditHistory1).isNotEqualTo(customAuditHistory2);
        customAuditHistory1.setId(null);
        assertThat(customAuditHistory1).isNotEqualTo(customAuditHistory2);
    }
}
