package com.quick.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quick.app.web.rest.TestUtil;

public class FileanhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fileanh.class);
        Fileanh fileanh1 = new Fileanh();
        fileanh1.setId(1L);
        Fileanh fileanh2 = new Fileanh();
        fileanh2.setId(fileanh1.getId());
        assertThat(fileanh1).isEqualTo(fileanh2);
        fileanh2.setId(2L);
        assertThat(fileanh1).isNotEqualTo(fileanh2);
        fileanh1.setId(null);
        assertThat(fileanh1).isNotEqualTo(fileanh2);
    }
}
