package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class ImageServerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageServer.class);
        ImageServer imageServer1 = new ImageServer();
        imageServer1.setId(1L);
        ImageServer imageServer2 = new ImageServer();
        imageServer2.setId(imageServer1.getId());
        assertThat(imageServer1).isEqualTo(imageServer2);
        imageServer2.setId(2L);
        assertThat(imageServer1).isNotEqualTo(imageServer2);
        imageServer1.setId(null);
        assertThat(imageServer1).isNotEqualTo(imageServer2);
    }
}
