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
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("JSON ERROR", exception.message ?: "invalid json"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(com.acme.tour.exception.PromocaoNotFoundException::class)
    fun PromocaoNotFoundException(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Promocao não localizada", exception.message !!), HttpStatus.NOT_FOUND)
    }
}