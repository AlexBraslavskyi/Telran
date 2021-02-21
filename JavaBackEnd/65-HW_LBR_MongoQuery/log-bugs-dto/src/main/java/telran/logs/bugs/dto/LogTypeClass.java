package telran.logs.bugs.dto;

public class LogTypeClass {

public static final String LOG_TYPE = "logType";
public LogType logType;


public LogTypeClass(LogType logType) {
	this.logType = logType;
}
public LogType getLogType() {
	return logType;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((logType == null) ? 0 : logType.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	LogTypeClass other = (LogTypeClass) obj;
	if (logType != other.logType)
		return false;
	return true;
}
}
