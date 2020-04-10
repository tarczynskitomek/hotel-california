package it.tarczynski.hotel.california.core.annotation;

import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Retention
@Target(allowedTargets = AnnotationTarget.CLASS)
public @interface ValueObject {
}
