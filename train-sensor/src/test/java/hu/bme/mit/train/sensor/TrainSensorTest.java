package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.user.TrainUserImpl;
import hu.bme.mit.train.interfaces.*;


import static org.mockito.Mockito.*;

public class TrainSensorTest {

    @Before
    public void before() {
        TrainUserImpl mockTU = mock(TrainUserImpl.class);
        TrainController mockTC = mock(TrainController.class);
        when(mockTC.getReferenceSpeed()).thenReturn(50);
        TrainSensorImpl ts = new TrainSensorImpl(mockTC, mockTU);
    }

    @Test
    public void AbsoluteMarginPositiveTest() {
        
    }
}
