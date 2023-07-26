package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class IncomingJSONRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncomingJSONRequest.class);
        IncomingJSONRequest incomingJSONRequest1 = new IncomingJSONRequest();
        incomingJSONRequest1.setId(1L);
        IncomingJSONRequest incomingJSONRequest2 = new IncomingJSONRequest();
        incomingJSONRequest2.setId(incomingJSONRequest1.getId());
        assertThat(incomingJSONRequest1).isEqualTo(incomingJSONRequest2);
        incomingJSONRequest2.setId(2L);
        assertThat(incomingJSONRequest1).isNotEqualTo(incomingJSONRequest2);
        incomingJSONRequest1.setId(null);
        assertThat(incomingJSONRequest1).isNotEqualTo(incomingJSONRequest2);
    }
}
