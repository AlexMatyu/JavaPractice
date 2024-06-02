package org.vtb.practice.task05.controllers.model.converter;

import org.vtb.practice.task05.controllers.model.CorpSettlAccountBody;
import org.vtb.practice.task05.controllers.model.CorpSettlAccountBodyDto;

import java.util.List;

public interface CorpSettlAccountBodyMapperable {
    public CorpSettlAccountBody dtoToModel(CorpSettlAccountBodyDto accountBodyDto);
}
