1.Microservice logs -provider
1.1.Classwork contains test with printing out the log content. Your task is to write automation test that checks that there have been 10 logs and all logs are different (there is no two equaled logs)
2.Microservice logs-db-provider
2.1.Complete functionality of the method receiving from a producer log object and saving
it into MongoDB
2.1.1. Validation: The validator has method validate that gets reference to LogDto object and returns Set of ConstraintViolation objects. If the set is not empty, the validation errors exist. Each object has method getMessage(). So far, you should validate LogDto object, display out all error messages.
2.1.2.Storing in MongoDB You should apply the same functionality with Mongo repository that we have
applied in the project logs-bugs-mongo-document for testing. Only in the project logs-db-provider it should be a part of the functionality rather than testing from the project logs-bugs-mongo-document
2.1.3.Testing You should write test cases for testing saving documents in the case of the validlogs and not saving documents in the case of the invalid logs