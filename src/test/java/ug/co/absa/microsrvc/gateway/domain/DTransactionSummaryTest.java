package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class DTransactionSummaryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DTransactionSummary.class);
        DTransactionSummary dTransactionSummary1 = new DTransactionSummary();
        dTransactionSummary1.setId(1L);
        DTransactionSummary dTransactionSummary2 = new DTransactionSummary();
        dTransactionSummary2.setId(dTransactionSummary1.getId());
        assertThat(dTransactionSummary1).isEqualTo(dTransactionSummary2);
        dTransactionSummary2.setId(2L);
        assertThat(dTransactionSummary1).isNotEqualTo(dTransactionSummary2);
        dTransactionSummary1.setId(null);
        assertThat(dTransactionSummary1).isNotEqualTo(dTransactionSummary2);
    }
}
