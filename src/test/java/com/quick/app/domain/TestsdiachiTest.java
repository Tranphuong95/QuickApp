package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class TestsdiachiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Testsdiachi.class);
        Testsdiachi testsdiachi1 = new Testsdiachi();
        testsdiachi1.setId(1L);
        Testsdiachi testsdiachi2 = new Testsdiachi();
        testsdiachi2.setId(testsdiachi1.getId());
        assertThat(testsdiachi1).isEqualTo(testsdiachi2);
        testsdiachi2.setId(2L);
        assertThat(testsdiachi1).isNotEqualTo(testsdiachi2);
        testsdiachi1.setId(null);
        assertThat(testsdiachi1).isNotEqualTo(testsdiachi2);
    }
}
