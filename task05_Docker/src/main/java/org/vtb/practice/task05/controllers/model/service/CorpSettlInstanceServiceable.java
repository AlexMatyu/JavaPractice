package org.vtb.practice.task05.controllers.model.service;

import org.vtb.practice.task05.controllers.model.CorpSettlInstanceBodyDto;
import org.vtb.practice.task05.controllers.model.ResponceInstMsg;

public interface CorpSettlInstanceServiceable {
    ResponceInstMsg process(CorpSettlInstanceBodyDto instanceMsgIn);
}
