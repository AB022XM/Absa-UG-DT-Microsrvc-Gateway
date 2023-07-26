package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class AmolDTransactionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AmolDTransactions.class);
        AmolDTransactions amolDTransactions1 = new AmolDTransactions();
        amolDTransactions1.setId(1L);
        AmolDTransactions amolDTransactions2 = new AmolDTransactions();
        amolDTransactions2.setId(amolDTransactions1.getId());
        assertThat(amolDTransactions1).isEqualTo(amolDTransactions2);
        amolDTransactions2.setId(2L);
        assertThat(amolDTransactions1).isNotEqualTo(amolDTransactions2);
        amolDTransactions1.setId(null);
        assertThat(amolDTransactions1).isNotEqualTo(amolDTransactions2);
    }
}
