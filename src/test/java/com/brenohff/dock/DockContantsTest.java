package com.brenohff.dock;

import com.brenohff.dock.consts.DockConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class DockContantsTest {

    @Test
    public void constants() {
        DockConstants constants = new DockConstants();
        assertEquals(DockConstants.JSON_OBJECT_PLAT, DockConstants.JSON_OBJECT_PLAT);
    }
}
