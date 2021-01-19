package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class TestuuidTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Testuuid.class);
        Testuuid testuuid1 = new Testuuid();
        testuuid1.setId(1L);
        Testuuid testuuid2 = new Testuuid();
        testuuid2.setId(testuuid1.getId());
        assertThat(testuuid1).isEqualTo(testuuid2);
        testuuid2.setId(2L);
        assertThat(testuuid1).isNotEqualTo(testuuid2);
        testuuid1.setId(null);
        assertThat(testuuid1).isNotEqualTo(testuuid2);
    }
}
