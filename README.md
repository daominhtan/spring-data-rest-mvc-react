# Hi

This is sample application using spring-data-rest-mvc with spring-security on the backend and ReactJS on the front-end, including an authentication component.

Run the application with `mvn spring-boot:run`.

Once it's running, access the app at `http://localhost:8080/`.

The java code is all where you would expect it for a maven app (src/main/java).

The React/js code is all in `src/main/resources/static/js/application/main.js`.

# Goals

In particular, I want to use this as a reference implementation for how to implement authentication and access control through  spring-security, but from a single page app. Spring Security has conventions and expectations around login that are more rigid than what's expected in a reactive client world. For instance, passing authentication tokens around, model login dialogs, etc. are all a bit out of the range of "normal" for spring-security. So I wanted to develop this as a reference implementation for how to do it.

## Why a Spring backend?

Java still rocks my world. You get total control at the cost of verbosity. There's a lot that's just much better on the backend in a java world compared to other languages. And spring-data-rest-mvc makes building a robust backend really simple once you know how to do it. Figuring out how to do it properly is the tricky, time-consuming part. So I thought a reference implementation like this would be a big help.

## Why a React front-end?

React's component model is really slick, composable and comfortable once you get the hang of it.
