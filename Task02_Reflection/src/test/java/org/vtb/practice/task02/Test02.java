package org.vtb.practice.task02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Test02 {

    @Test
    @DisplayName("Check Cache and Mutator")
    void checkCacheMutator (){
        FractionTst fractionExt = new FractionTst(1, 4);
        Fractionable fractionable = Utils.cache(fractionExt);
        double res1 = fractionable.doubleValue();
        double res2 = fractionable.doubleValue();

        Assertions.assertEquals(res1, res2);
        Assertions.assertEquals(fractionExt.methodCallCount, 1);

        fractionable.setNum(2);

        Assertions.assertEquals(fractionExt.methodCallCount, 1);

        double res3 = fractionable.doubleValue();
        double res4 = fractionable.doubleValue();
        res4 = fractionable.doubleValue();

        Assertions.assertNotEquals(res3, res1);
        Assertions.assertEquals(res3, res4);
        Assertions.assertEquals(fractionExt.methodCallCount, 2);
    }
}


