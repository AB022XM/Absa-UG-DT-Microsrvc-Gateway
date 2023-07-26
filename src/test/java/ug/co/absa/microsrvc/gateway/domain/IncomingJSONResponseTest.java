package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class IncomingJSONResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncomingJSONResponse.class);
        IncomingJSONResponse incomingJSONResponse1 = new IncomingJSONResponse();
        incomingJSONResponse1.setId(1L);
        IncomingJSONResponse incomingJSONResponse2 = new IncomingJSONResponse();
        incomingJSONResponse2.setId(incomingJSONResponse1.getId());
        assertThat(incomingJSONResponse1).isEqualTo(incomingJSONResponse2);
        incomingJSONResponse2.setId(2L);
        assertThat(incomingJSONResponse1).isNotEqualTo(incomingJSONResponse2);
        incomingJSONResponse1.setId(null);
        assertThat(incomingJSONResponse1).isNotEqualTo(incomingJSONResponse2);
    }
}
