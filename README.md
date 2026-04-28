\# 🌿 Budget Tracker



A full-stack personal finance management application built with Spring Boot and Angular, deployed on Railway.



🔗 \*\*Live demo:\*\* https://budget-tracker-frontend-production-b067.up.railway.app



\---



\## Features



\- \*\*User authentication\*\* – Register and login with JWT-based security

\- \*\*Expense tracking\*\* – Create, edit and delete expenses with categories and dates

\- \*\*Category management\*\* – Create and manage custom expense categories

\- \*\*Expense list\*\* – View all expenses filtered by category, sorted by date

\- \*\*Summary\*\* – Category breakdown with pie chart, filterable by year and month

\- \*\*Monthly expenses\*\* – Overview of total spending per month

\- \*\*Balance tracking\*\* – Set a monthly starting balance and track remaining amount after expenses



\---



\## Tech Stack



\### Backend

\- Java 21

\- Spring Boot 3.5

\- Spring Security + JWT

\- Spring Data JPA / Hibernate

\- MySQL

\- Lombok

\- Maven



\### Frontend

\- Angular 21

\- TypeScript

\- Bootstrap 5

\- Chart.js



\### DevOps

\- Railway (cloud deployment)

\- GitHub (version control)



\---



\## Architecture



The application follows a standard REST API architecture:



\- The \*\*Angular frontend\*\* communicates with the \*\*Spring Boot backend\*\* via HTTP requests

\- The backend handles authentication, business logic and database operations

\- JWT tokens are stored in localStorage and sent with every request via an HTTP interceptor

\- Each user's data is fully isolated — categories, expenses and balances are user-specific



\---



\## Getting Started (Local Development)



\### Backend

1\. Clone the repository

2\. Configure `application.yaml` with your local MySQL credentials

3\. Run the Spring Boot application from IntelliJ



\### Frontend

1\. Navigate to the `angular-frontend` folder

2\. Run `npm install`

3\. Run `ng serve`

4\. Open `http://localhost:4200`



\---



\## Author



Brendon Komodi

