package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class DTransactionHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DTransactionHistory.class);
        DTransactionHistory dTransactionHistory1 = new DTransactionHistory();
        dTransactionHistory1.setId(1L);
        DTransactionHistory dTransactionHistory2 = new DTransactionHistory();
        dTransactionHistory2.setId(dTransactionHistory1.getId());
        assertThat(dTransactionHistory1).isEqualTo(dTransactionHistory2);
        dTransactionHistory2.setId(2L);
        assertThat(dTransactionHistory1).isNotEqualTo(dTransactionHistory2);
        dTransactionHistory1.setId(null);
        assertThat(dTransactionHistory1).isNotEqualTo(dTransactionHistory2);
    }
}
