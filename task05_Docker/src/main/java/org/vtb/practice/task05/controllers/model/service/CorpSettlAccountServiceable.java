package org.vtb.practice.task05.controllers.model.service;

import org.vtb.practice.task05.controllers.model.CorpSettlAccountBodyDto;
import org.vtb.practice.task05.controllers.model.ResponceAccMsg;

public interface CorpSettlAccountServiceable {
    ResponceAccMsg process(CorpSettlAccountBodyDto accountMsgIn);
}
