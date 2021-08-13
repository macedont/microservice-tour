package com.acme.tour.advice

import com.acme.tour.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandle {

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exeption: Exception): ResponseEntity<ErrorMessage>{

        return ResponseEntity(ErrorMessage("JSON ERROR", exeption.message ?: "invalid json"), HttpStatus.BAD_REQUEST)
    }


}