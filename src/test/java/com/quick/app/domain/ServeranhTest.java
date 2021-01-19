package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class ServeranhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Serveranh.class);
        Serveranh serveranh1 = new Serveranh();
        serveranh1.setId(1L);
        Serveranh serveranh2 = new Serveranh();
        serveranh2.setId(serveranh1.getId());
        assertThat(serveranh1).isEqualTo(serveranh2);
        serveranh2.setId(2L);
        assertThat(serveranh1).isNotEqualTo(serveranh2);
        serveranh1.setId(null);
        assertThat(serveranh1).isNotEqualTo(serveranh2);
    }
}
