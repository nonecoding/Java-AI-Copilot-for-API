package org.ai.aicopilotforapi.common.util;


import org.ai.aicopilotforapi.common.exception.AppException;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorUtils {
  private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  public static <T> void validate(T obj) {
    var errs = validator.validate(obj);
    if (!errs.isEmpty()) {
      throw new AppException(400, errs.iterator().next().getMessage());
    }
  }
}