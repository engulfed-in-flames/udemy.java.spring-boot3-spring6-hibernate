# Aspect-Oriented Programming (AOP)

## Two Main Problems

- Code Tangling

  - There are Logging and security code tangled in

- Code Scattering
  - If we need to change logging or security code, we have update all classes

## AOP

- Programming technique based on concept of an Aspect
  - Aspect can be reused at multiple locations
  - Code for aspect is defined in a single class
  - Solve code tangling and code scattering
  - Reusable, Configurable
  - Usecases: logging, security, transactions
  - Disadvantages: hard to follow, minor performance cost
- Handling Cross-Cutting Concerns (logging, security...)

## Spring AOP and AspectJ

※ Spring Initializr website doesn't have an option for adding AOP dependency

Start with Spring AOP, and then move to AspectJ

- Recommended books
  - Aspect-Oriented Developemnt with Use Cases
  - AspectJ in Action

## Best Practices: Aspect and Advices

- Keep the code small and fast
- Do not perform any expensive / slow operations

## AOP Terminology

- Pointcut ← Advice를 적용할 예상 표현식
