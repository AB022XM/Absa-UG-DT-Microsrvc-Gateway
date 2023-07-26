package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class DTransactionDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DTransactionDetails.class);
        DTransactionDetails dTransactionDetails1 = new DTransactionDetails();
        dTransactionDetails1.setId(1L);
        DTransactionDetails dTransactionDetails2 = new DTransactionDetails();
        dTransactionDetails2.setId(dTransactionDetails1.getId());
        assertThat(dTransactionDetails1).isEqualTo(dTransactionDetails2);
        dTransactionDetails2.setId(2L);
        assertThat(dTransactionDetails1).isNotEqualTo(dTransactionDetails2);
        dTransactionDetails1.setId(null);
        assertThat(dTransactionDetails1).isNotEqualTo(dTransactionDetails2);
    }
}
