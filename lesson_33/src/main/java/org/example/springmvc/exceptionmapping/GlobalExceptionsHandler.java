package org.example.springmvc.exceptionmapping;


import org.example.springmvc.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(final Exception exception) {
        // TODO: подключить логгер и залогировать ошибку: log.error("unexpected exception", exception);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Something Went Wrong");
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView bindExceptionHandler(final BindException bindException) {
        final ModelAndView modelAndView = new ModelAndView();
        final String bindExceptionMessages = bindException.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        modelAndView.addObject("message", bindExceptionMessages);
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFoundException(final UserNotFoundException userNotFoundException) {
        final ModelAndView modelAndView = new ModelAndView();
        final String userNotFoundExceptionMessage = userNotFoundException.getMessage();
        modelAndView.addObject("message", userNotFoundExceptionMessage);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
