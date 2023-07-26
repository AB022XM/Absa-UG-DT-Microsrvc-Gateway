package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class PaymentConfigTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentConfig.class);
        PaymentConfig paymentConfig1 = new PaymentConfig();
        paymentConfig1.setId(1L);
        PaymentConfig paymentConfig2 = new PaymentConfig();
        paymentConfig2.setId(paymentConfig1.getId());
        assertThat(paymentConfig1).isEqualTo(paymentConfig2);
        paymentConfig2.setId(2L);
        assertThat(paymentConfig1).isNotEqualTo(paymentConfig2);
        paymentConfig1.setId(null);
        assertThat(paymentConfig1).isNotEqualTo(paymentConfig2);
    }
}
