package org.example.socialnetwork.exceptionmapping;


import lombok.extern.slf4j.Slf4j;
import org.example.socialnetwork.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(final Exception exception) {
        log.error("unexpected exception", exception);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message:", "Something Went Wrong");
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView bindExceptionHandler(final BindException bindException) {
        log.error("unexpected exception", bindException);
        final ModelAndView modelAndView = new ModelAndView();
        final String bindExceptionMessages = bindException.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        modelAndView.addObject("message:", bindExceptionMessages);
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFoundException(final UserNotFoundException userNotFoundException) {
        log.error("unexpected exception", userNotFoundException);
        final ModelAndView modelAndView = new ModelAndView();
        final String userNotFoundExceptionMessage = userNotFoundException.getMessage();
        modelAndView.addObject("message:", userNotFoundExceptionMessage);
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView methodNotSupportedException(
            final HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        log.error("unexpected exception", httpRequestMethodNotSupportedException);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message:", httpRequestMethodNotSupportedException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
