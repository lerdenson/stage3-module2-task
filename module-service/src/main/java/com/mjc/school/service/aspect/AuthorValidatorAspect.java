package com.mjc.school.service.aspect;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.exceptions.ErrorCodeMessage;
import com.mjc.school.service.exceptions.ValidationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthorValidatorAspect {

    @Before("@annotation(com.mjc.school.service.annotations.ToValidate) " +
            "&& within(com.mjc.school.service.impl.AuthorService) " +
            "&& args(authorDtoRequest)")
    public void doValidate(AuthorDtoRequest authorDtoRequest) {
        validateName(authorDtoRequest.getName());
    }

    private void validateName(String name) {
        if (!(name.length() >= 3 && name.length() <= 15)) {
            throw new ValidationException(String.format(ErrorCodeMessage.INCORRECT_AUTHOR_NAME.toMsg(), name.length()));
        }
    }
}
