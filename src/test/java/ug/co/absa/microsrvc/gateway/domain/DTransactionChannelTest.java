package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class DTransactionChannelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DTransactionChannel.class);
        DTransactionChannel dTransactionChannel1 = new DTransactionChannel();
        dTransactionChannel1.setId(1L);
        DTransactionChannel dTransactionChannel2 = new DTransactionChannel();
        dTransactionChannel2.setId(dTransactionChannel1.getId());
        assertThat(dTransactionChannel1).isEqualTo(dTransactionChannel2);
        dTransactionChannel2.setId(2L);
        assertThat(dTransactionChannel1).isNotEqualTo(dTransactionChannel2);
        dTransactionChannel1.setId(null);
        assertThat(dTransactionChannel1).isNotEqualTo(dTransactionChannel2);
    }
}
