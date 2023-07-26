package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class OutgoingJSONResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutgoingJSONResponse.class);
        OutgoingJSONResponse outgoingJSONResponse1 = new OutgoingJSONResponse();
        outgoingJSONResponse1.setId(1L);
        OutgoingJSONResponse outgoingJSONResponse2 = new OutgoingJSONResponse();
        outgoingJSONResponse2.setId(outgoingJSONResponse1.getId());
        assertThat(outgoingJSONResponse1).isEqualTo(outgoingJSONResponse2);
        outgoingJSONResponse2.setId(2L);
        assertThat(outgoingJSONResponse1).isNotEqualTo(outgoingJSONResponse2);
        outgoingJSONResponse1.setId(null);
        assertThat(outgoingJSONResponse1).isNotEqualTo(outgoingJSONResponse2);
    }
}
