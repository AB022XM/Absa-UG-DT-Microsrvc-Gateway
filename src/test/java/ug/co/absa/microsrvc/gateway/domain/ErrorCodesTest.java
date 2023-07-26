package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class ErrorCodesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ErrorCodes.class);
        ErrorCodes errorCodes1 = new ErrorCodes();
        errorCodes1.setId(1L);
        ErrorCodes errorCodes2 = new ErrorCodes();
        errorCodes2.setId(errorCodes1.getId());
        assertThat(errorCodes1).isEqualTo(errorCodes2);
        errorCodes2.setId(2L);
        assertThat(errorCodes1).isNotEqualTo(errorCodes2);
        errorCodes1.setId(null);
        assertThat(errorCodes1).isNotEqualTo(errorCodes2);
    }
}
