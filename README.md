Abstract
As per the World Health Organization, in 2016, 13% of the adult population worldwide
(11% of men and 15% of women) was classified as obese. The global prevalence of

obesity has nearly tripled between 1975 and 2016, reaching 22.6% of the world's

population as of 2022, with 19% of men and 24.5% of women affected. These statistics

demonstrate a significant increase in the number of individuals suffering from obesity

worldwide. The SPORTER application aims to address this issue by providing users with the

motivation to engage in regular exercise, as physical activity not only helps to combat

obesity, but also reduces the risk of various other diseases, such as high cholesterol and

strokes, as well as increasing the likelihood of developing certain types of cancer, such as

colon cancer and breast cancer, and can even lead to an earlier death.

The SPORTER application offers users three different options for registration. They can
choose to use the app as a guest with limited functionality, or as a player who engages in

physical activity either at home or in a club. In both of these options, players have access

to exercise and nutrition resources within the app. These resources can be provided

through a personal trainer who oversees the player's progress through the app, or through

pre-set exercises and dietary plans that are available in the app's database. These plans

are typically designed according to player categories and not tailored specifically to

individual players.

Our project utilizes ReactJS for web development, which is based on the JavaScript
programming language. Additionally, it utilizes React Native for mobile development. The

application also utilizes an application programming interface (API) that is created using

Java Spring Boot to support the core features of the application and manage the

database.

In terms of databases, the project employs a SQL database (MySQL) for storing the
primary data of the application, while real-time features such as chat messaging are

implemented using Firebase messaging service.

Chapter
Introduction

1.1 Background
The incorporation of technology in physical activity can greatly enhance the

motivation and engagement of individuals in exercise. A sport app that offers

personalized workout plans, tracking, and analysis can empower individuals to

take control of their fitness journey and make exercise a regular part of their

lifestyle.

1.2 Problem Statement
Individuals face two main problems:

Lack of motivation: Difficulty in staying committed and consistent in
regular physical activity.
Lack of guidance: Difficulty in creating and following an effective workout
plan that suits their specific needs and goals.
Individuals also face challenges in tracking their progress and measuring
their performance.
1.3 Significant
A sport app that provides personalized workout plans and tracking tools can

help to increase the motivation, engagement, and consistency of individuals in

exercise. It empowers individuals to take control of their fitness journey, allowing

them to set and achieve their fitness goals and make exercise a regular part of

their lifestyle. This ultimately leads to a healthier and more active population.


1.4 Objectives and Scope
The main objective of the sport app is to provide individuals with a

comprehensive tool to help them achieve their fitness goals and make exercise

a regular part of their lifestyle. To achieve this goal, the app will focus on the

following objectives:

To provide personalized workout plans tailored to the specific needs and

goals of each individual user provided from the personal coach.

To enable users to track and monitor their progress and performance, allowing

them to measure their success and identify areas for improvement.

To provide users with a variety of exercises and workout plans, to cater to

different fitness levels and preferences.

To provide a user-friendly interface, which allows for easy navigation and

interaction with the app.

To provide coaches with a social aspect, through which they can connect with

other users, share their progress and get inspiration from others.

To ensure the security and privacy of the users' data.

The scope of the app includes the following features:

Personalized workout plans: Users will be able to create their own workout
plans based on their goals, fitness level, and preferences.
Progress tracking: Users will be able to track their progress and monitor their
performance.
Exercises and workout plans library: Users will have access to a variety of
exercises and workout plans, catering to different fitness levels and
preferences.
Social aspect: Users will be able to connect with other users and share their
progress.
User-friendly interface: The app will be designed to be easy to navigate and
use.
Data security: Users' data will be protected and kept private.

Some Features:

1.The Chat Messages Feature allows players to communicate with one
another and allows coaches to communicate with players and versa verse.
2.The Card Feature, located on the player's page, enables the purchase of
products and subscription to coaches or gyms.
3.The Charts Feature allows players to track various vital activities, such as
diet, exercise, water intake, and improvement in Body Mass Index BMI.
4.The Recommendation System Feature utilizes machine learning to assist
players in selecting the most suitable and near coach or gym.
5.The Server Administrator Service, provided by Spring Developers, enables
the administrator to monitor and visualize server requests and responses,
and much more services.

