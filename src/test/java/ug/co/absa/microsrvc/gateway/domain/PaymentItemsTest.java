package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class PaymentItemsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentItems.class);
        PaymentItems paymentItems1 = new PaymentItems();
        paymentItems1.setId(1L);
        PaymentItems paymentItems2 = new PaymentItems();
        paymentItems2.setId(paymentItems1.getId());
        assertThat(paymentItems1).isEqualTo(paymentItems2);
        paymentItems2.setId(2L);
        assertThat(paymentItems1).isNotEqualTo(paymentItems2);
        paymentItems1.setId(null);
        assertThat(paymentItems1).isNotEqualTo(paymentItems2);
    }
}
