package org.vtb.practice.task05.controllers.model.processor;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@Component("necessfields")
public class NecessaryFields implements UnaryOperator<Object> {
    @Override
    public Object apply(Object corpSettlMsg) {
//        System.out.println("Step super: " + this.getClass().getSimpleName() + " > " + corpSettlMsg.getClass().getSimpleName());

        List<String> emptyFields = emptyMarkedFields(corpSettlMsg);

        if (!emptyFields.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Имя обязательного параметра " + emptyFields.toString().replace(",", ";") + " не заполнено.");
        return corpSettlMsg;
    }

    @SneakyThrows
    private List<String> emptyMarkedFields(Object obj) {
        List<String> res = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(RequiredParameter.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value == null) {
                    res.add(field.getName());
                } else {
                    if (!value.getClass().isPrimitive()
                            && !value.getClass().getName().startsWith("java.lang"))
                        if (value instanceof List) {
                            List<?> listValue = (List<?>) value;
                            for (Object listItem : listValue) {
                                res.addAll(emptyMarkedFields(listItem));
                            }
                        } else
                            res.addAll(emptyMarkedFields(value));
                }
            }
        }
        return res;
    }
}