Chapter
Constraints and Earlier Coursework

2.1 Constraints Limitations
2.1.1 Time Limitations
Building the software was a time-consuming process that involved learning new
technologies, researching a topic, designing the user interface, and
implementing the software on both the front-end and the back-end. Despite
time constraints, certain constraints such as time constraints to search and
implement the best libraries for specific attributes have been encountered.
2.2 Earlier Coursework
In terms of previous coursework, the development of this app has been heavily influenced
by:
 Object Oriented Programming (JAVA): Java Spring Boot was used to build the back
end, applying many of the concepts learned in this course.
 Database design management: The database for this application is built using
MySQL, and database design principles and concepts, such as dependencies, keys,
and indexes, are applied.
 Web Development: Front-end HTML, CSS and JavaScript were used, this course
provided a solid grounding in these languages and understanding and building APIs.
 Software Engineering: All aspects of software development, including requirements,
architecture, and user acceptance have been covered and proven to be very
useful in the development of this application.
 Critical Thinking Scientific Research: The research process and preparation for
building this app was greatly influenced by the research skills and paper writing
techniques learned in this course.
 In short, the development of this application has been greatly influenced by the
knowledge and skills gained through various courses, such as object-oriented
programming, database design, web development, software engineering, critical
thinking, and scientific research. Despite the time constraints and limitations, the
application has been successfully developed and implemented.


Chapter
Literature Review

3.1 Review & Studies.
A thorough examination of the current research on sport apps indicates that sport apps
that offer personalized workout plans, tracking, and analysis can effectively increase

motivation, engagement, and consistency in exercise.

One study by Wang et al. (2018) found that individuals who used a sport app for tracking
physical activity had a significant increase in their physical activity levels compared to those

who did not use the app. Another study by Huang et al. (2019) found that the use of a sport

app that provided personalized workout plans and progress tracking led to a significant

increase in physical activity levels and a reduction in body mass index (BMI) in overweight

and obese individuals.

A review by McLean et al. (2018) also found that sport apps that provided personalized
feedback and progress tracking were effective in increasing physical activity levels and

reducing BMI. The review also highlighted the importance of social support in increasing

motivation and engagement in exercise, as sport apps that allowed users to connect with

other users and share their progress were found to be more effective than those that did not

have this feature.

Additionally, several studies have emphasized the importance of the design and user-
friendliness of sport apps in their effectiveness. A study by Kim et al. (2019) found that sport

apps with a user-friendly interface and easy navigation were more likely to be used

consistently and resulted in higher physical activity levels.

3.2 Why SPORTER is the best?
SPORTER is a full-featured sport app that stands out from the rest with its unique feature of
providing users with the nearest gyms and coaches using a recommendation system. Not

only does it provide personalized workout plans, tracking and analysis, but it also makes it

easy for users to find and access physical activity resources in their local area, making it

even more convenient for them to achieve their fitness goals and make exercise a regular

part of their lifestyle.



Chapter 4

Methodology

4.1 Architecture & Technologies Utilized
4.1.1 ReactJS & React Native
React is considered a flexible free open-source front-end JavaScript library
that is used to Develop fast and interactive web applications [3]. The library
was made by Meta (Facebook Formerly) and has a vast community and a
very large collection of libraries and reusable Components.
React Native is a JavaScript framework for building mobile applications
using React. It allows for building mobile apps for iOS and Android using the
same codebase. It also has a large community and a variety of available
libraries.
We decided to use React and React Native for many different reasons:

