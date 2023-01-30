package org.example.swagger.exceptionmapping;


import lombok.extern.slf4j.Slf4j;
import org.example.swagger.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView userNotFoundException(final UserNotFoundException userNotFoundException) {
        log.error("unexpected exception", userNotFoundException);
        final ModelAndView modelAndView = new ModelAndView();
        final String userNotFoundExceptionMessage = userNotFoundException.getMessage();
        modelAndView.addObject("message:", userNotFoundExceptionMessage);
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView methodNotSupportedException(
            final HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        log.error("unexpected exception", httpRequestMethodNotSupportedException);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message:", httpRequestMethodNotSupportedException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView methodArgumentTypeMismatch(
            final MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", methodArgumentTypeMismatchException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView methodArgumentNotValidException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", methodArgumentNotValidException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView illegalStateException(
            final IllegalStateException illegalStateException) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", illegalStateException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(java.lang.AssertionError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView assertionErrorException(
            final java.lang.AssertionError assertionError) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", assertionError.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
