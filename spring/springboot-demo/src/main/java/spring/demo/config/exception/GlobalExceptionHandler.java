package spring.demo.config.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by facheng on 17-11-30.
 *
 * 全局Exception处理
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final String DEFAULT_ERROR_VIEW = "error";

    public static final String DEFAULT_ERROR_MESSAGE = "error_message";

    @ExceptionHandler
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {

        LOGGER.error(e.getMessage(), e);
        ModelAndView errorModelAndView = new ModelAndView(DEFAULT_ERROR_VIEW);
        Map<String, Object> model = errorModelAndView.getModel();
        model.put(DEFAULT_ERROR_MESSAGE, e);
        return errorModelAndView;
    }
}
