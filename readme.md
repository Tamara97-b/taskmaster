# Task Master

## day one
 * in this lab  I make main page In particular, it should have a heading at the top of the page, an image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

 * Add a Task page
   On the “Add a Task” page, allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.

  * All Tasks
   The all tasks page should just be an image with a back button; it needs no functionality.

![homepage screen](screenshots/mainPage.PNG)

![add task screen](screenshots/addTask.PNG)

![all task screen](screenshots/allTask.PNG)


## day tow
Task Detail Page
 ### the requirments for this lab are :
* Create a Task Detail page. It should have a title at the top of the page, and a Lorem Ipsum description.
* Settings Page
Create a Settings page. It should allow users to enter their username and hit save.
* Homepage
The main page should be modified to contain three different buttons with hardcoded task titles. When a user taps one of the titles, it should go to the Task Detail page, and the title at the top of the page should match the task title that was tapped on the previous page.
The homepage should also contain a button to visit the Settings page, and once the user has entered their username, it should display “{username}’s tasks” above the three task buttons.

![homepage screen](screenshots/homePageeForLab27.PNG)

![run task](screenshots/run.PNG)

![swim task](screenshots/swim.PNG)

![walk task](screenshots/walk.PNG)

![setting page](screenshots/setting.PNG)


## day three
* create a Task class. A Task should have a title, a body, and a state. The state should be one of “new”, “assigned”, “in progress”, or “complete”.
* Refactor your homepage to use a RecyclerView for displaying Task data. This should have hardcoded Task data for now.
* In  MainActivity, create at least three hardcoded Task instances and use those to populate your RecyclerView/ViewAdapter.
Ensure that you can tap on any one of the Tasks in the RecyclerView, and it will appropriately launch the detail page with the correct Task title displayed.

![homepage screen](screenshots/lab28.PNG)


## day four
* Modify your Add Task form to save the data entered in as a Task in your local database.
* Refactor homepage’s RecyclerView to display all Task entities in your database.
* the description and status of a tapped task are also displayed on the detail page, in addition to the title. (Note that you can accomplish this by passing along the entire Task entity, or by passing along only its ID in the intent.)

![homepage screen](screenshots/lab29.PNG)


## lab 32
Using the amplify add api command, create a Task resource that replicates our existing Task schema. Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.

![image](screenshots/lab32.PNG)


## lab33

Tasks Are Owned By Teams
Created a second entity for a team, which has a name and a list of tasks. Update your tasks to be owned by a team.

Manually create three teams by running a mutation exactly three times in your code. (You do NOT need to allow the user to create new teams.)

Add Task Form
Modified your Add Task form to include either a Spinner or Radio Buttons for which team that task belongs to.

Settings Page
In addition to a username, allow the user to choose their team on the Settings page. Use that Team to display only that team’s tasks on the homepage.
![image](screenshots/lab33.PNG)

## lab36

Add Cognito to your Amplify setup. Add in user login and sign up flows to your application, using Cognito’s pre-built UI as appropriate. Display the logged in user’s username somewhere relevant in your app.
Allow users to log out of your application.
![image](screenshots/lab36.PNG)









