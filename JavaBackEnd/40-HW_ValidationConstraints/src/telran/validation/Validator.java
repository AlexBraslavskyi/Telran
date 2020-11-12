package telran.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import telran.validation.constraints.Email;
import telran.validation.constraints.Future;
import telran.validation.constraints.IpV4;
import telran.validation.constraints.Max;
import telran.validation.constraints.Min;
import telran.validation.constraints.NotEmpty;
import telran.validation.constraints.NotNull;
import telran.validation.constraints.Past;
import telran.validation.constraints.Valid;

public class Validator {

	public static List<String> validate(Object obj) {
		List<String> res = new ArrayList<String>();
		Field fields[] = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			for (Annotation annotation : field.getAnnotations()) {

				processAnnotation(res, obj, annotation, field);

			}
		}

		return res;
	}

	static void processAnnotation(List<String> res, Object obj, Annotation annotation, Field field) {

		if (annotation.annotationType() == NotNull.class) {
			NotNull notNullConstraint = (NotNull) annotation;

			try {
				if (field.get(obj) == null) {
					res.add(notNullConstraint.message());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (annotation.annotationType() == Min.class) {
			Min minConstraint = (Min) annotation;

			try {
				if (field.getInt(obj) < minConstraint.value()) {
					res.add(String.format(minConstraint.message()));

				}
			} catch (Exception e) {
				res.add(String.format("type of field %s not Integer", field.getName()));
			}

		} else if (annotation.annotationType() == Max.class) {
			Max maxConstraint = (Max) annotation;
			try {
				if (field.getInt(obj) > maxConstraint.value()) {
					res.add(String.format(maxConstraint.message()));

				}
			} catch (Exception e) {
				res.add(String.format("type of field %s not Integer", field.getName()));
			}
		} else if (annotation.annotationType() == Future.class) {
			Future futureConstraint = (Future) annotation;
			try {
				if (LocalDate.parse(futureConstraint.value()).isAfter(LocalDate.parse((CharSequence) field.get(obj)))) {
					res.add(String.format(futureConstraint.message()));
				}
			} catch (Exception e) {
				res.add(String.format("type of field %s not String", field.getName()));
			}
		} else if (annotation.annotationType() == Past.class) {
			Past pastConstraint = (Past) annotation;
			try {
				if (LocalDate.parse(pastConstraint.value()).isBefore(LocalDate.parse((CharSequence) field.get(obj)))) {
					res.add(String.format(pastConstraint.message()));
				}
			} catch (Exception e) {
				res.add(String.format("type of field %s not String", field.getName()));
			}
		} else if (annotation.annotationType() == Email.class) {
			Email emailConstraint = (Email) annotation;
			try {
				if (!field.get(obj).toString().matches(emailAddress())) {
					res.add(String.format(emailConstraint.message()));
				}
			} catch (Exception e) {
				res.add(String.format("type of field %s not String", field.getName()));
			}
		} else if (annotation.annotationType() == IpV4.class) {
			IpV4 ipv4Constraint = (IpV4) annotation;
			try {
				if (!field.get(obj).toString().matches(ipV4())) {
					res.add(String.format(ipv4Constraint.message()));
				}
			} catch (Exception e) {
				res.add(String.format("type of field %s not String", field.getName()));
			}
		} else if (annotation.annotationType() == NotEmpty.class) {
			NotEmpty notEmptyConstraint = (NotEmpty) annotation;
			try {
				if (field.get(obj).equals("")) {
					res.add(String.format(notEmptyConstraint.message()));
				}
			} catch (Exception e) {
				res.add(String.format("type of field %s not String", field.getName()));
			}
		} else if (annotation.annotationType() == Valid.class) {
			try {
				Field[] validFields = field.get(obj).getClass().getDeclaredFields();
				for (Field f : validFields) {
					f.setAccessible(true);
					for (Annotation a : f.getAnnotations()) {
						processAnnotation(res, field.get(obj), a, f);
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String emailAddress() {

		String firstPart = "[^\\s,]+";
		String domein = "[a-zA-Z]-?+[a-zA-Z]+";
		String regex = String.format("%1$s@%2$s(\\.%2$s){1,3}", firstPart, domein);
		return regex;

	}

	public static String Less256() {

		String regex = "\\d|\\d?|[0-1]\\d\\d|2[0-4]\\d|25[0-5]";
		return regex;
	}

	public static String ipV4() {
		String regex = String.format("((%1$s)\\.){3}(%1$s)", Less256());
		return regex;

	}
}
