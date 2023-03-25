# marvelSnapDeck - Spring Boot-based Java web application

![Lines of code](https://img.shields.io/tokei/lines/github/momcilovicluka/marvelSnapDeck)
![GitHub repo size](https://img.shields.io/github/repo-size/momcilovicluka/marvelSnapDeck)
![GitHub commit activity](https://img.shields.io/github/commit-activity/w/momcilovicluka/marvelSnapDeck)

Repozitorij za Java Spring aplikaciju iz predmeta Razvoj Informacionih Sistema na PMF-u

Marvel Snap Decks is an application that allows users to share their favorite Marvel snap decks with other fans. In addition to sharing snap decks, users can also add friends and send messages to connect with other Marvel enthusiasts. Users can also leave comments on decks to share their thoughts and feedback, as well as add decks to their favorites for quick and easy access.

Features
- Share your favorite Marvel snap decks with other fans
- Add friends and connect with other Marvel enthusiasts
- Send messages to your friends within the app
- Leave comments on decks to share your thoughts and feedback
- Add decks to your favorites for quick and easy access
- User-friendly interface for easy navigation

A comprehensive Spring Boot-based Java web application built from the ground up, utilizing industry-standard development practices and technologies

•	Designed and implemented comprehensive CRUD operations for efficient data management

•	Developed a robust and scalable database using MySQL Workbench and optimized for high performance

•	Crafted a custom UI with an intuitive design, utilizing HTML5/CSS3 to enhance user engagement and satisfaction

•	Utilized version control with Git for streamlined management of code modifications

•	Incorporated industry-standard security measures, such as Spring security framework, to ensure secure authentication of users and safeguarding of sensitive data

# 🚀 Running the Application Locally

This guide will walk you through how to set up and run the application locally.

## 📋 Prerequisites

Make sure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/)

## 🛠️ Installation and Setup

1. Clone the repository to your local machine:
```
git clone https://github.com/your-username/your-repository.git
```
2. Open a terminal and navigate to the project directory.

3. Run the following command to build and start a containerized application:
```
docker compose up
```
This command may take several minutes to complete.

4. Once the container is up and running, open your web browser and navigate to `localhost:8082`. You will be redirected to phpMyAdmin, where you can manage your MySQL database.

- Username: `root`
- Password: `root`

5. Click on the `marvelSnapDeck Database` on the left-hand side of the screen, then click on `import` at the top of the screen.

6. Select `dump.sql` from the `Database` directory in the root of the project, then click `import` at the bottom of the page.

7. Once the import is complete, open your web browser and navigate to `localhost:8080` to access the application.

🎉 Congratulations! You have successfully installed and set up the application locally. Enjoy!
