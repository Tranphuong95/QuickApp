package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class ImagexTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Imagex.class);
        Imagex imagex1 = new Imagex();
        imagex1.setId(1L);
        Imagex imagex2 = new Imagex();
        imagex2.setId(imagex1.getId());
        assertThat(imagex1).isEqualTo(imagex2);
        imagex2.setId(2L);
        assertThat(imagex1).isNotEqualTo(imagex2);
        imagex1.setId(null);
        assertThat(imagex1).isNotEqualTo(imagex2);
    }
}
