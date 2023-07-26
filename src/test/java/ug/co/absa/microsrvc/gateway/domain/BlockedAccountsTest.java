package ug.co.absa.microsrvc.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.microsrvc.gateway.web.rest.TestUtil;

class BlockedAccountsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlockedAccounts.class);
        BlockedAccounts blockedAccounts1 = new BlockedAccounts();
        blockedAccounts1.setId(1L);
        BlockedAccounts blockedAccounts2 = new BlockedAccounts();
        blockedAccounts2.setId(blockedAccounts1.getId());
        assertThat(blockedAccounts1).isEqualTo(blockedAccounts2);
        blockedAccounts2.setId(2L);
        assertThat(blockedAccounts1).isNotEqualTo(blockedAccounts2);
        blockedAccounts1.setId(null);
        assertThat(blockedAccounts1).isNotEqualTo(blockedAccounts2);
    }
}