React and React Native offers a clean and easy to understand and utilize
syntax that’s called JSX Syntax. It is very similar to HTML but the main idea is
that it allows the developer to inject JavaScript code inside the front-end
design, which allows for more freedom Over controlling the component.
Reusable components is one of React’s strongest features. You don’t need
to recreate A layout you have made earlier, instead, you only need to
create it once and import
It everywhere else where it is needed.
A vast collection of strictly free libraries. This one other important feature of
React that allows the developers to use the fruits of other experienced
developers By utilizing their libraries. This doesn’t necessarily mean it is a
good thing, but it Serves the purpose of building projects in the fastest
interval.


4.1.2 MySQL
MySQL is a widely-used relational database management system (RDBMS)

that was chosen for this project for several reasons:

 Its compatibility with Java Spring Boot, which makes data handling
more efficient.
 Its ability to easily return updated documents or query results.
4.1.3 Firebase
Firebase is a comprehensive app development platform developed by

Google that enables the creation of mobile and web applications. It

provides a wide range of tools to support app development, including real-

time databases, authentication, and hosting services.

We chose to integrate Firebase into this project for several reasons,

including:

Its real-time database capabilities, which allow for efficient and

instantaneous transfer of data to and from the app, thus making it ideal for

implementing real-time functionalities such as chat messaging.

Its robust authentication services, which enable secure and seamless user

login and registration.

Its hosting services, which provide a reliable and cost-effective way to

deploy and host the app.

In summary, Firebase provides a complete set of tools for app

development, making it a valuable addition to the SPORTER app, as it

enables efficient and effective implementation of real-time functionality,

user authentication and hosting services.



4.1.4 React-Native-Maps

We used react-native-maps and react-native-chart-kit libraries in our

application for the following reasons:

react-native-maps allows for easy integration of maps and geolocation
features in the application, which is particularly useful for the feature that

suggests nearby gyms.

react-native-chart-kit is a library that allows for easy creation of
interactive charts, which is useful for displaying workout progress and other

data in the application.

4.1.5 Java Spring Boot
Spring Boot is an open-source framework that is used to develop

microservices-based applications using the Spring Framework. It provides a

simple and efficient way to create stand-alone and production-ready

Spring applications.

The decision to use Spring Boot in this project was based on several factors,

including:

Its ability to provide a simple and efficient way to create a stand-alone,
production-ready application.

Its compatibility with MySQL, which allows for easy data handling and
management.

4.1.6 Expo-Video-Player
We used expo-video-player library in our application to provide a seamless

video playback experience to the users while they are following the

exercise or workout videos that are provided by the app. it also provides a

lot of useful APIs to control the playback, audio, and other features.



4.1.7 Docker

Docker is a containerization platform that allows for easy deployment and

management of applications and their dependencies. It was used in this

project to provide containerization of the database to the admin panel,

ensuring enhanced security and maintenance of the database.

4.1.8 Others
In addition to the technologies discussed above, several other techniques

were utilized in the development of the SPORTER app. These include:

4.1.8.1 JSON Web Tokens (JWT)
JWT is a standard for creating secure, self-contained access tokens.
These tokens are used to authenticate users and provide secure
communication between the app and the API. JWT is an open standard
(RFC 7519) that defines a compact and self-contained way for securely
transmitting information between parties.
4.1.8.2 GitHub
GitHub is a web-based platform for version control and collaboration
that allows developers to work together on a project. In this project,
GitHub was used to manage the source code, track issues and bugs,
and collaborate with other team members.
4.1.8.3 Git
Git is a free and open-source distributed version control system that
allows developers to manage the source code of their projects. It was
used in this project to track changes, collaborate with other team
members, and maintain the history of the project.
4.1.8.4 Axios
Axios is a JavaScript library that enables easy communication between
the app and the API. It is a popular library for making HTTP requests from
JavaScript and it was used in this project to handle all the API calls.


4.2 Features & Implementation
SPORTER is a combination btw the SPORTER app, SPORTER administration

panel & Server Manager let’s take them on by one starting from the sporter

app.

4.2.1 SPORTER App
The app contains 2 user types player & coach so it has 3 different types of
interfaces player interfaces, coach interfaces, common interfaces
between the players & the coaches.
