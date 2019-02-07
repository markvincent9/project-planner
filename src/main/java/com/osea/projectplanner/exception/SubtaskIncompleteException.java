package com.osea.projectplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Project Not Found")
public class SubtaskIncompleteException extends Exception{
}
