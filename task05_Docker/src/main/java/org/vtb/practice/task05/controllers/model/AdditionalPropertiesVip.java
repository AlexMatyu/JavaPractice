package org.vtb.practice.task05.controllers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdditionalPropertiesVip {
    private String key;			// Ключ
    private String value;		// Значение
    private String name;

    @Override
    public String toString() {
        return "AdditionalPropertiesVip{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
