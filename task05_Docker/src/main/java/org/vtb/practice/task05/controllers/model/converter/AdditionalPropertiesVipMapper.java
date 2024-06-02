package org.vtb.practice.task05.controllers.model.converter;

import org.springframework.stereotype.Service;
import org.vtb.practice.task05.controllers.model.AdditionalPropertiesVip;
import org.vtb.practice.task05.controllers.model.AdditionalPropertiesVipDto;

import java.util.List;

@Service
public class AdditionalPropertiesVipMapper implements AdditionalPropertiesVipMapperable {
    public static AdditionalPropertiesVip dtoToModel(AdditionalPropertiesVipDto additionalPropertiesVipDto) {
        AdditionalPropertiesVip additProp = new AdditionalPropertiesVip();
        additProp.setKey(additionalPropertiesVipDto.key());
        additProp.setName(additionalPropertiesVipDto.name());
        additProp.setValue(additionalPropertiesVipDto.value());
        return additProp;
    }
}
