package com.mathcunha.ppmtool.web;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    Pattern UK_PATTERN = Pattern.compile("constraint \\[(?<constraint>.+)\\]");

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class, ConstraintViolationException.class})
    protected ResponseEntity<Map<String, String>> handleIntegrityConstraint(RuntimeException ex, WebRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        Matcher matcher = UK_PATTERN.matcher(ex.getMessage());
        matcher.find();
        String constraint = matcher.group("constraint");
        errorMap.put(constraint.split("_")[2], constraint);
        return new ResponseEntity<Map<String, String>> (errorMap, HttpStatus.BAD_REQUEST);
    }

    public static void main(String args[]){
        Pattern UK_PATTERN = Pattern.compile("constraint \\[(?<constraint>.+)\\]");

        Matcher matcher = UK_PATTERN.matcher("ould not execute statement; SQL [n/a]; constraint [UK_sem4so99abwti26hmi35e2kyb]");
        matcher.find();

        System.out.println(matcher.group("constraint"));
    }
}
