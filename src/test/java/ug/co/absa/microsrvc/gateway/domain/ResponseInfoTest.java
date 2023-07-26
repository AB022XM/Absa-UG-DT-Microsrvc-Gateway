package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class ResponseInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponseInfo.class);
        ResponseInfo responseInfo1 = new ResponseInfo();
        responseInfo1.setId(1L);
        ResponseInfo responseInfo2 = new ResponseInfo();
        responseInfo2.setId(responseInfo1.getId());
        assertThat(responseInfo1).isEqualTo(responseInfo2);
        responseInfo2.setId(2L);
        assertThat(responseInfo1).isNotEqualTo(responseInfo2);
        responseInfo1.setId(null);
        assertThat(responseInfo1).isNotEqualTo(responseInfo2);
    }
}
