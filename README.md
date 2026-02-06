# Exam Retake: Budget tracker

### Preparing

- Clone your repository to your computer
- Commit your changes frequently!
- Go through the whole description before starting it!

### What are the rules?

- Please work individually
- ChatGPT and Phind usages are allowed
- Be careful when copy-pasting parts from previous projects
- Take your time to plan your software (domain objects, software layers, urls, components etc.)
- Always check the acceptance criteria before finishing a user story
- You can complete the user stories in any order you want

### Technical requirements

- Use a Maven project
- Use SpringBoot with JPA
- Use MySQL to store the data
- Use Bootstrap to make it cool looking, create a navigation bar
- Use Logback to log what is happening
- Use Angular for frontend

### Brief description 
Create a budget tracker app that people can use to track their expenses. When they buy something,
or pay somewhere, they can save the amount and a category in the application to overview the overall expenses after a time. 

### User stories:

### 0. Backlog item: Config (2 points)

- Create the project layout (necessary folders and packages)
- Create the database
- Create the angular app
- Add required css and js files

#### 1. US: Create new category (11 points)
As a user, I want to create a new Category that is stored in a persistent database (MySQL), 
so I can store my budget categories in long term. 

Category properties/fields:
 - name (String, mandatory)

**Acceptance criteria:**
 - There is a link on the navigation bar that navigates to the "Create new category" page (1p) 
 - The form has all necessary fields and a Save button (3p)
 - If there is NO validation error, the category data is stored in the database (use DTOs) (4p)
 - The input is validated and validation errors displayed under the input fields (2p)
 - Server logging: "Category is created" is logged to the console (1p) 

#### 2. US: Expense category list (6 points)
As a user, I want to see my budget categories in a list sorted in ascending order by their name. 

Category's fields to display in the list:
 - id
 - name

**Acceptance criteria:**
 - There is a link on the navigation bar that navigates to the "Category list" page (1p)
 - The categories are displayed with all fields (1p)
 - They are ordered by name (2p)
 - After save, the user is redirected to the Category list page (1p)
 - Server logging: "Category list page is requested" is logged to the console (1p) 

**NOT list:**
 - there is no "modify category" and "delete category" function

#### 3. US: New expense (15 points)
As a user, I want to create new Expenses that are stored in a persistent database (MySQL). 
I want to create them one after the other, so after saving one, I want to have an empty new Expense form (with a message,
that the previous one was successfully saved.).  

Expense properties/fields:
 - amount (type: Double, mandatory)
 - expenseDate (type: LocalDate, mandatory)
 - category, that is chosen from a select list (type: Category, mandatory)
 - description (type: String, optional)

**Acceptance criteria:**
 - There is a link on the navigation bar that navigates to the "Create new expense" page (1p) 
 - The form is shown with all fields and a Save button (3p)
 - The available categories are displayed as options (in the select input field) (3p)
 - If there is NO validation error, the expense data is stored in the database (use DTOs) (4p)
 - The input is validated and validation errors are displayed under the input fields (2p)
 - After saving, the user is redirected to an **empty New expense form** (to create expenses faster) (1p)
 - Server logging: "Expense is created" is logged to the console (1p) 

#### 4. US: Expense list (8 points)
As a user, I want to see my expenses in a list ordered by expenseDate in descending order! So I can see my recent expenses. 

Expense's fields to display in the list:
 - amount
 - expenseDate (format: yyyy-MM-dd)
 - category's name
 - description, if it is not empty

````
Hint: for date formatting use DateTimeFormatter class or the @JsonFormat annotation
````

**Acceptance criteria:**
 - There is a link on the navigation bar that navigates to the "Expense list" page (1p)
 - The expenses are displayed with all fields (3p)
 - The date is formatted (1p)
 - The expenses' order is correct (2p)
 - Server logging: "Expenses list page is requested" is logged to the console (1p) 

**NOT list:**
 - there is no "modify expense" and "delete expense" function


#### 5. US: Summary page (8 points)
As a user, I want to see the overview of my expenses for the past, grouped by categories.

| Category name | Sum amount |
|:-------------:|:----------:|
| Food          | 80.0       |
| Travel        | 443.0      |
| Clothes       | 404.0      |
| House         | 302.0      |


**Acceptance criteria:**
 - There is a link on the navigation bar that navigates to the "Summary" page (1p) 
 - A table or list is displayed (use DTOs) (2p)
 - Only those categories are displayed that have at least one expense (2p) 
 - Correct sum values in the "Sum amount" column (2p)
 - Server logging: "Summary  page is requested" is logged to the console (1p) 

## License 
Copyright © Progmasters (QTC Kft.), 2016-2023.
All rights reserved. No part or the whole of this Teaching Material (TM) may be reproduced, copied, distributed, publicly performed, disseminated to the public, adapted or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of QTC Kft. This TM may only be used for the purposes of teaching exclusively by QTC Kft. and studying exclusively by QTC Kft.’s students and for no other purposes by any parties other than QTC Kft.
This TM shall be kept confidential and shall not be made public or made available or disclosed to any unauthorized person.
Any dispute or claim arising out of the breach of these provisions shall be governed by and construed in accordance with the laws of Hungary. 
