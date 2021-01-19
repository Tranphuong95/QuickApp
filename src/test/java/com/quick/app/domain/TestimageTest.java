package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class TestimageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Testimage.class);
        Testimage testimage1 = new Testimage();
        testimage1.setId(1L);
        Testimage testimage2 = new Testimage();
        testimage2.setId(testimage1.getId());
        assertThat(testimage1).isEqualTo(testimage2);
        testimage2.setId(2L);
        assertThat(testimage1).isNotEqualTo(testimage2);
        testimage1.setId(null);
        assertThat(testimage1).isNotEqualTo(testimage2);
    }
}
