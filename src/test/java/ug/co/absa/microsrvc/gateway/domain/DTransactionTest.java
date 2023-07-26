package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class DTransactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DTransaction.class);
        DTransaction dTransaction1 = new DTransaction();
        dTransaction1.setId(1L);
        DTransaction dTransaction2 = new DTransaction();
        dTransaction2.setId(dTransaction1.getId());
        assertThat(dTransaction1).isEqualTo(dTransaction2);
        dTransaction2.setId(2L);
        assertThat(dTransaction1).isNotEqualTo(dTransaction2);
        dTransaction1.setId(null);
        assertThat(dTransaction1).isNotEqualTo(dTransaction2);
    }
}
