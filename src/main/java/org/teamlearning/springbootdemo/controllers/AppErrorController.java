package org.teamlearning.springbootdemo.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.teamlearning.springbootdemo.exception.ApiException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@ApiIgnore
@Controller
public class AppErrorController implements ErrorController {
    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public void error(HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        throw new ApiException(status.getReasonPhrase(), status, null);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}