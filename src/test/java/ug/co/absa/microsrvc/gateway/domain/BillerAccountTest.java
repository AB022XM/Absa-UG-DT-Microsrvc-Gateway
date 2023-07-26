package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class BillerAccountTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillerAccount.class);
        BillerAccount billerAccount1 = new BillerAccount();
        billerAccount1.setId(1L);
        BillerAccount billerAccount2 = new BillerAccount();
        billerAccount2.setId(billerAccount1.getId());
        assertThat(billerAccount1).isEqualTo(billerAccount2);
        billerAccount2.setId(2L);
        assertThat(billerAccount1).isNotEqualTo(billerAccount2);
        billerAccount1.setId(null);
        assertThat(billerAccount1).isNotEqualTo(billerAccount2);
    }
}
