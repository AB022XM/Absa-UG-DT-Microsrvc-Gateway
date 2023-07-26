package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class RequestInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestInfo.class);
        RequestInfo requestInfo1 = new RequestInfo();
        requestInfo1.setId(1L);
        RequestInfo requestInfo2 = new RequestInfo();
        requestInfo2.setId(requestInfo1.getId());
        assertThat(requestInfo1).isEqualTo(requestInfo2);
        requestInfo2.setId(2L);
        assertThat(requestInfo1).isNotEqualTo(requestInfo2);
        requestInfo1.setId(null);
        assertThat(requestInfo1).isNotEqualTo(requestInfo2);
    }
}
