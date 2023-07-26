package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class LiquidationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Liquidation.class);
        Liquidation liquidation1 = new Liquidation();
        liquidation1.setId(1L);
        Liquidation liquidation2 = new Liquidation();
        liquidation2.setId(liquidation1.getId());
        assertThat(liquidation1).isEqualTo(liquidation2);
        liquidation2.setId(2L);
        assertThat(liquidation1).isNotEqualTo(liquidation2);
        liquidation1.setId(null);
        assertThat(liquidation1).isNotEqualTo(liquidation2);
    }
}
